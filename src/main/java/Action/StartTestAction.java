package Action;

import Logic.DescExtractor;
import Logic.JBSEConnection;
import Logic.TypeBuild;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vcs.VcsBundle;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import javassist.NotFoundException;
import net.sf.cglib.core.Local;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;
import static java.lang.Thread.sleep;

public class StartTestAction extends AnAction {
    private JBSEConnection jbseConnection;
    // Для JBSE
    private String projectPath;
    private String outputClass;
    private String testingClass;
    private ArrayList<String> namesAndDescriptors;

    private static final String GEN_FOLDER_NAME = "GeneratedTest";
    private VirtualFile fileIndokedPopUpMenu;
    private VirtualFile pathToProject;

    @Override
    public void actionPerformed(AnActionEvent e) {
        // Получаем файл по которому мы нажали ПКМ
        fileIndokedPopUpMenu = e.getData(VIRTUAL_FILE);
        // получаем путь до проекта
        projectPath = Objects.requireNonNull(e.getProject()).getBasePath() + "/";

        // виртуальный файл корня проекта, чтобы можно было рекурсивно обновлять весь проект
        pathToProject = LocalFileSystem.getInstance().findFileByPath(projectPath);
        VirtualFile generatedDir = LocalFileSystem.getInstance().findFileByPath(projectPath + GEN_FOLDER_NAME + "/");
        pathToProject.refresh(true,true);

        // если такой папки нет создаем
        if(generatedDir == null){
            try {
                generatedDir = LocalFileSystem.getInstance().createChildDirectory(this, pathToProject, GEN_FOLDER_NAME);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        pathToProject.refresh(true,true);

        String javaFileName = fileIndokedPopUpMenu.getName().replace(".java", "");
        testingClass = fileIndokedPopUpMenu.getCanonicalPath().replace(projectPath+"src/main/java/", "");
        outputClass = "GeneratedTest/" + javaFileName + "Test.java";

        // Получаем дескрипторы и имена методов
        try {
            DescExtractor descExtractor = new DescExtractor(projectPath, typeOfProject());
            namesAndDescriptors = descExtractor.getAllSplittedNameAndDec(javaFileName);
        } catch (NotFoundException notFoundException) {
            Messages.showMessageDialog("The corresponding .class file does not exist. Compile the project and try again.", "Error",
                    Messages.getInformationIcon());
        }

//        runJBSE(e);
//        updateWhileNonVisible(e);

        Messages.showMessageDialog(namesAndDescriptors.toString(), "Generation of test",
                Messages.getInformationIcon());

//        Messages.showMessageDialog(e.getProject(), "Test generation is completed", "Generation of test",
//                Messages.getInformationIcon());
    }

    private void runJBSE(AnActionEvent e) {
        jbseConnection = new JBSEConnection(projectPath, testingClass, namesAndDescriptors.get(1), namesAndDescriptors.get(0), outputClass);
        CommandProcessor.getInstance().executeCommand(e.getProject(), () -> {
            System.out.println("Starting generation");
            jbseConnection.start2();
            System.out.println("Symbolic execution is completed");
        },  VcsBundle.message("command.name.open.error.message.view"), null);
    }

    private void updateWhileNonVisible(AnActionEvent e) {
        VirtualFile virtualFile;
        do {
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            virtualFile = LocalFileSystem.getInstance().findFileByPath(projectPath+outputClass);
            pathToProject.refresh(true,true);
        } while (virtualFile == null);
    }

    private TypeBuild typeOfProject() {
        VirtualFile maven = LocalFileSystem.getInstance().findFileByPath(projectPath+"target/classes/");
        VirtualFile gradle = LocalFileSystem.getInstance().findFileByPath(projectPath + "build/");
        TypeBuild typeBuild;

        if (maven != null) {
            typeBuild = TypeBuild.MAVEN;
        } else if (gradle != null) {
            typeBuild = TypeBuild.GRADLE;
        } else {
            typeBuild = TypeBuild.CLA;
        }
        return typeBuild;
    }
}

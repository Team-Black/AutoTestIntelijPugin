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

    private static final String NAME_OF_GENERATION_FOLDER = "GeneratedTest";
    private VirtualFile virtualFile;

    @Override
    public void actionPerformed(AnActionEvent e) {
        virtualFile = e.getData(VIRTUAL_FILE);
        projectPath = Objects.requireNonNull(e.getProject()).getBasePath() + "/";

        VirtualFile pathToSrc = LocalFileSystem.getInstance().findFileByPath(projectPath);
        VirtualFile generatedDir = LocalFileSystem.getInstance().findFileByPath(projectPath + NAME_OF_GENERATION_FOLDER + "/");
        pathToSrc.refresh(true,true);
        if(generatedDir == null){
            try {
                generatedDir = LocalFileSystem.getInstance().createChildDirectory(this, pathToSrc, NAME_OF_GENERATION_FOLDER);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        pathToSrc.refresh(true,true);
        String javaFileName = virtualFile.getName().replace(".java", "");
        testingClass = virtualFile.getCanonicalPath().replace(projectPath+"src/main/java/", "");
        outputClass = "GeneratedTest/" + javaFileName + "Test.java";

        try {
            DescExtractor descExtractor = new DescExtractor(projectPath, typeOfProject());
            namesAndDescriptors = descExtractor.getAllSplittedNameAndDec(javaFileName);
        } catch (NotFoundException notFoundException) {
            Messages.showMessageDialog("The corresponding .class file does not exist. Compile the project and try again.", "Error",
                    Messages.getInformationIcon());
        }

        Messages.showMessageDialog(e.getProject(), "Test generation is completed", "Generation of test",
                Messages.getInformationIcon());
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
        VirtualFile targetPath = LocalFileSystem.getInstance().
        findFileByPath(Objects.requireNonNull(e.getProject()).getBasePath() + "/out/");
        int i = 0;
        do {
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            targetPath.refresh(true,true);
            i++;
        } while (i < 6);
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

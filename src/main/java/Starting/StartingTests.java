package Starting;

import JBSElogic.JBSEConnection;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vcs.VcsBundle;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Objects;

import static java.lang.Thread.sleep;

public class StartingTests extends AnAction {
    JBSEConnection jbseConnection;

    @Override
    public void actionPerformed(AnActionEvent e) {
        jbseConnection = new JBSEConnection();
        CommandProcessor.getInstance().executeCommand(e.getProject(), () -> {
            System.out.println("Starting generation");
            System.out.println("Initialization and external call to JBSE");
            System.out.println("Symbolic execution is run");
            jbseConnection.start2();
            System.out.println("Symbolic execution is completed");
        },  VcsBundle.message("command.name.open.error.message.view"), null);

        //FIXME по хорошему нам надо использовать файл для проверки в цикле но как я не знаю
//        VirtualFile  targetFile =LocalFileSystem.getInstance().
//                findFileByPath(e.getProject().getBasePath() + jbseConnection.getTEST_NAME());

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

            Messages.showMessageDialog(e.getProject(), "Test generation is completed", "Generation of test",
                    Messages.getInformationIcon());
    }
}

package Starting;

import JBSElogic.JBSEConnection;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vcs.VcsBundle;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import sun.reflect.generics.tree.Tree;

import javax.swing.tree.TreePath;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class StartingTests extends AnAction {
    JBSEConnection jbseConnection;

//    public void popupInvokation() {
//        ActionManager actionManager = ActionManager.getInstance();
//
//        DefaultActionGroup group = new DefaultActionGroup();
//
//        group.add(actionManager.getAction("createTests.AllTestsAction"));
//
//        final ActionPopupMenu popupMenu = ActionManager.getInstance().createActionPopupMenu(ActionPlaces.ANT_EXPLORER_POPUP, group);
//        popupMenu.getComponent().show();
//    }

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

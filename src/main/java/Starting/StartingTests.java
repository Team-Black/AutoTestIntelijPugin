package Starting;

import JBSElogic.JBSEConnection;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

public class StartingTests extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {

        JBSEConnection jbseConnection = new JBSEConnection();
        jbseConnection.start();

        Messages.showMessageDialog(e.getProject(), "Test generation is completed", "Generation of test",
                Messages.getInformationIcon());
    }
}

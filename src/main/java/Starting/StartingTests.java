package Starting;

import JBSElogic.JBSEConnection;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

import java.io.IOException;

public class StartingTests extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {

        try {
            JBSEConnection jbseConnection =  JBSEConnection.class.newInstance();
            jbseConnection.start();
        } catch (InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }

        Messages.showMessageDialog(e.getProject(), "Test generation is completed", "Generation of test",
                Messages.getInformationIcon());
    }
}

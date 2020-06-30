package Settings;

import com.intellij.openapi.components.PersistentStateComponent;

import javax.swing.*;
import java.util.Objects;

/**
 * GUI for the {@link SettingsConfig}
 */
public class SettingsForm {
    private JTextField enterJBSETextField;
    private JTextField enterJRETextField;
    private JTextField enterJBSEhomeTextField;
    private JTextField enterZ3TextField;
    private JPanel rootPanel;

    private PersistentStateComponent<SettingState> state;

    // заполняем созданный GUI тем что уже хранится в persistence
    public void createUI() {
         state = new SettingsPersist().getInstance();
        enterJRETextField.setText(Objects.requireNonNull(state.getState()).jrePath);
        enterJBSETextField.setText(Objects.requireNonNull(state.getState().jarPath));
        enterZ3TextField.setText(Objects.requireNonNull(state.getState()).z3Path);
        enterJBSEhomeTextField.setText(Objects.requireNonNull(state.getState()).jbseHome);
    }

    // получаем всё иерархию GUI
    public JPanel getRootPanel() {
        return rootPanel;
    }

    // произошли ли изменения
    public boolean isModified() {
        boolean modified = false;
        modified |= !enterJRETextField.getText().equals(Objects.requireNonNull(state.getState()).jrePath);
        modified |= !enterJBSETextField.getText().equals(state.getState().jarPath);
        modified |= !enterZ3TextField.getText().equals(state.getState().z3Path);
        modified |= !enterJBSEhomeTextField.getText().equals(state.getState().jbseHome);
        return modified;
    }

    // сохраняем изменения в persistence
    public void apply() {
        Objects.requireNonNull(state.getState()).jarPath = enterJBSETextField.getText();
        Objects.requireNonNull(state.getState()).jrePath = enterJRETextField.getText();
        Objects.requireNonNull(state.getState()).jbseHome = enterJBSEhomeTextField.getText();
        Objects.requireNonNull(state.getState()).z3Path = enterZ3TextField.getText();
    }

    //сбрасываем изменения до тех которые были сохраннены в последний раз
    public void reset() {
        enterJRETextField.setText(Objects.requireNonNull(state.getState()).jrePath);
        enterJBSETextField.setText(Objects.requireNonNull(state.getState().jarPath));
        enterZ3TextField.setText(Objects.requireNonNull(state.getState()).z3Path);
        enterJBSEhomeTextField.setText(Objects.requireNonNull(state.getState()).jbseHome);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}

package Settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBLabel;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SettingsDataWrapper extends DialogWrapper {

    private JPanel panel = new JPanel(new GridBagLayout());
    private JTextField txtJbseHome = new JTextField();
    private JTextField txtZ3Path = new JTextField();
    private JTextField txtTestHome = new JTextField(); // можно будет убрать
    private JTextField txtMethodClass = new JTextField();
    private JTextField txtMethodDescriptor = new JTextField();
    private JTextField txtMethodName = new JTextField();
    private JTextField txtTestName = new JTextField();
    private JTextField txtJrePath = new JTextField();
    private JTextField txtJarPath = new JTextField();
    //    private JTextField txtFormat = new JTextField();

    protected SettingsDataWrapper(boolean canBeParent) {
        super(canBeParent);
        init();
        setTitle("Plugin settings");

        PersistentStateComponent<SettingState> state = new SettingsPlugin().getInstance();
        if (state != null) {
//            txtFormat.setText(Objects.requireNonNull(state.getState()).format);
            txtJrePath.setText(Objects.requireNonNull(state.getState()).jrePath);
            txtJarPath.setText(Objects.requireNonNull(state.getState().jarPath));
            txtZ3Path.setText(Objects.requireNonNull(state.getState()).z3Path);
            txtJbseHome.setText(Objects.requireNonNull(state.getState()).jbseHome);
            txtTestHome.setText(Objects.requireNonNull(state.getState()).testHome);
            txtTestName.setText(Objects.requireNonNull(state.getState()).testName);
            txtMethodClass.setText(Objects.requireNonNull(state.getState()).methodClass);
            txtMethodName.setText(Objects.requireNonNull(state.getState()).methodName);
            txtMethodDescriptor.setText(Objects.requireNonNull(state.getState()).methodDesc);
        }
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        GridBag gb = new GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL);

        panel.setPreferredSize(new Dimension(600, 300));
//        1 txtJbseHome
//        2 txtZ3Path
//        3 txtTestHome
//        4 txtTestName
//        5 txtMethodClass
//        6 txtMethodName
//        7 txtMethodDescriptor

//        panel.add(label("Format :"), gb.nextLine().next().weightx(0.2));
//        panel.add(txtFormat, gb.next().weightx(0.8));

        panel.add(label("Enter JAR path :"), gb.nextLine().next().weightx(0.2));
        panel.add(txtJarPath, gb.next().weightx(0.8));

        panel.add(label("Enter JRE path :"), gb.nextLine().next().weightx(0.2));
        panel.add(txtJrePath, gb.next().weightx(0.8));

        panel.add(label("Enter JBSE home path:"), gb.nextLine().next().weightx(0.2));
        panel.add(txtJbseHome, gb.next().weightx(0.8));

        panel.add(label("Enter Z3 path:"), gb.nextLine().next().weightx(0.2));
        panel.add(txtZ3Path, gb.next().weightx(0.8));

        panel.add(label("Enter test home path:"), gb.nextLine().next().weightx(0.2));
        panel.add(txtTestHome, gb.next().weightx(0.8));

        panel.add(label("Enter name of test class:"), gb.nextLine().next().weightx(0.2));
        panel.add(txtTestName, gb.next().weightx(0.8));

        panel.add(label("Enter class of a method :"), gb.nextLine().next().weightx(0.2));
        panel.add(txtMethodClass, gb.next().weightx(0.8));

        panel.add(label("Enter method name:"), gb.nextLine().next().weightx(0.2));
        panel.add(txtMethodName, gb.next().weightx(0.8));

        panel.add(label("Enter descriptor of method:"), gb.nextLine().next().weightx(0.2));
        panel.add(txtMethodDescriptor, gb.next().weightx(0.8));

        return panel;
    }

    @Override
    protected void doOKAction() {
        PersistentStateComponent<SettingState> state = new SettingsPlugin().getInstance();
        Objects.requireNonNull(state.getState()).jarPath = txtJarPath.getText();
        Objects.requireNonNull(state.getState()).jrePath = txtJrePath.getText();
        Objects.requireNonNull(state.getState()).jbseHome = txtJbseHome.getText();
        Objects.requireNonNull(state.getState()).z3Path = txtZ3Path.getText();
        Objects.requireNonNull(state.getState()).testHome = txtTestHome.getText();
        Objects.requireNonNull(state.getState()).testName = txtTestName.getText();
        Objects.requireNonNull(state.getState()).methodClass = txtMethodClass.getText();
        Objects.requireNonNull(state.getState()).methodName = txtMethodName.getText();
        Objects.requireNonNull(state.getState()).methodDesc = txtMethodDescriptor.getText();
//        Objects.requireNonNull(state.getState()).format = txtFormat.getText();

        close(OK_EXIT_CODE);
    }


    private JComponent label(String text) {
        JBLabel label = new JBLabel(text);
        label.setComponentStyle(UIUtil.ComponentStyle.SMALL);
        label.setFontColor(UIUtil.FontColor.BRIGHTER);
        label.setBorder(JBUI.Borders.empty(0, 5, 2, 0));
        return label;
    }
}

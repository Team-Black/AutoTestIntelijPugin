package Settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.sun.istack.Nullable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class IdeaOtherSettingsConfigure implements SearchableConfigurable {
    IdeaOtherSettingsForm mGUI;

    @Nullable
    @Override
    public JComponent createComponent() {
        mGUI = new IdeaOtherSettingsForm();
        return mGUI.$$$getRootComponent$$$();
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
    }

    @Override
    public void disposeUIResources() {
        mGUI = null;
    }

    @Override
    public @NotNull String getId() {
        return "1";
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return null;
    }
}

package Settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.sun.istack.Nullable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class IdeaOtherSettingsConfig implements SearchableConfigurable {
    private IdeaOtherSettingsForm mGUI;
    private PersistentStateComponent<SettingState> mState;

    @Nullable
    @Override
    public JComponent createComponent() {
        mGUI = new IdeaOtherSettingsForm();
        mGUI.createUI();
        return mGUI.getRootPanel();
    }

    public IdeaOtherSettingsConfig() {
        mState = new SettingsPlugin().getInstance();
    }

    @Override
    public @org.jetbrains.annotations.Nullable Runnable enableSearch(String option) {
        return null;
    }

    @Override
    public void reset() {
        mGUI.reset();
    }

    @Override
    public boolean isModified() {
        return mGUI.isModified();
    }

    @Override
    public void apply() throws ConfigurationException {
        mGUI.apply();
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
        return  "AutoTest Generator";
    }
}

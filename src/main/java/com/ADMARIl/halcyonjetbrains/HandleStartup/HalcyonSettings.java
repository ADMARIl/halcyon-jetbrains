package com.ADMARIl.halcyonjetbrains.HandleStartup;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "HTSettings",
        storages = {@Storage("halcyon.xml")}
)
public class HalcyonSettings implements PersistentStateComponent<HalcyonSettingsData> {
    private HalcyonSettingsData halcyonSettings = new HalcyonSettingsData();

    private static final HalcyonSettings HALCYON_SETTINGS_COMPONENT = new HalcyonSettings();

    @Nullable
    @Override
    public HalcyonSettingsData getState() {
        return halcyonSettings;
    }

    @Override
    public void loadState(@NotNull HalcyonSettingsData state) {
        this.halcyonSettings = state;
    }

    public HalcyonSettings() {
    }

    public static HalcyonSettings getInstance() {
        if (ApplicationManager.getApplication() == null) {
            return HALCYON_SETTINGS_COMPONENT;
        }

        HalcyonSettings service = ServiceManager.getService(HalcyonSettings.class);
        if (service == null) {
            return HALCYON_SETTINGS_COMPONENT;
        }

        return service;
    }

    public void setEnabled(boolean enabled) {
        assert getState() != null;
        getState().enabled = enabled;
    }

    public boolean isEnabled() {
        assert getState() != null;
        return getState().enabled;
    }

    public void setWelcomeNotifyShowed(boolean welcomeNotifyShowed) {
        assert getState() != null;
        getState().welcomeNotifyShowed = welcomeNotifyShowed;
    }

    public boolean isWelcomeNotifyShowed() {
        assert getState() != null;
        return getState().welcomeNotifyShowed;
    }

}

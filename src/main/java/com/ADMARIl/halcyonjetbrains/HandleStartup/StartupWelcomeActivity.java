package com.ADMARIl.halcyonjetbrains.HandleStartup;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.HyperlinkEvent;

public class StartupWelcomeActivity implements StartupActivity {
    @Override
    public void runActivity(@NotNull Project project) {
        if (CsvSettings.getInstance().isWelcomeNotifyShowed()) {
            return;
        }

        NotificationGroup notificationGroup = new NotificationGroup(
                "Halcyon Theme", NotificationDisplayType.STICKY_BALLOON, true
        );

        NotificationListener.Adapter notificationListener = new NotificationListener.Adapter() {
            @Override
            protected void hyperlinkActivated(@NotNull Notification notification, @NotNull HyperlinkEvent e) {
                if (!project.isDisposed()) {
                    ShowSettingsUtil.getInstance().showSettingsDialog(project, RainbowCsvHelper.OPTIONS_NAME);
                }
            }
        };

        Notification notification = notificationGroup.createNotification(
                "Halcyon-Theme",
                "You can set the Theme settings in " +
                        "<a href=\"#\">Settings → Appearance & Behavior → Appearance → Theme</a>",
                NotificationType.INFORMATION,
                notificationListener
        );

        Notifications.Bus.notify(notification);
        CsvSettings.getInstance().setWelcomeNotifyShowed(true);
    }
}

package com.ADMARIl.halcyonjetbrains.HandleStartup;

import com.intellij.notification.*;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.HyperlinkEvent;

public class StartupWelcomeActivity implements StartupActivity {
    @Override
    public void runActivity(@NotNull Project project) {
        if (HalcyonSettings.getInstance().isWelcomeNotifyShowed()) {
            return;
        }

//        NotificationGroup notificationGroup = new NotificationGroup(
//                "Halcyon Theme", NotificationDisplayType.STICKY_BALLOON, true
//        );

        NotificationListener.Adapter notificationListener = new NotificationListener.Adapter() {
            @Override
            protected void hyperlinkActivated(@NotNull Notification notification, @NotNull HyperlinkEvent e) {
                if (!project.isDisposed()) {
                    ShowSettingsUtil.getInstance().showSettingsDialog(project, "Appearance & Behavior");
                }
            }
        };

        Notification notification = NotificationGroupManager.getInstance().getNotificationGroup("Halcyon Theme").createNotification(
                "Halcyon-Theme",
                "If you don't see the correct theme, make sure it's selected in " +
                        "<a href=\"#\">Settings → Appearance & Behavior → Appearance → Theme</a>",
                NotificationType.INFORMATION,
                notificationListener
        );

        Notifications.Bus.notify(notification);
        HalcyonSettings.getInstance().setWelcomeNotifyShowed(true);
    }
}

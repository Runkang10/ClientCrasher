package org.sysapp.runkang10.clientcrasher.utils;

import net.kyori.adventure.text.Component;
import org.sysapp.runkang10.clientcrasher.ClientCrasher;

public class SenderUtils {
    private static final ClientCrasher plugin = ClientCrasher.getInstance();

    public static void sendConsoleMessage(Component message) {
        plugin.getServer().getConsoleSender().sendMessage(message);
    }

    public static void sendConsoleError(String message) {
        plugin.getLogger().severe(message);
    }

    public static void sendConsoleWarning(String message) {
        plugin.getLogger().warning(message);
    }
}

package org.sysapp.runkang10.clientcrasher.utils;

import net.kyori.adventure.text.Component;
import org.sysapp.runkang10.clientcrasher.ClientCrasher;

public class SenderUtils {
    private static ClientCrasher plugin = ClientCrasher.getInstance();

    public static void sendConsoleMessage(Component message) {
        plugin.getServer().getConsoleSender().sendMessage(message);
    }
}

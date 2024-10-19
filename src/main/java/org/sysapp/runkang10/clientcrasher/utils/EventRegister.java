package org.sysapp.runkang10.clientcrasher.utils;

import org.bukkit.event.Listener;
import org.sysapp.runkang10.clientcrasher.ClientCrasher;

public class EventRegister {
    private static final ClientCrasher plugin = ClientCrasher.getInstance();

    public static void register(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}

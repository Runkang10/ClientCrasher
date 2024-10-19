package org.sysapp.runkang10.clientcrasher.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

import static org.sysapp.runkang10.clientcrasher.ClientCrasher.getData;

public class OnPlayerJoin implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        // Check if the player is in the crash list
        List<String> CrashList = getData().getStringList("crashlist");

        if (CrashList.contains(event.getPlayer().getName()))
            event.getPlayer().kick();
    }
}

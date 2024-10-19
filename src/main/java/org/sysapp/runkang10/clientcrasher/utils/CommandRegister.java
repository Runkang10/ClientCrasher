package org.sysapp.runkang10.clientcrasher.utils;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.sysapp.runkang10.clientcrasher.ClientCrasher;
import org.sysapp.runkang10.clientcrasher.commands.CrashCommand;
import org.sysapp.runkang10.clientcrasher.commands.admin.ClientCrasherCommand;

public class CommandRegister {
    public CommandRegister(ClientCrasher plugin) {
        // LifecycleEventManager
        LifecycleEventManager<@NotNull Plugin> manager = plugin.getLifecycleManager();

        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            Commands commands = event.registrar();
            // ClientCrasher command (Admin)
            // Crash command
            // CrashTempBan command
            // CrashBan command
            // CrashUnBan command
            commands.register(
                    "crash",
                    "",
                    new CrashCommand()
            );
            new ClientCrasherCommand(commands);
        });
    }
}

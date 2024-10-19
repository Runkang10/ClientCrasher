package org.sysapp.runkang10.clientcrasher.commands.admin;

import com.mojang.brigadier.Command;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;
import org.sysapp.runkang10.clientcrasher.ClientCrasher;

import java.util.List;

import static org.sysapp.runkang10.clientcrasher.ClientCrasher.pluginConfig;

public class ClientCrasherCommand {
    private static final ClientCrasher plugin = ClientCrasher.getInstance();

    public ClientCrasherCommand(Commands commands) {
        commands.register(
                Commands.literal("clientcrasher")
                        .then(
                                Commands.literal("reload")
                                        .executes(ctx -> {
                                            ctx.getSource().getSender().sendMessage(
                                                    Component.text("Reloading ClientCrasher ...")
                                                            .color(NamedTextColor.RED)
                                                            .decorate(TextDecoration.BOLD)
                                            );

                                            try {
                                                plugin.reloadConfig();
                                                ctx.getSource().getSender().sendMessage(Component.text("ClientCrasher reloaded successfully!")
                                                        .color(NamedTextColor.DARK_AQUA)
                                                        .decorate(TextDecoration.BOLD));

                                                return Command.SINGLE_SUCCESS;
                                            } catch (Exception exception) {
                                                ctx.getSource().getSender().sendMessage(Component.text("Could not reload ClientCrasher. Reason: " + exception.getMessage())
                                                        .color(NamedTextColor.DARK_AQUA)
                                                        .decorate(TextDecoration.BOLD));

                                                return Command.SINGLE_SUCCESS;
                                            }
                                        }))
                        .then(
                                Commands.literal("info")
                                        .executes(ctx -> {
                                            CommandSender message = ctx.getSource().getSender();
                                            message.sendMessage("");
                                            message.sendMessage(
                                                    Component.text("   " + pluginConfig.getName() + " " + "(v" + pluginConfig.getVersion() + ")")
                                                            .color(NamedTextColor.RED)
                                                            .decorate(TextDecoration.BOLD)
                                            );
                                            message.sendMessage(
                                                    Component.text("   " + pluginConfig.getDescription())
                                                            .color(NamedTextColor.GRAY)
                                            );
                                            message.sendMessage(
                                                    Component.text("   " + "API version: ")
                                                            .color(NamedTextColor.WHITE)
                                                            .append(
                                                                    Component.text(pluginConfig.getApiVersion())
                                                                            .color(NamedTextColor.DARK_AQUA)
                                                                            .decorate(TextDecoration.BOLD)
                                                            )
                                            );
                                            message.sendMessage("");

                                            return Command.SINGLE_SUCCESS;
                                        })
                        )
                        .build(),

                "ClientCrasher Admin command.",
                List.of("cc", "clientc", "ccrasher")
        );
    }
}

package org.sysapp.runkang10.clientcrasher.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.ArgumentType;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.PlayerProfileListResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.Nullable;
import org.sysapp.runkang10.clientcrasher.ClientCrasher;

import java.util.Collection;
import java.util.Objects;

public class CrashCommand implements BasicCommand {
    private final static ClientCrasher instance = ClientCrasher.getInstance();


    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("kickplayer")) {
            Objects.requireNonNull(instance.getServer().getPlayer(commandSourceStack.getSender().getName())).kick();
        }
    }

    @Override
    public Collection<String> suggest(CommandSourceStack commandSourceStack, String[] args) {
        return BasicCommand.super.suggest(commandSourceStack, args);
    }

    @Override
    public boolean canUse(CommandSender sender) {
        return BasicCommand.super.canUse(sender);
    }

    @Override
    public @Nullable String permission() {
        return BasicCommand.super.permission();
    }
}
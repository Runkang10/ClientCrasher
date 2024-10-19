package org.sysapp.runkang10.clientcrasher;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.sysapp.runkang10.clientcrasher.api.PaperPluginConfig;
import org.sysapp.runkang10.clientcrasher.events.OnPlayerJoin;
import org.sysapp.runkang10.clientcrasher.utils.CommandRegister;
import org.sysapp.runkang10.clientcrasher.utils.EventRegister;
import org.sysapp.runkang10.clientcrasher.utils.SetupServer;

import java.util.Objects;

import static org.sysapp.runkang10.clientcrasher.utils.SenderUtils.sendConsoleMessage;

public final class ClientCrasher extends JavaPlugin {
    private static ClientCrasher instance;
    private static YamlConfiguration DB;
    private static ProtocolManager protocolManager;
    public static PaperPluginConfig pluginConfig;

    @Override
    public void onEnable() {
        instance = this;
        super.onEnable();
        // Setup
        pluginConfig = new PaperPluginConfig();
        DB = new SetupServer().initializeSetupServer();
        this.getLogger().info(DB.toString());
        // Load on enable message
        this.LoadMessage("enabled");
        // Initialize ProtocolLib
        this.initializeProtocolLib();

        // Register Commands
        new CommandRegister(this);
        // Register Events
        EventRegister.register(new OnPlayerJoin());
    }

    private void initializeProtocolLib() {
        PluginManager pluginManager = getServer().getPluginManager();
        Plugin protocolLib = pluginManager.getPlugin("ProtocolLib");

        if (protocolLib == null || !protocolLib.isEnabled()) return;

        try {
            protocolManager = ProtocolLibrary.getProtocolManager();
            sendConsoleMessage(Component.text("ProtocolLib found and initialized correctly!").color(NamedTextColor.GREEN));

        } catch (Exception exception) {
            this.getLogger().severe("Could not initialize ProtocolLib: " + exception.getMessage());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.LoadMessage("disabled");
    }

    public static ClientCrasher getInstance() {
        return instance;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public static YamlConfiguration getData() {
        return DB;
    }

    private void LoadMessage(String msg) {
        NamedTextColor DMS;

        String name = pluginConfig.getName();
        String version = pluginConfig.getVersion();
        String authors = pluginConfig.getAuthors().getFirst();

        if (Objects.equals(msg, "successfully"))
            DMS = NamedTextColor.GREEN;
        else
            DMS = NamedTextColor.RED;

        sendConsoleMessage(Component.text(" ___  ___").color(NamedTextColor.DARK_RED));
        sendConsoleMessage(Component.text("/    /").color(NamedTextColor.DARK_RED));
        sendConsoleMessage(Component.text("|    |       ").color(NamedTextColor.DARK_RED).append(Component.text(name + " ").color(NamedTextColor.RED).decorate(TextDecoration.BOLD).append(Component.text("(v" + version + ")"))));
        sendConsoleMessage(Component.text("|    |       ").color(NamedTextColor.DARK_RED).append(Component.text("Powered by " + authors).color(NamedTextColor.DARK_GRAY)));
        sendConsoleMessage(Component.text("\\___ \\___").color(NamedTextColor.DARK_RED));
        sendConsoleMessage(Component.newline());
        sendConsoleMessage(Component.text("Successfully " + msg + " ").color(DMS).append(Component.text(name + " v" + version).decorate(TextDecoration.BOLD).append(Component.text("!").color(NamedTextColor.GREEN))));
    }
}

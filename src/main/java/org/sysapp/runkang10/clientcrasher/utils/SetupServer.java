package org.sysapp.runkang10.clientcrasher.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.sysapp.runkang10.clientcrasher.ClientCrasher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class SetupServer {
    private static final ClientCrasher plugin = ClientCrasher.getInstance();
    private File configFile;
    private FileConfiguration configuration;

    public YamlConfiguration initializeSetupServer() {
        // Check if the plugin folder exists
        if (!this.checkDataFolder()) {
            this.createDataFolder();
        } else {
            this.loadExistingConfig();
        }

        return getData();
    }

    private boolean checkDataFolder() {
        return plugin.getDataFolder().exists();
    }

    private YamlConfiguration getData() {
        return YamlConfiguration.loadConfiguration(configFile);
    }

    private void createDataFolder() {
        configFile = new File(plugin.getDataFolder(), "db.yml");

        // Try to create the plugin folder
        try {
            plugin.getDataFolder().mkdirs();
            // Copy the db.yml file from resources
            copyDefaultConfig();

            // Load the configuration file
            configuration = YamlConfiguration.loadConfiguration(configFile);

        } catch (IOException e) {
            SenderUtils.sendConsoleError("Could not create ClientCrasher's folder: " + e.getMessage());
        }
    }

    private void loadExistingConfig() {
        configFile = new File(plugin.getDataFolder(), "db.yml");

        // Load the existing configuration file
        configuration = YamlConfiguration.loadConfiguration(configFile);
    }

    private void copyDefaultConfig() throws IOException {
        // Copy the db.yml file from resources to the plugin folder
        try (InputStream in = plugin.getResource("db.yml")) {
            if (in == null) {
                SenderUtils.sendConsoleError("Default config file db.yml not found in resources.");
                return;
            }
            Files.copy(in, configFile.toPath());
        }
    }
}
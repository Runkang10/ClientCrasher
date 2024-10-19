package org.sysapp.runkang10.clientcrasher.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.sysapp.runkang10.clientcrasher.ClientCrasher;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class PaperPluginConfig {
    private final static ClientCrasher instance = ClientCrasher.getInstance();
    private final YamlConfiguration ConfigLoader;

    public PaperPluginConfig() {
        InputStream stream = instance.getResource("paper-plugin.yml");
        ConfigLoader = YamlConfiguration.loadConfiguration(new InputStreamReader(stream));
    }

    public String getName() {
        return ConfigLoader.getString("name", "Unknown");
    }

    public String getVersion() {
        return ConfigLoader.getString("version", "Unknown");
    }

    public String getApiVersion() {
        return ConfigLoader.getString("api-version", "Unknown");
    }

    public List<String> getAuthors() {
        return ConfigLoader.getStringList("authors");
    }

    public String getDescription() {
        return ConfigLoader.getString("description", "No description available");
    }
}

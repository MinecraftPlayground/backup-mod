package dev.loat.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import dev.loat.logging.Logger;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigManager {
    
    public static final String path = "backup";
    private static final Map<Class<?>, Config<?>> configs = new HashMap<>();

    public static <ConfigFile> void addConfig(
        Config<ConfigFile> config
    ) {
        configs.put(config.getConfigFileClass(), config);
    }

    @SuppressWarnings("unchecked")
    public static <ConfigFile> Config<ConfigFile> getConfig(Class<ConfigFile> type) {
        return (Config<ConfigFile>) configs.get(type);
    }
        
    public static Path resolve(String configFile) {
        return ConfigManager.resolve(Path.of(configFile));
    }
    
    public static Path resolve(Path configFile) {
        Path configFilePath = FabricLoader.getInstance()
            .getConfigDir()
            .resolve(ConfigManager.path);

        try {
            Files.createDirectories(configFilePath);
        } catch (IOException e) {
            Logger.error("Could not create config directory:\n%s".formatted(e));
        }

        return configFilePath.resolve(configFile);
    }

    public static void loadAll() {
        ConfigManager.configs.values().forEach(Config::load);
    }
}

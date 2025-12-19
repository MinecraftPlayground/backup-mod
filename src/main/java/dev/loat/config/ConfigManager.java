package dev.loat.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import dev.loat.config.files.BackupConfigFile;
import dev.loat.logging.Logger;
import net.fabricmc.loader.api.FabricLoader;


public class ConfigManager {
    
    public static final String path = "backup";

    @SuppressWarnings("null")
    public static final Config<BackupConfigFile> CONFIG = new Config<>(
        ConfigManager.resolve("config.yml"),
        BackupConfigFile.class
    );

        
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
        CONFIG.load();
    }
}

package dev.loat.config;

import dev.loat.config.parser.YamlSerializer;
import dev.loat.logging.Logger;

import java.io.File;
import java.nio.file.Path;


public class Config<ConfigFile> {
    private final Class<ConfigFile> configFileClass;
    private final YamlSerializer<ConfigFile> serializer;
    @SuppressWarnings("null")
    private ConfigFile config = null;

    public Config(
        Path path,
        Class<ConfigFile> configFileClass
    ) {
        this.configFileClass = configFileClass;
        this.serializer = new YamlSerializer<>(
            path.toString(),
            configFileClass
        );

        try {
            File file = path.toFile();

            // Check if the config file exists, if not create it
            if(!file.exists()) {
                this.serializer.serialize(configFileClass.getDeclaredConstructor().newInstance());
            }

            this.load();
        } catch (Exception serializeException) {
            Logger.error("Error while serializing the config file:\n%s".formatted(serializeException));
        }
    }

    Class<ConfigFile> getConfigFileClass() {
        return this.configFileClass;
    }

    @SuppressWarnings("null")
    public void load() {
        try {
            this.config = this.serializer.parse();
        } catch (Exception parseException) {
            Logger.error("Error while parsing the config file:\n%s".formatted(parseException));

            try {
                this.config = this.configFileClass.getDeclaredConstructor().newInstance();
            } catch (Exception newInstanceException) {
                Logger.error("Error while creating a new instance of the config class:\n%s".formatted(newInstanceException));

                this.config = null;
            }
        }
    }

    public ConfigFile get() {
        return this.config;
    }
}

package dev.loat.config;

import dev.loat.config.parser.YamlSerializer;
import dev.loat.logging.Logger;

import java.io.File;
import java.nio.file.Path;

/**
 * Represents a configuration file.
 * 
 * @param <ConfigFile> The config file type
 */
public class Config<ConfigFile> {
    private Path path;
    private final Class<ConfigFile> configFileClass;
    private final YamlSerializer<ConfigFile> serializer;
    @SuppressWarnings("null")
    private ConfigFile config = null;

    /**
     * Creates a new config instance.
     *
     * @param path The path to the config file
     * @param configFileClass The config file class
     */
    public Config(
        Path path,
        Class<ConfigFile> configFileClass
    ) {
        this.path = path;
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
            Logger.error("Error while serializing the config file (%s):\n%s".formatted(this.path.toString(), serializeException));
        }
    }


    /**
     * Returns the config file class associated with this config.
     *
     * @return The config file class
     */
    Class<ConfigFile> getConfigFileClass() {
        return this.configFileClass;
    }


    /**
     * Loads the configuration from the file.
     *
     * If the file does not exist, it will be created with default values.
     *
     * If an error occurs while parsing the file, it will be caught and logged.
     * In this case, a new instance of the config class will be created using its default constructor.
     * If an error occurs while creating a new instance of the config class, it will be caught and logged.
     * In this case, the config will be set to null.
     */
    @SuppressWarnings("null")
    public void load() {
        try {
            this.config = this.serializer.parse();
        } catch (Exception parseException) {
            Logger.error("Error while parsing the config file (%s):\n%s".formatted(this.path.toString(), parseException));

            try {
                this.config = this.configFileClass.getDeclaredConstructor().newInstance();
            } catch (Exception newInstanceException) {
                Logger.error("Error while creating a new instance of the config class:\n%s".formatted(newInstanceException));

                this.config = null;
            }
        }
    }

    /**
     * Returns the configuration associated with this config.
     *
     * @return The configuration associated with this config, or null if an error occurred while parsing or creating the config.
     */
    public ConfigFile get() {
        return this.config;
    }
}

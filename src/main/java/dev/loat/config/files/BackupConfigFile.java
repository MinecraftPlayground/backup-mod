package dev.loat.config.files;

import dev.loat.config.components.Level;
import dev.loat.config.parser.annotation.Comment;

public final class BackupConfigFile {
    @Comment("The Port to use for the API server.")
    public int port = 8080;

    @Comment("""
    Maximum log level to use for transmit. This is the highest level of logging a connected client can receive.
    
    Setting this for example to "INFO" will log INFO, WARN and ERROR messages.
    ERROR > WARN > INFO > DEBUG
    
    Possible Values:
    - "DEBUG"
    - "INFO"
    - "WARN"
    - "ERROR"
    """)
    public String logLevel = Level.INFO;
}

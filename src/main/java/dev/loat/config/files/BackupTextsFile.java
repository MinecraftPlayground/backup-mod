package dev.loat.config.files;

import dev.loat.config.annotation.Comment;

public final class BackupTextsFile {
    @Comment("/backup help")
    public String commandHelp = """
    /backup help Displays this help message.
    /backup reload Reloads the backup configuration and texts.
    /backup create [<comment>] Creates a new backup.
    /backup restore (latest|<name>) Restores an existing backup.
    /backup delete <name> Deletes an existing backup.
    /backup list Lists all existing backups.
    /backup info <name> Displays information about an existing backup.
    /backup cleanup Cleans up old backups based on the configuration.
    """;

    @Comment("/backup reload")
    public String commandReload = "Config reloaded successfully.";
}

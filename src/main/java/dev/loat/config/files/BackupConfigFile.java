package dev.loat.config.files;

import dev.loat.config.annotation.Comment;

public final class BackupConfigFile {
    @Comment("""        
    Path to the directory where backup files should be saved. This can be both,
    an absolute or relative path to the game folder.

    If no absolute path is specified, it defaults to the current working directory,
    aka. to the directory where the server.jar sits in.
    """)
    public String path = "./backups";
}

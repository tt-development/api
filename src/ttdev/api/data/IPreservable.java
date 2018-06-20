package ttdev.api.data;

import org.bukkit.configuration.file.FileConfiguration;

public interface IPreservable {

    void save(FileConfiguration configuration);

    Object load(FileConfiguration configuration);

}

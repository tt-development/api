package ttdev.api.general.data;

import org.bukkit.configuration.file.FileConfiguration;

public interface IPreservable {

    /**
     * An abstract method for saving custom objects.
     * @param configuration
     */
    void save(FileConfiguration configuration);

    /**
     * An abstract method for loading custom objects.
     * @param configuration
     * @return
     */
    Object load(FileConfiguration configuration);

}

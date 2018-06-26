package ttdev.api.general.data;

import org.bukkit.configuration.file.FileConfiguration;

public abstract class Preservable implements IPreservable {

    @Override
    public abstract void save(FileConfiguration configuration);

    @Override
    public abstract Object load(FileConfiguration configuration);

}

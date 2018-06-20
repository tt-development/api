package ttdev.api.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class DataStore implements IDataStore {

    private File associatedFile;
    private FileConfiguration configuration;

    @Override
    public void useFile(String path) {
        associatedFile = new File(path);
        configuration = YamlConfiguration.loadConfiguration(associatedFile);
    }

    @Override
    public void saveString(String value, String path) {
        configuration.set(path, value);
        saveConfiguration();
    }

    @Override
    public void saveInteger(Integer value, String path) {
        configuration.set(path, value);
        saveConfiguration();
    }

    @Override
    public void saveDouble(Double value, String path) {
        configuration.set(path, value);
        saveConfiguration();
    }

    @Override
    public void save(IPreservable preservable) {
        preservable.save(configuration);
    }

    @Override
    public void load(IPreservable preservable) {
        preservable.load(configuration);
    }

    private void saveConfiguration() {
        try {
            configuration.save(associatedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

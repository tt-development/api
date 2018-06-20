package ttdev.api.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataStore implements IDataStore {

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
    public String loadString(String path) {
        return configuration.getString(path);
    }

    @Override
    public void saveInteger(Integer value, String path) {
        configuration.set(path, value);
        saveConfiguration();
    }

    @Override
    public Integer loadInteger(String path) {
        return configuration.getInt(path);
    }

    @Override
    public void saveDouble(Double value, String path) {
        configuration.set(path, value);
        saveConfiguration();
    }

    @Override
    public Double loadDouble(String path) {
        return configuration.getDouble(path);
    }

    @Override
    public void save(IPreservable preservable) {
        preservable.save(configuration);
        saveConfiguration();
    }

    @Override
    public void load(IPreservable preservable) {
        preservable.load(configuration);
        saveConfiguration();
    }

    private void saveConfiguration() {
        try {
            configuration.save(associatedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

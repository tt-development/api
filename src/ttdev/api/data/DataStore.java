package ttdev.api.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataStore implements IDataStore {

    private File associatedFile;
    private FileConfiguration configuration;
    private String identifier = "";

    @Override
    public void useIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public void removeIdentifier() {
        this.identifier = "";
    }

    @Override
    public void useFile(String path) {
        associatedFile = new File(path);
        configuration = YamlConfiguration.loadConfiguration(associatedFile);
    }

    @Override
    public void saveString(String value, String path) {
        configuration.set(identifier + "." + path, value);
        saveConfiguration();
    }

    @Override
    public String loadString(String path) {
        return configuration.getString(identifier + "." + path);
    }

    @Override
    public void saveInteger(Integer value, String path) {
        configuration.set(identifier + "." + path, value);
        saveConfiguration();
    }

    @Override
    public Integer loadInteger(String path) {
        return configuration.getInt(identifier + "." + path);
    }

    @Override
    public void saveDouble(Double value, String path) {
        configuration.set(identifier + "." + path, value);
        saveConfiguration();
    }

    @Override
    public Double loadDouble(String path) {
        return configuration.getDouble(identifier + "." + path);
    }

    @Override
    public void saveObject(Object value, String path) {
        configuration.set(identifier + "." + path, value);
        saveConfiguration();
    }

    @Override
    public Object loadObject(String path) {
        return configuration.get(identifier + "." + path);
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

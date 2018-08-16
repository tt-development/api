package ttdev.api.data;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DataStore implements IDataStore {

    private JavaPlugin plugin;

    private File associatedFile;
    private FileConfiguration configuration;
    private String identifier = "";

    private boolean defaultConfig = false;

    private final String pathDelimiter = ".";

    public DataStore() {

    }

    public DataStore(JavaPlugin plugin) {
        this.plugin = plugin;
        configuration = plugin.getConfig();
        defaultConfig = true;
    }

    public DataStore(String filePath) {
        useFile(filePath);
    }

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
        configuration.set(identifier + pathDelimiter + path, value);
        saveConfiguration();
    }

    @Override
    public String loadString(String path) {
        return configuration.getString(identifier + pathDelimiter + path);
    }

    @Override
    public void saveInteger(Integer value, String path) {
        configuration.set(identifier + pathDelimiter + path, value);
        saveConfiguration();
    }

    @Override
    public Integer loadInteger(String path) {
        return configuration.getInt(identifier + pathDelimiter + path);
    }

    @Override
    public void saveShort(Short value, String path) {
        configuration.set(identifier + pathDelimiter + path, value);
        saveConfiguration();
    }

    @Override
    public Short loadShort(String path) {
        return (short) configuration.getInt(identifier + pathDelimiter + path);
    }

    @Override
    public void saveLong(Long value, String path) {
        configuration.set(identifier + pathDelimiter + path, value);
        saveConfiguration();
    }

    @Override
    public long loadLong(String path) {
        return configuration.getLong(identifier + pathDelimiter + path);
    }

    @Override
    public void saveDouble(Double value, String path) {
        configuration.set(identifier + pathDelimiter + path, value);
        saveConfiguration();
    }

    @Override
    public Double loadDouble(String path) {
        return configuration.getDouble(identifier + pathDelimiter + path);
    }

    @Override
    public void saveObject(Object value, String path) {
        configuration.set(identifier + pathDelimiter + path, value);
        saveConfiguration();
    }

    @Override
    public Object loadObject(String path) {
        return configuration.get(identifier + pathDelimiter + path);
    }

    @Override
    public <T, R> void saveList(String path, List<T> values, Function<T, R> conversion) {
        List<R> conversions = new ArrayList<>();
        values.forEach(value -> conversions.add(conversion.apply(value)));
        configuration.set(path, conversions);
        saveConfiguration();
    }

    @Override
    public <T> List<T> loadList(String path, Function<String, T> conversion) {
        List<T> convertedList = new ArrayList<>();
        List<String> list = configuration.getStringList(path);
        list.forEach(value -> convertedList.add(conversion.apply(value)));
        return convertedList;
    }

    public <T> Map<String, T> loadMap(String path ,Function<String, T> conversion) {
        ConfigurationSection section = configuration.getConfigurationSection(path);
        Map<String, T> map = new HashMap<>();
        section.getKeys(false).forEach(key -> map.put(key, conversion.apply(key)));
        return map;
    }

    @Override
    public void save(IPreservable preservable) {
        preservable.save(this);
        saveConfiguration();
    }

    @Override
    public void load(IPreservable preservable) {
        preservable.load(this);
        saveConfiguration();
    }

    private void saveConfiguration() {
        if (defaultConfig) {
            plugin.saveConfig();
            return;
        }

        try {
            configuration.save(associatedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfiguration() {
        return defaultConfig ? plugin.getConfig() : YamlConfiguration.loadConfiguration(associatedFile);
    }

}

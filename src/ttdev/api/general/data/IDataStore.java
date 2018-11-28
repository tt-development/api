package ttdev.api.general.data;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface IDataStore {

    /**
     * Tells this data store to use a unique identifier at the
     * beginning of a path when saving a value. For example If you wanted
     * to save player specific data, you would use the players UUID as
     * the identifier
     *
     * @param identifier
     */
    void useIdentifier(String identifier);

    void removeIdentifier();

    /**
     * Use the specified file for storing data in this object.
     *
     * @param path
     */
    void useFile(String path);

    void saveString(String value, String path);

    String loadString(String path);

    void saveInteger(Integer value, String path);

    Integer loadInteger(String path);

    void saveShort(Short value, String path);

    Short loadShort(String path);

    void saveLong(Long value, String path);

    long loadLong(String path);

    void saveDouble(Double value, String path);

    Double loadDouble(String path);

    void saveObject(Object value, String path);

    Object loadObject(String path);

    <T, R> void saveList(String path, List<T> values, Function<T, R> conversion);

    <T> List<T> loadList(String path, Function<String, T> conversion);

    <K, V> Map<K, V> loadMap(String path, Function<String, K> keyParser, Function<String, V> valueParser);

    <T> void saveMap(String path, Map<String, T> map);

    void save(IPreservable preservable);

    void load(IPreservable preservable);

    /**
     * Get the underlying configuration object associated with this
     * data store. The only reason you would need to use this class woud be
     * to access configuration methods data store doesn't include.
     *
     * @return
     */
    FileConfiguration getConfiguration();

}

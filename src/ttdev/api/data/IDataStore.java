package ttdev.api.data;

public interface IDataStore {

    /**
     * Tells this data store to use a unique identifier at the
     * beginning of a path when saving a value. For example If you wanted
     * to save player specific data, you would use the players UUID as
     * the identifier
     * @param identifier
     */
    void useIdentifier(String identifier);

    void removeIdentifier();

    /**
     * Use the specified file for storing data in this object.
     * @param path
     */
    void useFile(String path);

    void saveString(String value, String path);

    String loadString(String path);

    void saveInteger(Integer value, String path);

    Integer loadInteger(String path);

    void saveDouble(Double value, String path);

    Double loadDouble(String path);

    void saveObject(Object value, String path);

    Object loadObject(String path);

    void save(IPreservable preservable);

    void load(IPreservable preservable);

}

package ttdev.api.general.data;

public interface IDataStore {

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

    void save(IPreservable preservable);

    void load(IPreservable preservable);

}

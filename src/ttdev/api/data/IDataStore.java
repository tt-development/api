package ttdev.api.data;

public interface IDataStore {

    void useFile(String path);

    void saveString(String value, String path);

    void saveInteger(Integer value, String path);

    void saveDouble(Double value, String path);

    void save(IPreservable preservable);

    void load(IPreservable preservable);

}
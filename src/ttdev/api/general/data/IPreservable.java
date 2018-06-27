package ttdev.api.general.data;

public interface IPreservable {

    /**
     * An abstract method for saving custom objects.
     * @param dataStore
     */
    void save(DataStore dataStore);

    /**
     * An abstract method for loading custom objects.
     * @param dataStore
     */
    void load(DataStore dataStore);

}

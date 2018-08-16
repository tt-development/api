package ttdev.api.datatype;

public class APair<K, V> {

    private K key;
    private V value;

    public APair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

}

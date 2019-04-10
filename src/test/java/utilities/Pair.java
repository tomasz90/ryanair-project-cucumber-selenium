package utilities;

public class Pair<K, V> {

    private final K properties;
    private final V path;

    public Pair(K properties, V path) {
        this.properties = properties;
        this.path = path;
    }

    public K getProperties() {
        return properties;
    }

    public V getPath() {
        return path;
    }

}

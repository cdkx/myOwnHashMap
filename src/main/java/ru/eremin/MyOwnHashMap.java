package ru.eremin;


public class MyOwnHashMap<K, V> {
    private final Object[] array = new Object[16];


    public void put(K key, V value) {
        Key<K> keyClass = new MyOwnHashMap.Key<>(key);
        var index = (array.length - 1) & keyClass.hash();

        if (index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        array[index] = value;
    }

    public V get(K key) {
        Key<K> keyClass = new MyOwnHashMap.Key<>(key);
        var index = (array.length - 1) & keyClass.hash();
        if (index >= array.length) {
            return null;
        }
        @SuppressWarnings("unchecked")
        V value = (V) array[index];
        return value;
    }

    public void remove(K key) {
        Key<K> keyClass = new MyOwnHashMap.Key<>(key);
        var index = (array.length - 1) & keyClass.hash();
        array[index] = null;
    }


    public static class Key<K> {
        private final K key;

        public Key(K key) {
            this.key = key;
        }

        public int hash() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }
}

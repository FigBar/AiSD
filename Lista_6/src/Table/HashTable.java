package Table;


import java.util.*;


public abstract class HashTable<K, V> implements Map<K, V> {

    int count = 0;

    Entry<?, ?>[] table;

    float threshold;
    float loadFactor;

    final int MAX_ARRAY_SIZE = 1000000;

    public HashTable(int initialCapacity, float loadFactor) {

        if (initialCapacity <= 0) {
            initialCapacity = 11;
        }
        if (loadFactor <= 0) {
            loadFactor = 0.75f;
        }

        table = new Entry<?, ?>[initialCapacity];
        this.loadFactor = loadFactor;
        this.threshold = table.length * loadFactor;

    }

    abstract Entry<K, V> findEntryByKey(K key);


    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsKey(Object key) {
        return (findEntryByKey((K) key) != null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        Entry<K, V> toGet = findEntryByKey((K) key);

        if (toGet != null) {
            return toGet.value;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> toPut : m.entrySet()) {
            put(toPut.getKey(), toPut.getValue());
        }

    }

    @Override
    public void clear() {

        table = new Entry<?, ?>[11];
        count = 0;
        threshold = (table.length * loadFactor);

    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {

        V value = get(key);

        if (value == null) {
            return defaultValue;
        } else {
            return value;
        }
    }


    @Override
    public V putIfAbsent(K key, V value) {

        if (value == null) {
            throw new NullPointerException();
        }

        V oldValue = get(key);

        if (oldValue == null) {
            put(key, value);
            return null;
        }
        return oldValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object key, Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        Entry<K, V> entry = findEntryByKey((K) key);
        if (entry != null) {
            if (entry.value.equals(value)) {
                remove(key);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        if (oldValue == null || newValue == null) {
            throw new NullPointerException();
        }

        Entry<K, V> entry = findEntryByKey(key);

        if (entry != null) {
            if (entry.value.equals(oldValue)) {
                entry.value = newValue;
                return true;
            }
        }
        return false;
    }

    @Override
    public V replace(K key, V value) {
        if (value == null) {
            throw new NullPointerException();
        }

        Entry<K, V> entry = findEntryByKey(key);

        if (entry == null) {
            return null;
        } else {
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }
    }

    static class Entry<K, V> implements Map.Entry<K, V> {

        int hash;
        K key;
        V value;

        Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }


        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            if (value == null) {
                throw new NullPointerException();
            }

            V oldValue = this.value;
            this.value = value;

            return oldValue;
        }
    }
}

package Table;

import java.util.*;

public class HashTableOA<K, V> extends HashTable<K, V> {

    public HashTableOA(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public HashTableOA(int initialCapacity) {
        super(initialCapacity, 0.75f);
    }

    public HashTableOA() {
        this(11);
    }

    @Override
    @SuppressWarnings("unchecked")
    Entry<K, V> findEntryByKey(K key) {
        int hash = key.hashCode();

        for (int i = 1; i <= table.length; i++) {
            int hashIndex = (Math.abs(hash) + i * i) % table.length;
            if (table[hashIndex] != null && (table[hashIndex].hash == hash) && (table[hashIndex].key.equals(key))) {
                return (Entry<K, V>) table[hashIndex];
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {

        if (table.length != MAX_ARRAY_SIZE) {

            int newSize = (int) Math.ceil(table.length * 1.1);

            if (newSize > MAX_ARRAY_SIZE) {
                newSize = MAX_ARRAY_SIZE;
            }

            Entry<?, ?>[] tempTable = new Entry<?, ?>[newSize];
            threshold = (int) (tempTable.length * loadFactor);

            for (Entry<K, V> copyEntry : (Entry<K, V>[]) table) {
                if (copyEntry != null) {
                    for (int j = 1; j <= table.length; j++) {
                        int hash = (Math.abs(copyEntry.hash) + j * j) % tempTable.length;
                        if (tempTable[hash] == null) {
                            tempTable[hash] = copyEntry;
                            break;
                        }
                    }
                }
            }
            table = tempTable;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            return false;
        }

        for (Entry<?, ?> entry : table) {
            if (entry != null && entry.value.equals(value))
                return true;
        }

        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        if (value == null) {
            throw new NullPointerException();
        }

        int hash = key.hashCode();

        for (int i = 1; i <= table.length; i++) {
            int index = (Math.abs(hash) + i * i) % table.length;

            Entry<K, V> entrySlot = (Entry<K, V>) table[index];

            if (entrySlot == null) {
                if (threshold <= count) {
                    rehash();
                }
                index = (Math.abs(hash) + i * i) % table.length;
                table[index] = new Entry<>(hash, key, value);
                count++;
                return null;
            } else if ((entrySlot.hash == hash) || (entrySlot.key.equals(key))) {
                V oldValue = entrySlot.value;
                entrySlot.value = value;
                return oldValue;
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        int hash = key.hashCode();

        for (int i = 1; i <= table.length; i++) {
            int index = (Math.abs(hash) + i * i) % table.length;
            Entry<K, V> entry = (Entry<K, V>) table[index];

            if (entry != null && entry.hash == hash && entry.key.equals(key)) {
                V oldValue = entry.value;
                table[index] = null;
                count--;
                return oldValue;
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<V> values() {
        if (isEmpty())
            return null;

        Collection<V> collection = new ArrayList<>();

        for (Entry<K, V> entry : (Entry<K, V>[]) table) {
            if (entry != null) {
                if (entry.value != null) {
                    collection.add(entry.value);
                }
            }
        }
        return collection;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<K> keySet() {
        if (isEmpty())
            return null;

        Set<K> set = new HashSet<>();
        for (Entry<K, V> entry : (Entry<K, V>[]) table) {
            if (entry != null) {
                if (entry.key != null) {
                    set.add(entry.key);
                }
            }
        }
        return set;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Map.Entry<K, V>> entrySet() {

        if (isEmpty())
            return null;

        Set<Map.Entry<K, V>> set = new HashSet();

        for (Entry<K, V> entry : (Entry<K, V>[]) table) {
            if (entry != null) {
                if (entry.key != null)
                    set.add(entry);
            }
        }

        return set;
    }
}

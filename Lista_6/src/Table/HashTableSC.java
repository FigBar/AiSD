package Table;

import java.util.*;

public class HashTableSC<K, V> extends HashTable<K, V> {

    public HashTableSC(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public HashTableSC(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public HashTableSC() {
        this(11);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Entry<K, V> findEntryByKey(K key) {

        int hash = key.hashCode();
        int hashIndex = Math.abs(hash) % table.length;

        for (EntrySC<K, V> searchedEntry = (EntrySC<K, V>) table[hashIndex]; searchedEntry != null; searchedEntry = searchedEntry.next) {
            if ((searchedEntry.hash == hash) && searchedEntry.key.equals(key)) {
                return searchedEntry;
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
                EntrySC<K, V> copySCEntry = (EntrySC<K, V>) copyEntry;

                for (; copySCEntry != null; copySCEntry = copySCEntry.next) {
                    int newIndex = Math.abs(copySCEntry.hash) % tempTable.length;
                    copySCEntry.next = (EntrySC<K, V>) tempTable[newIndex];
                    tempTable[newIndex] = copySCEntry;
                }
            }
            table = tempTable;
        }
    }

    @Override
    @SuppressWarnings("unchecked ")
    public boolean containsValue(Object value) {
        if (value == null) {
            return false;
        }

        for (Entry<?, ?> entry : table) {
            EntrySC<K, V> scEntry = (EntrySC<K, V>) entry;
            for (; scEntry != null; scEntry = scEntry.next) {
                if (scEntry.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException();
        }

        int hash = key.hashCode();
        int hashIndex = Math.abs(hash) % table.length;
        Entry<K, V> entry = findEntryByKey(key);

        if (entry != null) {
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        } else {
            if (threshold <= count) {
                rehash();
                hashIndex = Math.abs(hash) % table.length;
            }
            EntrySC<K, V> nextEntry = (EntrySC<K, V>) table[hashIndex];
            table[hashIndex] = new EntrySC<>(hash, key, value, nextEntry);
            count++;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        int hash = key.hashCode();
        int hashIndex = Math.abs(hash) % table.length;

        V removedValue = null;

        if (table[hashIndex] != null) {
            if (table[hashIndex].hash == hash && table[hashIndex].key.equals(key)) {
                removedValue = (V) table[hashIndex].value;
                table[hashIndex] = ((EntrySC<?, ?>) table[hashIndex]).next;
                count--;
            } else {
                EntrySC<K, V> prevEntry = (EntrySC<K, V>) table[hashIndex];
                for (EntrySC<K, V> entry = prevEntry.next; entry != null; prevEntry = entry, entry = entry.next) {
                    if (entry.hash == hash && entry.key.equals(key)) {
                        removedValue = entry.value;
                        prevEntry.next = entry.next;
                        count--;
                    }
                }
            }
        }
        return removedValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<K> keySet() {
        if (isEmpty()) {
            return null;
        }
        Set<K> set = new HashSet<>();

        for (Entry<K, V> entry : (Entry<K, V>[]) table) {
            EntrySC<K, V> scEntry = (EntrySC<K, V>) entry;
            for (; scEntry != null; scEntry = scEntry.next) {
                if (scEntry.key != null) {
                    set.add(scEntry.key);
                }
            }
        }
        return set;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<V> values() {

        if (isEmpty()) {
            return null;
        }
        Collection<V> collection = new ArrayList<>();

        for (Entry<K, V> entry : (Entry<K, V>[]) table) {
            EntrySC<K, V> scEntry = (EntrySC<K, V>) entry;
            for (; scEntry != null; scEntry = scEntry.next) {
                if (scEntry.value != null) {
                    collection.add(scEntry.value);
                }
            }
        }
        return collection;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Map.Entry<K, V>> entrySet() {

        if (isEmpty()) {
            return null;
        }
        Set<Map.Entry<K, V>> set = new HashSet<>();

        for (Entry<K, V> entry : (Entry<K, V>[]) table) {
            EntrySC<K, V> scEntry = (EntrySC<K, V>) entry;
            for (; scEntry != null; scEntry = scEntry.next) {
                set.add(scEntry);
            }
        }
        return set;

    }

    static class EntrySC<K, V> extends Entry<K, V> {

        EntrySC<K, V> next;

        EntrySC(int hash, K key, V value, EntrySC<K, V> next) {
            super(hash, key, value);
            this.next = next;
        }

    }
}

package Table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HashTableOATest {

    HashTableOA<Integer, String> tableOA;

    @BeforeEach
    void setUp() {
        tableOA = new HashTableOA<>();
    }

    @Test
    void containsValue() {

        assertFalse(tableOA.containsValue(null));
        assertFalse(tableOA.containsValue("Kasia"));
        tableOA.put(22, "Kasia");
        assertTrue(tableOA.containsValue("Kasia"));
    }

    @Test
    @SuppressWarnings("Duplicates")
    void put() {

        assertThrows(NullPointerException.class, () -> tableOA.put(25, null));
        assertThrows(NullPointerException.class, () -> tableOA.put(null, "abc"));

        assertNull(tableOA.put(25, "abc"));
        assertEquals("abc", tableOA.put(25,"def"));

        assertEquals(1, tableOA.size());

    }

    @Test
    @SuppressWarnings("Duplicates")
    void remove() {
        assertThrows(NullPointerException.class, () -> tableOA.remove(null));

        tableOA.put(22, "abc");
        tableOA.put(21,"def");

        assertNull(tableOA.remove(20));
        assertEquals(2, tableOA.size());
        assertEquals("abc", tableOA.remove(22));
        assertEquals("def", tableOA.remove(21));
        assertEquals(0, tableOA.size());

    }

    @Test
    @SuppressWarnings("Duplicates")
    void values() {
        assertNull(tableOA.values());

        tableOA.put(1, "Basia");
        tableOA.put(2, "Asia");
        tableOA.put(3, "Kasia");

        ArrayList<String> collection = new ArrayList<>();
        collection.add("Basia");
        collection.add("Asia");
        collection.add("Kasia");

        assertEquals(collection, tableOA.values());

    }

    @Test
    @SuppressWarnings("Duplicates")
    void keySet() {
        assertNull(tableOA.keySet());

        tableOA.put(1, "Basia");
        tableOA.put(2, "Asia");
        tableOA.put(3, "Kasia");

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        assertEquals(set, tableOA.keySet());

    }

    @Test
    @SuppressWarnings("Duplicates")
    void entrySet() {

        ArrayList<Integer> keys = new ArrayList<>();
        keys.add(1);
        keys.add(2);
        keys.add(3);

        ArrayList<String> values = new ArrayList<>();
        values.add("Basia");
        values.add("Asia");
        values.add("Kasia");

        tableOA.put(1, "Basia");
        tableOA.put(2, "Asia");
        tableOA.put(3, "Kasia");

        for (Map.Entry<Integer, String> entry : tableOA.entrySet()) {
            keys.remove(entry.getKey());
            values.remove(entry.getValue());
        }

        assertTrue(keys.isEmpty());
        assertTrue(values.isEmpty());

    }
}
package Table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HashTableSCTest {

    HashTableSC<Integer,String> tableSC;

    @BeforeEach
    void setUp() {
        tableSC = new HashTableSC<>();
    }

    @Test
    void containsValue() {

        assertFalse(tableSC.containsValue(null));
        assertFalse(tableSC.containsValue("Kasia"));
        tableSC.put(22, "Kasia");
        assertTrue(tableSC.containsValue("Kasia"));
    }

    @Test
    @SuppressWarnings("Duplicates")
    void put() {

        assertThrows(NullPointerException.class, () -> tableSC.put(25, null));
        assertThrows(NullPointerException.class, () -> tableSC.put(null, "abc"));

        assertNull(tableSC.put(25, "abc"));
        assertEquals("abc", tableSC.put(25,"def"));

        assertEquals(1, tableSC.size());

    }

    @Test
    @SuppressWarnings("Duplicates")
    void remove() {

        assertThrows(NullPointerException.class, () -> tableSC.remove(null));

        tableSC.put(22, "abc");
        tableSC.put(21,"def");

        assertNull(tableSC.remove(20));
        assertEquals(2, tableSC.size());
        assertEquals("abc", tableSC.remove(22));
        assertEquals("def", tableSC.remove(21));
        assertEquals(0, tableSC.size());
    }

    @Test
    @SuppressWarnings("Duplicates")
    void keySet() {
        assertNull(tableSC.keySet());

        tableSC.put(1, "Basia");
        tableSC.put(2, "Asia");
        tableSC.put(3, "Kasia");

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        assertEquals(set, tableSC.keySet());
    }

    @Test
    @SuppressWarnings("Duplicates")
    void values() {

        assertNull(tableSC.values());

        tableSC.put(1, "Basia");
        tableSC.put(2, "Asia");
        tableSC.put(3, "Kasia");

        ArrayList<String> collection = new ArrayList<>();
        collection.add("Basia");
        collection.add("Asia");
        collection.add("Kasia");

        assertEquals(collection, tableSC.values());
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

        tableSC.put(1, "Basia");
        tableSC.put(2, "Asia");
        tableSC.put(3, "Kasia");

        for (Map.Entry<Integer, String> entry : tableSC.entrySet()) {
            keys.remove(entry.getKey());
            values.remove(entry.getValue());
        }

        assertTrue(keys.isEmpty());
        assertTrue(values.isEmpty());
    }
}
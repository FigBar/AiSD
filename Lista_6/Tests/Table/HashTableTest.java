package Table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    HashTableOA<Integer, String> tableOA;
    HashTableSC<Integer, String> tableSC;


    @BeforeEach
    void setUp() {
        tableOA = new HashTableOA<>();
        tableSC = new HashTableSC<>();
    }

    @Test
    void containsKey() {

        assertThrows(NullPointerException.class, () -> tableOA.containsKey(null));

        assertFalse(tableOA.containsKey(5));
        assertFalse(tableOA.containsKey(10));

        tableOA.put(5, "Patryk");
        tableOA.put(10, "Mirek");

        assertTrue(tableOA.containsKey(5));
        assertTrue(tableOA.containsKey(10));

        assertThrows(NullPointerException.class, () -> tableSC.containsKey(null));

        assertFalse(tableSC.containsKey(5));
        assertFalse(tableSC.containsKey(10));

        tableOA.put(1, "Patryk");
        tableOA.put(20, "Mirek");

        assertTrue(tableOA.containsKey(1));
        assertTrue(tableOA.containsKey(20));
    }

    @Test
    void get() {

        assertThrows(NullPointerException.class, () -> tableOA.get(null));
        assertNull(tableOA.get(25));

        tableOA.put(25, "kotek");

        assertEquals("kotek", tableOA.get(25));

        assertThrows(NullPointerException.class, () -> tableSC.get(null));
        assertNull(tableSC.get(100));

        tableSC.put(100, "piesek");

        assertEquals("piesek", tableSC.get(100));

    }

    @Test
    void getOrDefault() {

        assertNull(tableOA.getOrDefault(15, null));
        assertThrows(NullPointerException.class, () -> tableOA.getOrDefault(null, "abc"));
        assertThrows(NullPointerException.class, () -> tableOA.getOrDefault(null, null));

        assertEquals("Tomek", tableOA.getOrDefault(15, "Tomek"));
        tableOA.put(15, "Asia");
        assertEquals("Asia", tableOA.getOrDefault(15, "Tomek"));

        assertNull(tableSC.getOrDefault(13, null));
        assertThrows(NullPointerException.class, () -> tableSC.getOrDefault(null, "abc"));
        assertThrows(NullPointerException.class, () -> tableSC.getOrDefault(null, null));

        assertEquals("Mirek", tableSC.getOrDefault(13, "Mirek"));
        tableSC.put(13, "Basia");
        assertEquals("Basia", tableSC.getOrDefault(13, "Mirek"));
    }

    @Test
    void putIfAbsent() {

        assertThrows(NullPointerException.class, () -> tableOA.putIfAbsent(25, null));
        assertThrows(NullPointerException.class, () -> tableOA.putIfAbsent(null, "abc"));
        assertThrows(NullPointerException.class, () -> tableOA.putIfAbsent(null, null));

        assertNull(tableOA.putIfAbsent(2, "def"));
        assertEquals("def", tableOA.putIfAbsent(2, "Kasia"));
        assertEquals("def", tableOA.get(2));


        assertThrows(NullPointerException.class, () -> tableSC.putIfAbsent(25, null));
        assertThrows(NullPointerException.class, () -> tableSC.putIfAbsent(null, "abc"));
        assertThrows(NullPointerException.class, () -> tableSC.putIfAbsent(null, null));

        assertNull(tableSC.putIfAbsent(2, "def"));
        assertEquals("def", tableSC.putIfAbsent(2, "Kasia"));
        assertEquals("def", tableSC.get(2));

    }

    @Test
    void remove() {
        assertThrows(NullPointerException.class, () -> tableOA.putIfAbsent(25, null));
        assertThrows(NullPointerException.class, () -> tableOA.putIfAbsent(null, "abc"));
        assertThrows(NullPointerException.class, () -> tableOA.putIfAbsent(null, null));

        tableOA.put(22, "abc");
        assertFalse(tableOA.remove(22, "cde"));
        assertFalse(tableOA.remove(21, "abc"));
        assertTrue(tableOA.remove(22, "abc"));
        assertNull(tableOA.get(22));


        assertThrows(NullPointerException.class, () -> tableSC.putIfAbsent(25, null));
        assertThrows(NullPointerException.class, () -> tableSC.putIfAbsent(null, "abc"));
        assertThrows(NullPointerException.class, () -> tableSC.putIfAbsent(null, null));

        tableSC.put(22, "abc");
        assertFalse(tableSC.remove(22, "cde"));
        assertFalse(tableSC.remove(21, "abc"));
        assertTrue(tableSC.remove(22, "abc"));
        assertNull(tableSC.get(22));

    }

    @Test
    void replace() {
        assertThrows(NullPointerException.class, () -> tableOA.replace(25, null, "abc"));
        assertThrows(NullPointerException.class, () -> tableOA.replace(null, "abc", "edf"));
        assertThrows(NullPointerException.class, () -> tableOA.replace(25, "abc", null));

        tableOA.put(14, "abc");
        assertFalse(tableOA.replace(14, "Asia", "def"));
        assertEquals("abc", tableOA.get(14));
        assertTrue(tableOA.replace(14, "abc", "def"));
        assertEquals("def", tableOA.get(14));

        assertThrows(NullPointerException.class, () -> tableSC.replace(25, null, "abc"));
        assertThrows(NullPointerException.class, () -> tableSC.replace(null, "abc", "edf"));
        assertThrows(NullPointerException.class, () -> tableSC.replace(25, "abc", null));

        tableSC.put(14, "abc");
        assertFalse(tableSC.replace(14, "Asia", "def"));
        assertEquals("abc", tableSC.get(14));
        assertTrue(tableSC.replace(14, "abc", "def"));
        assertEquals("def", tableSC.get(14));


    }

    @Test
    void replace1() {
        assertThrows(NullPointerException.class, () -> tableOA.replace(13, null));
        assertThrows(NullPointerException.class, () -> tableOA.replace(null, "abc"));

        assertNull(tableOA.replace(13, "abc"));
        tableOA.put(13, "Jan");
        assertEquals("Jan", tableOA.replace(13,"abc"));
        assertEquals("abc", tableOA.get(13));


        assertThrows(NullPointerException.class, () -> tableSC.replace(13, null));
        assertThrows(NullPointerException.class, () -> tableSC.replace(null, "abc"));

        assertNull(tableSC.replace(13, "abc"));
        tableSC.put(13, "Jan");
        assertEquals("Jan", tableSC.replace(13,"abc"));
        assertEquals("abc", tableSC.get(13));

    }

    @Test
    void labTest(){
        HashTableOA <String,Integer> table1 = new HashTableOA<>();
        HashTableSC <String, Integer> table2 = new HashTableSC<>();

        table1.put("Aa", 1);
        table1.put("Bb", 2);

        assertTrue(table1.containsKey("Aa"));
        assertTrue(table1.containsKey("Bb"));

        assertEquals((Integer) 1, table1.get("Aa"));
        assertEquals((Integer) 2, table1.get("Bb"));

        assertEquals((Integer) 2, table1.remove("Bb"));

        assertTrue(table1.containsKey("Aa"));
        assertFalse(table1.containsKey("Bb"));

        assertEquals((Integer) 1, table1.get("Aa"));
        assertEquals(null, table1.get("Bb"));


        table2.put("Aa", 1);
        table2.put("Bb", 2);

        assertTrue(table2.containsKey("Aa"));
        assertTrue(table2.containsKey("Bb"));

        assertEquals((Integer) 1, table2.get("Aa"));
        assertEquals((Integer) 2, table2.get("Bb"));

        assertEquals((Integer) 2, table2.remove("Bb"));

        assertTrue(table2.containsKey("Aa"));
        assertFalse(table2.containsKey("Bb"));

        assertEquals((Integer) 1, table2.get("Aa"));
        assertEquals(null, table2.get("Bb"));


    }
}
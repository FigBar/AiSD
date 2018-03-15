package Queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    private MyQueue<String> queue1;
    private String a, b, c, d, e;
    private ArrayList<String> addList;

    @BeforeEach
    void setUp() {
        queue1 = new MyStack();
        addList = new ArrayList<>();
        a = "A";
        b = "B";
        c = "C";
        d = "D";
        e = "E";
        addList.add(a);
        addList.add(b);
        addList.add(c);

    }

    @Test
    void contains() {
        queue1.add(a);
        queue1.add(b);
        c = null;
        assertTrue(queue1.contains(a));
        assertFalse(queue1.contains(d));
        assertThrows(NullPointerException.class, () -> queue1.contains(c));

    }

    @Test
    void toArray() {

        String[] array = {};
        assertArrayEquals(array, queue1.toArray());

        String[] array1 = {a, b, c};
        queue1.add(a);
        queue1.add(b);
        queue1.add(c);

        assertArrayEquals(array1, queue1.toArray());
    }

    @Test
    void toArray1() {
        String[] array1 = new String[3];
        queue1.add(a);
        queue1.add(b);
        queue1.add(c);
        queue1.add(d);
        array1 = queue1.toArray(array1);
        assertEquals(4, array1.length);

        Integer[] array2 = new Integer[3];

        assertThrows(NullPointerException.class, () -> queue1.toArray(null));
        assertThrows(ArrayStoreException.class, () -> queue1.toArray(array2));

        String[] array3 = {a, b, c, d};

        assertArrayEquals(array3, array1);
    }

    @Test
    void add() {
        assertTrue(queue1.add(a));

        assertThrows(NullPointerException.class, () -> queue1.add(null));

        queue1.add(c);
        assertEquals(2, queue1.size());
    }

    @Test
    void remove() {
        queue1.add(a);
        queue1.add(b);
        queue1.add(c);

        assertTrue(queue1.remove(b));
        assertEquals(2, queue1.size());

        assertFalse(queue1.remove(d));
        assertThrows(NullPointerException.class, () -> queue1.remove(null));
    }

    @Test
    void containsAll() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add(a);
        list1.add(b);
        list1.add(c);

        Integer jed = 1;
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(jed);

        assertFalse(queue1.containsAll(list2));


        queue1.add(a);
        queue1.add(b);

        assertFalse(queue1.containsAll(list1));

        queue1.add(c);

        assertTrue(queue1.containsAll(list1));

        queue1.add(d);
        assertTrue(queue1.containsAll(list1));

        assertThrows(NullPointerException.class, () -> queue1.contains(null));
    }

    @Test
    void addAll() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add(a);
        list1.add(b);
        list1.add(c);

        assertTrue(queue1.addAll(list1));

        assertEquals(3, queue1.size());

        list1.add(2, null);

        assertThrows(NullPointerException.class, () -> queue1.addAll(list1));

        assertThrows(NullPointerException.class, () -> queue1.addAll(null));
    }

    @Test
    void removeAll() {
        queue1.add(a);
        queue1.add(b);
        queue1.add(c);
        queue1.add(d);

        ArrayList<String> list1 = new ArrayList<>();
        list1.add(a);
        list1.add(b);
        list1.add(c);

        assertTrue(queue1.removeAll(list1));

        assertEquals(1, queue1.size());

        assertThrows(NullPointerException.class, () -> queue1.removeAll(null));

        list1.add(2, null);

        assertThrows(NullPointerException.class, () -> queue1.removeAll(list1));
    }

    @Test
    void retainAll() {
        queue1.add(a);
        queue1.add(b);
        queue1.add(c);
        queue1.add(d);

        ArrayList<String> list1 = new ArrayList<>();
        list1.add(a);
        list1.add(b);
        list1.add(c);

        assertTrue(queue1.retainAll(list1));
        assertEquals(3, queue1.size());

        assertFalse(queue1.retainAll(list1));

        assertThrows(NullPointerException.class, () -> queue1.retainAll(null));

        list1.add(2, null);
        assertThrows(NullPointerException.class, () -> queue1.retainAll(list1));
    }

    @Test
    void offer() {
        assertTrue(queue1.offer(a));

        assertEquals(1, queue1.size());

        assertThrows(NullPointerException.class, () -> queue1.offer(null));

    }
}
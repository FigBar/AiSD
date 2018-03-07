package Queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FifoQueueTest {
    FifoQueue<String> queue1;
    String a, b, c, d, e;

    @BeforeEach
    void setUp() {
        queue1 = new FifoQueue<>();
        a = "A";
        b = "B";
        c = "C";
        d = "D";
        e = "E";
    }

    @Test
    @SuppressWarnings("Duplicates")
    void remove() {
        assertThrows(NoSuchElementException.class, () -> queue1.remove());
        queue1.add(a);
        queue1.add(b);
        queue1.add(c);

        assertEquals(a, queue1.remove());
        assertEquals(2, queue1.size());

        assertEquals(b, queue1.remove());
        assertEquals(c, queue1.remove());

        assertThrows(NoSuchElementException.class, () -> queue1.remove());
    }

    @Test
    @SuppressWarnings("Duplicates")
    void poll() {
        assertEquals(null, queue1.poll());
        queue1.add(a);
        queue1.add(b);
        queue1.add(c);

        assertEquals(a, queue1.poll());
        assertEquals(2, queue1.size());

        assertEquals(b, queue1.poll());
        assertEquals(c, queue1.poll());

        assertEquals(null, queue1.poll());


    }

    @Test
    @SuppressWarnings("Duplicates")
    void element() {
        assertThrows(NoSuchElementException.class, () -> queue1.element());
        queue1.add(a);
        queue1.add(b);
        queue1.add(c);

        assertEquals(a, queue1.element());
        assertEquals(3, queue1.size());


    }

    @Test
    @SuppressWarnings("Duplicates")
    void peek() {
        assertEquals(null, queue1.peek());
        queue1.add(a);
        queue1.add(b);
        queue1.add(c);

        assertEquals(a, queue1.peek());
        assertEquals(3, queue1.size());
    }
}
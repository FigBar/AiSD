package Queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {
    private MyStack<String> myStack;
    private String a, b, c, d, e;

    @BeforeEach
    void setUp() {
        myStack = new MyStack<>();
        a = "A";
        b = "B";
        c = "C";
        d = "D";
        e = "E";
    }

    @Test
    @SuppressWarnings("Duplicates")
    void remove() {
        assertThrows(NoSuchElementException.class, () -> myStack.remove());
        myStack.add(c);
        myStack.add(d);
        myStack.add(e);

        assertEquals(e, myStack.remove());
        assertEquals(2, myStack.size());

        assertEquals(d, myStack.remove());
        assertEquals(c, myStack.remove());

        assertThrows(NoSuchElementException.class, () -> myStack.remove());
    }

    @Test
    @SuppressWarnings("Duplicates")
    void poll() {
        assertEquals(null, myStack.poll());
        myStack.add(a);
        myStack.add(b);
        myStack.add(c);

        assertEquals(c, myStack.poll());
        assertEquals(2, myStack.size());

        assertEquals(b, myStack.poll());
        assertEquals(a, myStack.poll());

        assertEquals(null, myStack.poll());
    }

    @Test
    @SuppressWarnings("Duplicates")
    void element() {
        assertThrows(NoSuchElementException.class, () -> myStack.element());
        myStack.add(a);
        myStack.add(b);
        myStack.add(c);

        assertEquals(c, myStack.element());
        assertEquals(3, myStack.size());


    }

    @Test
    @SuppressWarnings("Duplicates")
    void peek() {
        assertEquals(null, myStack.peek());
        myStack.add(a);
        myStack.add(b);
        myStack.add(c);

        assertEquals(c, myStack.peek());
        assertEquals(3, myStack.size());
    }
}
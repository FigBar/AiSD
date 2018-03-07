package Queues;


import java.util.NoSuchElementException;

public class FifoQueue<E> extends MyQueue {
    public FifoQueue() {
        super();
    }

    private E unlinkFirst(Node<E> f) {
        E unlinked = f.current;
        Node<E> next = f.next;

        f.current = null;
        f.next = null;

        first = next;

        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return unlinked;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove() {

        if (isEmpty())
            throw new NoSuchElementException();
        else
            return unlinkFirst((Node<E>) first);

    }

    @Override
    @SuppressWarnings("unchecked")
    public E poll() {
        if (isEmpty())
            return null;
        else
            return unlinkFirst((Node<E>) first);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E element() {
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return (E)first.current;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty())
            return null;
        else
            return (E)first.current;
    }
}

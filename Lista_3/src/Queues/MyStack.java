package Queues;

import java.util.NoSuchElementException;

public class MyStack<E> extends MyQueue {

    public MyStack() {
        super();
    }

    private E unlinkLast(Node<E> l) {
        E unlinked = l.current;
        Node<E> prev = l.prev;

        l.current = null;
        l.prev = null;

        last = prev;

        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
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
            return unlinkLast((Node<E>) last);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E poll() {
        if (isEmpty())
            return null;
        else
            return unlinkLast((Node<E>) last);

    }

    @Override
    @SuppressWarnings("unchecked")
    public E element() {
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return (E) last.current;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty())
            return null;
        else
            return (E) last.current;
    }
}

package Queues;


import java.util.*;

public abstract class MyQueue<E> implements Queue<E> {
    protected int size = 0;

    protected Node<E> first;

    protected Node<E> last;


    @SuppressWarnings("unchecked")
    private void linkFirst(E element) {
        Node<E> head = first;
        Node<E> newNode = new Node(null, element, head);
        first = newNode;
        if (head == null)
            last = newNode;
        else
            head.prev = newNode;
        size++;
    }

    @SuppressWarnings("unchecked")
    private void linkLast(E element) {
        Node<E> tail = last;
        Node<E> newNode = new Node(tail, element, null);
        last = newNode;
        if (tail == null)
            first = newNode;
        else
            tail.next = newNode;
        size++;
    }

    private Node<E> findNode(E element) {
        Node<E> searched = null;

        for (Node<E> i = first; i != null; i = i.next) {
            if (i.current.equals(element)) {
                searched = i;
            }
        }
        return searched;
    }

    private void unlink(Node<E> n) {
        Node<E> next = n.next;
        Node<E> prev = n.prev;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            n.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            n.next = null;
        }
        n.current = null;
        size--;

    }

    @Override
    public int size() {
        return size;
    }

    public int indexOf(E e) {

        int index = 0;
        if (e == null) {
            throw new NullPointerException();
        } else {
            for (Node<E> i = first; i != null; i = i.next) {
                if (e.equals(i.current))
                    return index;
                index++;
            }
        }
        return -1;
    }


    @Override
    public boolean isEmpty() {
        return (first == last && first == null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        E element = (E) o;
        return indexOf(element) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyQueueIterator<>(this);
    }

    @Override
    public Object[] toArray() {
        Object[] anArray = new Object[size];
        int index = 0;
        if (isEmpty()) {
            return anArray;
        } else {
            for (Node<E> i = first; i != null; i = i.next) {
                anArray[index] = i.current;
                index++;
            }
            return anArray;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a == null)
            throw new NullPointerException();

        if (size > a.length) {
            a = Arrays.copyOf(a, size);
        }

            E[] queueArray = (E[]) this.toArray();
            System.arraycopy(queueArray, 0, a, 0, size);


        return a;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException();
        } else {
            linkLast(e);
            return true;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException();
        } else {
            E element = (E) o;
            if(contains(o)){
                unlink(findNode(element));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int counter = 0;

        Object[] array;
        array = c.toArray();
        if(c == null){
            throw new NullPointerException();
        } else {
           for(Object o : array) {
               if(contains(o))
                   counter++;
            }
        }
        return (counter == c.size());
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(c == null || c.contains(null)){
            throw new NullPointerException();
        }else{
            for(E element : c){
                add(element);
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int counter = size;

        Object[] array;
        array = c.toArray();
        if(c == null){
            throw new NullPointerException();
        } else {
            for(Object o : array) {
                    remove(o);
            }
        }
        return (counter != size);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int counter = size;

        if(c == null || c.contains(null)){
            throw new NullPointerException();
        } else {
            for(Node<E> i = first; i!= null; i = i.next){
                if (!c.contains(i.current))
                    unlink(i);
            }

        }
        return (counter != size);
    }

    @Override
    public void clear() {
        for (Node<E> i = first; i != null; i = i.next) {
            i.prev = null;
            i.current = null;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    private static class MyQueueIterator<E> implements Iterator<E>{

        private int size;
        private Node<E> element;

        private int position = 1;

        private MyQueueIterator(MyQueue<E> queue){
            size = queue.size();
            element= queue.first;

        }

        @Override
        public boolean hasNext() {
            return (position <= size);
        }

        @Override
        public E next() throws NoSuchElementException {
            E current;
            if(hasNext()) {
                current = element.current;
                element = element.next;
                position++;
                return current;
            }else
                throw new NoSuchElementException();
        }

        @Override
        public void remove() throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }
    }

    protected static class Node<E> {
        E current;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E current, Node<E> next) {
            this.prev = prev;
            this.current = current;
            this.next = next;
        }
    }
}

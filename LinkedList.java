import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO JAVADOC
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private int size;

    // TODO JAVADOC
    public LinkedList() {
        head = null;
        size = 0;
    }

    // TODO JAVADOC
    public LinkedList(T[] data) {
        for (T datum : data) {
            add(datum); // keep adding to end
        }
    }

    // TODO JAVADOC
    public Node<T> getHead() {
        return head;
    }

    // TODO JAVADOC
    public T[] toArray() {
        return null; // FIXME
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("===== LINKEDLIST %d =====\nisEmpty: %s\nsize: %d\nhead: %s\ndata: [",
                        hashCode(),
                        isEmpty(),
                        size(),
                        (head == null ? "null" : head.getData())));

        T[] data = toArray();
        if (data == null) {
            sb.append("TODO: Implement toArray method...");
        } else {
            for (int i = 0; i < data.length - 1; ++i) {
                sb.append(String.format("%s, ", data[i])); // append all but last value
            }
            if (data.length > 0) {
                sb.append(String.format("%s", data[data.length - 1])); // append last value
            }
        }
        sb.append("]\n============================");
        return sb.toString();
    }

    @Override
    public void add(T element) throws IllegalArgumentException {
        if head.getData()
    }

    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {
        // FIXME
    }

    @Override
    public T remove() throws NoSuchElementException {
        return null; // FIXME
    }

    @Override
    public T remove(int index) throws NoSuchElementException, IndexOutOfBoundsException {
        return null; // FIXME
    }

    @Override
    public T remove(T element) throws IllegalArgumentException, NoSuchElementException {
        return null; // FIXME
    }

    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {
        return null; // FIXME
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        return null; // FIXME
    }

    @Override
    public boolean contains(T element) throws IllegalArgumentException {
        return false; // FIXME
    }

    @Override
    public void clear() {
        // FIXME
    }

    @Override
    public boolean isEmpty() {
        return false; // FIXME
    }

    @Override
    public int size() {
        return 0; // FIXME
    }

    @Override
    public Iterator<T> iterator() {
        return null; // FIXME
    }
}
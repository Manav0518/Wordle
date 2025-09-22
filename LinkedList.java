import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple generic singly linked list implementation.
 *
 * @param <T> the type of elements in this list
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private int size;

    /**
     * Constructs an empty LinkedList.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Constructs a LinkedList from an array of elements.
     *
     * @param data the array of elements to insert into the list
     */
    public LinkedList(T[] data) {
        for (T datum : data) {
            add(datum);
        }
    }

    /**
     * Returns the head node of the list.
     *
     * @return the head node, or null if the list is empty
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Converts this linked list into an array.
     *
     * @return an array containing all elements in the list
     */
    public T[] toArray() {
        // TODO: Implement this
        return null;
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
                sb.append(String.format("%s, ", data[i]));
            }
            if (data.length > 0) {
                sb.append(String.format("%s", data[data.length - 1]));
            }
        }
        sb.append("]\n============================");
        return sb.toString();
    }

    /**
     * Appends the specified element to the end of the list.
     *
     * @param element element to be appended
     * @throws IllegalArgumentException if the element is null
     */
    @Override
    public void add(T element) throws IllegalArgumentException {
        // TODO: Implement this
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index   index at which the element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws IllegalArgumentException  if the element is null
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, IllegalArgumentException {
        // TODO: Implement this
    }

    /**
     * Removes and returns the first element from the list.
     *
     * @return the removed element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T remove() throws NoSuchElementException {
        // TODO: Implement this
        return null;
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index index of the element to remove
     * @return the removed element
     * @throws NoSuchElementException    if the list is empty
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @Override
    public T remove(int index)
            throws NoSuchElementException, IndexOutOfBoundsException {
        // TODO: Implement this
        return null;
    }

    /**
     * Removes the first occurrence of the specified element from this list.
     *
     * @param element the element to remove
     * @return the removed element
     * @throws IllegalArgumentException if the element is null
     * @throws NoSuchElementException   if the element is not found
     */
    @Override
    public T remove(T element)
            throws IllegalArgumentException, NoSuchElementException {
        // TODO: Implement this
        return null;
    }

    /**
     * Replaces the element at the specified position with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws IllegalArgumentException  if the element is null
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, IllegalArgumentException {
        // TODO: Implement this
        return null;
    }

    /**
     * Returns the element at the specified position.
     *
     * @param index index of the element to return
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // TODO: Implement this
        return null;
    }

    /**
     * Checks whether the list contains the specified element.
     *
     * @param element element whose presence is to be tested
     * @return true if the list contains the element, false otherwise
     * @throws IllegalArgumentException if the element is null
     */
    @Override
    public boolean contains(T element) throws IllegalArgumentException {
        // TODO: Implement this
        return false;
    }

    /**
     * Removes all elements from the list.
     */
    @Override
    public void clear() {
        // TODO: Implement this
    }

    /**
     * Returns true if the list contains no elements.
     *
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        // TODO: Implement this
        return false;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return size of the list
     */
    @Override
    public int size() {
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an iterator
     */
    @Override
    public Iterator<T> iterator() {
        // TODO: Implement this
        return null;
    }
}

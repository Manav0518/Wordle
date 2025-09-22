import java.util.NoSuchElementException;

/**
 * List Abstract Data Type.
 *
 * @author CS1331 TAs
 * @version 13.31
 * @param <E> the type of elements in this list
 */
public interface List<E> extends Iterable<E> {
    /**
     * Inserts the element at the end of the list.
     *
     * @param element the element we are adding to the list
     * @throws IllegalArgumentException if the passed in element is null
     */
    void add(E element) throws IllegalArgumentException;

    /**
     * Inserts the element at the specified index of the list.
     * If an element exists at that index, that element in the list should come after the new element being added.
     *
     * @param index   the index to add the element at
     * @param element the element we are adding to the list
     * @throws IndexOutOfBoundsException if the passed in index is invalid. index == size() is valid.
     *                                   In the event both arguments are invalid, this exception should be thrown.
     * @throws IllegalArgumentException if the passed in element is null
     */
    void add(int index, E element) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * Removes the element at the front of the list and returns it.
     *
     * @return the removed element from the front of the list
     * @throws NoSuchElementException if the list is empty
     */
    E remove() throws NoSuchElementException;

    /**
     * Removes the element at the specified index and returns it.
     *
     * @param index the index of the element to be removed
     * @return the removed element at the specified index of the list
     * @throws NoSuchElementException    if the list is empty. In the event that the list is empty and
     *                                   the passed in index is invalid, this exception should be thrown.
     * @throws IndexOutOfBoundsException if the passed in index is invalid
     */
    E remove(int index) throws NoSuchElementException, IndexOutOfBoundsException;

    /**
     * Removes the first occurrence of the specified element from the list and returns it.
     * The first occurrence of the specified element is the first element in the list
     * that is equal to the passed in element according to how the equals method is defined.
     *
     * @param element the element to be removed
     * @return the element that is removed from the list. Do not return the passed in element!
     * @throws IllegalArgumentException if the passed in element is null
     * @throws NoSuchElementException   if the passed in element is not in the list. If the passed in element
     *                                  is null, an IllegalArgumentException should be thrown instead.
     */
    E remove(E element) throws IllegalArgumentException, NoSuchElementException;

    /**
     * Replaces the element at a specific index with the passed in element.
     *
     * @param index   the index of the element to be replaced
     * @param element the element that should replace the existing element at the passed in index
     * @return the element that was replaced
     * @throws IndexOutOfBoundsException if the passed in index is invalid.
     *                                   In the event both arguments are invalid, this exception should be thrown.
     * @throws IllegalArgumentException  if the passed in element is null
     */
    E set(int index, E element) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * Returns the element at the specified index.
     *
     * You must implement this method by explicitly using an iterator
     * (i.e., by explicitly utilizing Iterator's hasNext() and next() methods).
     * Do not use a for-each loop.
     *
     * @param index the index of the element to get
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the passed in index is invalid
     */
    E get(int index) throws IndexOutOfBoundsException;

    /**
     * Checks if the list contains the specified element.
     * The list contains the specified element if there exists at least one element in the list
     * that is equal to the passed in element according to how the equals method is defined.
     *
     * You must implement this method by explicitly using an iterator
     * (i.e., by explicitly utilizing Iterator's hasNext() and next() methods).
     * Do not use a for-each loop.
     *
     * @param element the element to search for in the list
     * @return whether the list contains the element
     * @throws IllegalArgumentException if the passed in element is null
     */
    boolean contains(E element) throws IllegalArgumentException;

    /**
     * Clears the list.
     */
    void clear();

    /**
     * Checks if the list is empty.
     *
     * @return whether the list is empty
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    int size();
}
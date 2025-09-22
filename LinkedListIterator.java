import java.util.Iterator;
import java.util.NoSuchElementException;
public class LinkedListIterator<T> implements Iterator<T>{
    private Node<T> next;
    public LinkedListIterator(LinkedList<T> linkedList) {
        if(linkedList == null){
            throw new IllegalArgumentException("List cant be null");
        }
        this.next = linkedList.getHead();
        }
    public boolean hasNext(){
        if(next!=null){
            return true;
        }
        return false;
    }
    public T next(){
        if(hasNext()){
            T data = next.getData();
            next = next.getNext();
            return data;
        }
        throw new NoSuchElementException("There are no more nodes left to iterate over");
    }

}
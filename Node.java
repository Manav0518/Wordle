public class Node<T>{
    T data;
    Node<T> next;
    Node(T data, Node<T> next){
        if(data == null){
            throw new IllegalArgumentException("The data is null");
        }
        this.data = data;
        this.next = next;
    }
    Node(T data){
        this(data, null);
    }
    public T getData(){
        return data;
    }
    public Node<T> getNext(){
        return next;
    }
    public void setNext(Node<T> next){
        this.next = next;
    }
    public void setData(T data){
        if(data == null){
            throw new IllegalArgumentException("This data is null");
        }
        this.data = data;
    }

}
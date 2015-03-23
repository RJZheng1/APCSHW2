import java.util.*;

public class MyStack<T>{
    MyLinkedList<T> top;
    
    public MyStack(){
	top = new MyLinkedList<T>();
    }

    public boolean empty(){
	return top.size() == 0;
    }

    public T peek(){
	return top.get(0);
    }

    public T pop(){
	return top.remove(0);
    }

    public T push(T item){
	top.add(0,item);
	return item;
    }
}
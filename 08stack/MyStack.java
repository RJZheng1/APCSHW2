import java.util.*;

public class MyStack<T>{
    LNode<T> top;
    
    public MyStack(){
	top = new LNode<T>();
    }

    public boolean empty(){
	return top == null;
    }

    public T peek(){
	if(empty())
	    throw new EmptyStackException();
	else
	    return top.getValue();
    }

    public T pop(){
	if(empty())
	    throw new EmptyStackException();
	else{
	    T old = top.getValue();
	    top = top.getNext();
	    return old;
	}
    }

    public T push(T item){
	top = new LNode<T>(item,top);
	return item;
    }
}
public class MyQueue<T>{
    MyLinkedList<T> top;

    public MyQueue(){
	top = new MyLinkedList<T>();
    }

    public boolean enqueue(T value){
	top.add(value);
	return true;
    }

    public T dequeue(){
	return top.remove(0,value);
    }
}
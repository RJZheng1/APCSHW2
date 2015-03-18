import java.util.*;

public class MyLinkedList<T> implements Iterable<T>{
    private LNode<T> first,last;
    private int size;

    public MyLinkedList(){
	first = new LNode<T>();
	last = first;
	size = 0;
    }

    public boolean add(T value){
	last.setNext(new LNode<T>(value));
	last = last.getNext();
	size++;
	return true;
    }

    public void add(int index, T value){
	if(index < 0 || index > size())
	    throw new IndexOutOfBoundsException();
	LNode<T> now = first;
	for(int i = 0;i < index;i++)
	    now = now.getNext();
	now.setNext(new LNode<T>(value,now.getNext()));
	size++;
    }

    public T get(int index){
	if(index < 0 || index >= size())
	    throw new IndexOutOfBoundsException();
	LNode<T> now = first.getNext();
	for(int i = 0;i < index;i++)
	    now = now.getNext();
	return now.getValue();
    }

    public int indexOf(T element){
	LNode<T> now = first.getNext();
	for(int i = 0;now.getNext() != null;i++){
	    if(now.getValue().equals(element))
		return i;
	    now = now.getNext();
	}
	return -1;
    }

    public Iterator<T> iterator(){
	return new MyLLIterator();
    }

    public String name(){
	return "zheng.rijiu";
    }

    public T remove(int index){
	if(index < 0 || index >= size())
	    throw new IndexOutOfBoundsException();
	LNode<T> now = first;
	for(int i = 0;i < index;i ++)
	    now = now.getNext();
	T old = now.getNext().getValue();
	now.setNext(now.getNext().getNext());
	size--;
	return old;
    }

    public T set(int index, T value){
	if(index < 0 || index >= size())
	    throw new IndexOutOfBoundsException();
	LNode<T> now = first.getNext();
	for(int i = 0;i < index;i++)
	    now = now.getNext();
	T old = now.getValue();
	now.setValue(value);
	return old;
    }

    public int size(){
	return size;
    }

    public String toString(){
	String result = "[ ";
	LNode now = first.getNext();
	while(now.getNext() != null){
	    result += now + ", ";
	    now = now.getNext();
	}
	result += (now.toString() + " ]");
	return result;
    }

    private class MyLLIterator implements Iterator<T>{
	LNode<T> curr;
	public MyLLIterator(){
	    curr = first.getNext();
	}
	public boolean hasNext(){
	    return curr != null;
	}
	public T next(){
	    T res = curr.getValue();
	    curr = curr.getNext();
	    return res;
	}
	public void remove(){
	    throw new UnsupportedOperationException();
	}
    }

    public static void main(String[] args){
	MyLinkedList<Integer> l = new MyLinkedList<Integer>();
	l.add(new Integer(2));
	l.add(new Integer(6));
	l.add(new Integer(9));
	l.add(1,new Integer(4));
	l.add(4,new Integer(5));
	System.out.println(l);
	System.out.println(l.get(0));
	System.out.println(l.set(0,new Integer(1)));
	System.out.println(l);
	System.out.println(l.remove(1));
	System.out.println(l);
	System.out.println(l.indexOf(new Integer(6)));
	System.out.println(l.indexOf(new Integer(53)));
	System.out.println(l.size());
	for(Integer i:l)
	    System.out.println(i);
    }
}
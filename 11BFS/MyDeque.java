import java.util.*;

public class MyDeque<T>{
    private Object[] stuffs;
    private int[] priorities;
    private int start,end,size;

    public MyDeque(){
	stuffs = new Object[8];
	priorities = new int[8];
	start = 0;
	end = 1;
	size = 0;
    }

    public void add(T value, int priority){
	if(size == stuffs.length)
	    resize();
	if(start < 0){
	    start = stuffs.length - 1;
	}
	stuffs[start] = (Object) value;
	priorities[start--] = priority;
	size++;
    }
    
    public void addFirst(T value){
	if(size == stuffs.length)
	    resize();
	if(start < 0)
	    start = stuffs.length - 1;
	stuffs[start--] = (Object)value;
	size++;
    }

    public void addLast(T value){
	if(size == stuffs.length)
	    resize();
	if(end == stuffs.length)
	    end = 0;
	stuffs[end++] = (Object)value;
	size++;
    }

    public T getFirst(){
	if(size == 0)
	    throw new NoSuchElementException();
	return (T)stuffs[start+1];
    }

    public T getLast(){
	if(size == 0)
	    throw new NoSuchElementException();
	return (T)stuffs[end-1];
    }

    public T removeFirst(){
	if(size == 0)
	    throw new NoSuchElementException();
	T old = (T)stuffs[++start];
	size--;
	return old;
    }

    public T removeLast(){
	if(size == 0)
	    throw new NoSuchElementException();
	T old = (T)stuffs[--end];
	size--;
	return old;
    }

    public T removeSmallest(){
	int x = head;
	for(int n = head+1;n != tail;n++){
	    if(n == stuffs.length)
		n = 0;
	    if(priorities[x] > priorities[n])
		x = n;
	}
	T result = stuffs[head];
	for(int n = x;n != tail;n++){
	}
    }

    public String toString(){
	String result = "[ ";
	int n = start + 1;
	while(n != end - 1){
	    if(n == stuffs.length)
		n = 0;
	    result += stuffs[n++].toString() + " ";
	}
	result += stuffs[n].toString() + " ]";
	return result;
    }

    private void resize(){
	Object[] newone = new Object[stuffs.length*2];
	int x = 0;
	int n = start + 1;
	while(n != end - 1){
	    if(n == stuffs.length)
		n = 0;
	    newone[x++] = stuffs[n++];
	}
	newone[x] = stuffs[n];
	start = newone.length - 1;
	end = stuffs.length;
	stuffs = newone;
    }

    public static void main(String[] args){
    }
}
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
	if(end == stuffs.length)
	    end = 0;
	stuffs[end] = (Object)value;
	priorities[end++] = priority;
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

    public boolean isEmpty(){
	return size == 0;
    }

    public T removeFirst(){
	if(size == 0)
	    throw new NoSuchElementException();
	T old = (T)stuffs[++start % stuffs.length];
	size--;
	return old;
    }

    public T removeLast(){
	if(size == 0)
	    throw new NoSuchElementException();
	T old = (T)stuffs[--end % stuffs.length];
	size--;
	return old;
    }
    
    public T removeSmallest(){
	if(size == 0)
	    throw new NoSuchElementException();
	int x = (start + 1) % stuffs.length;
	for(int n = (start + 1) % stuffs.length;n % stuffs.length != (end) % stuffs.length;n++){
	    if(priorities[x] > priorities[n % stuffs.length])
		x = n % stuffs.length;
	}
	T result = (T)stuffs[x];
	for(int n = x;n % stuffs.length != (end - 1) % stuffs.length;n++){
	    stuffs[n % stuffs.length] = stuffs[(n+1) % stuffs.length];
	    priorities[n % stuffs.length] = priorities[(n+1) % stuffs.length];
	}
	end--;
	size--;
	return result;
    }

    public String toString(){
	if(size == 0)
	    return "[ ]";
	String result = "[ ";
	int n = start + 1;
	while(n % stuffs.length != (end - 1) % stuffs.length){
	    result += stuffs[n++ % stuffs.length].toString() + ", ";
	}
	result += stuffs[n % stuffs.length].toString() + " ]";
	return result;
    }

    private void resize(){
	Object[] newone = new Object[stuffs.length*2];
	int x = 0;
	int n = start + 1;
	while(n % stuffs.length != (end - 1) % stuffs.length)
	    newone[x++] = stuffs[n++ % stuffs.length];
	newone[x] = stuffs[n % stuffs.length];
	start = newone.length - 1;
	end = stuffs.length;
	stuffs = newone;
    }

    public static void main(String[] args){
	MyDeque<String> MDQ = new MyDeque<String>();
	MDQ.add("FIRST",1);
	MDQ.add("THIRD",3);
	MDQ.add("SECOND",2);
	MDQ.add("FIFTH",5);
	MDQ.add("FOURTH",4);
	System.out.println(MDQ);
	MDQ.removeSmallest();
	System.out.println(MDQ);
	MDQ.removeSmallest();
	System.out.println(MDQ);
	MDQ.removeSmallest();
	System.out.println(MDQ);
	MDQ.removeSmallest();
	System.out.println(MDQ);
	MDQ.removeSmallest();
	System.out.println(MDQ);
    }
}

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
	stuffs[end] = (Object)value;
	priorities[end++] = priority;
	if(end == stuffs.length)
	    end = 0;
	size++;
    }
    
    public void addFirst(T value){
	if(size == stuffs.length)
	    resize();
	stuffs[start--] = (Object)value;
	if(start < 0)
	    start = stuffs.length - 1;
	size++;
    }

    public void addLast(T value){
	if(size == stuffs.length)
	    resize();
	stuffs[end++] = (Object)value;
	if(end == stuffs.length)
	    end = 0;
	size++;
    }

    public T getFirst(){
	if(size == 0)
	    throw new NoSuchElementException();
	return (T)stuffs[(start + 1) % stuffs.length];
    }

    public T getLast(){
	if(size == 0)
	    throw new NoSuchElementException();
	return (T)stuffs[(end - 1 + stuffs.length) % stuffs.length];
    }

    public boolean isEmpty(){
	return size == 0;
    }

    public T removeFirst(){
	if(size == 0)
	    throw new NoSuchElementException();
	if(start == stuffs.length - 1)
	    start = -1;
	T old = (T)stuffs[++start];
	size--;
	return old;
    }

    public T removeLast(){
	if(size == 0)
	    throw new NoSuchElementException();
	if(end == 0)
	    end = stuffs.length;
	T old = (T)stuffs[--end];
	size--;
	return old;
    }
    
    public T removeSmallest(){
	if(size == 0)
	    throw new NoSuchElementException();
	int x = (start + 1) % stuffs.length;
	for(int n = start + 1;n % stuffs.length != end % stuffs.length;n++){
	    if(priorities[x] > priorities[n % stuffs.length])
		x = n % stuffs.length;
	}
	T result = (T)stuffs[x];
	if(end == 0)
	    end = stuffs.length;
	stuffs[x] = stuffs[--end % stuffs.length];
	priorities[x] = priorities[end % stuffs.length];
	size--;
	return result;
    }

    public String toString(){
	if(size == 0)
	    return "[ ]";
	String result = "[ ";
	int n = start + 1;
	while(n % stuffs.length != (end - 1 + stuffs.length) % stuffs.length)
	    result += stuffs[n++ % stuffs.length].toString() + ", ";	
	result += stuffs[n % stuffs.length].toString() + " ]";
	return result;
    }

    private void resize(){
	Object[] newone = new Object[stuffs.length*2];
	int[] newpri = new int[priorities.length*2];
	int x = 0;
	int n = start + 1;
	while(n % stuffs.length != (end - 1 + stuffs.length) % stuffs.length){
	    newone[x] = stuffs[n % stuffs.length];
	    if(priorities.length != 0)
		newpri[x++] = priorities[n++ % stuffs.length];
	}
	newone[x] = stuffs[n % stuffs.length];
	newpri[x] = priorities[n % priorities.length];
	start = newone.length - 1;
	end = stuffs.length;
	stuffs = newone;
	priorities = newpri;
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

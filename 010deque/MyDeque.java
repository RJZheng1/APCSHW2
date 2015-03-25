import java.util.*;

public class MyDeque<T>{
    private Object[] stuffs;
    private int start,end,size;

    public MyDeque(){
	stuffs = new Object[8];
	start = 0;
	end = 1;
	size = 0;
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

    public String toString(){
	return Arrays.toString(stuffs);
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
	MyDeque<String> MDQ = new MyDeque<String>();
	MDQ.addFirst("Apples");
	MDQ.addLast("Oranges");
	System.out.println(MDQ);
	MDQ.addFirst("Tangerines");
	MDQ.addFirst("Grapes");
	MDQ.addLast("Watermelons");
	MDQ.addFirst("Wintermelons");
	MDQ.addLast("Pears");
	MDQ.addLast("NOTAFRUIT");
	System.out.println(MDQ);
	MDQ.addFirst("nomoreideas");
	System.out.println(MDQ);
	System.out.println(MDQ.getFirst());
	System.out.println(MDQ.getLast());
	System.out.println(MDQ.removeFirst());
	System.out.println(MDQ.removeLast());
	MDQ.addFirst("REPLACES nomoreideas");
	MDQ.addLast("REPLACES NOTAFRUIT");
	System.out.println(MDQ);
    }
}
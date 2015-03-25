import java.util.Arrays;

public class MyDeque<T>{
    private Object[] stuffs;
    private int start,end,size;

    public MyDeque(){
	stuffs = new Object[8];
	start = 0;
	end = 0;
	size = 0;
    }
    
    public void addFirst(T value){
	if(size == stuffs.length)
	    resize();
	if(start - 1 < 0)
	    start = stuffs.length;
	stuffs[--start] = (Object)value;
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

    public String toString(){
	return Arrays.toString(stuffs);
    }

    private void resize(){
	Object[] newone = new Object[stuffs.length*2];
	int x = 0;
	for(int n = start;n != end;n++){
	    if(n == stuffs.length)
		n = 0;
	    newone[x] = stuffs[n];
	}
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
    }
}
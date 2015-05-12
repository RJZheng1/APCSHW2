import java.util.*;

public class MyHeap{
    private int[] data;
    private boolean isMax;
    
    public MyHeap(){
	this(true);
    }

    public MyHeap(boolean isMax){
	data = new int[10];
	this.isMax = isMax;
    }

    public boolean compare(int i1, int i2){
	if(isMax)
	    return data[i1] > data[i2];
	return data[i1] > data[i2];
    }

    public void add(int n){
	data[0] = data[0] + 1;
	data[data[0]] = n;
	pushUp(data[0]);
    }

    public void pushUp(int index){
	if(index != 1 && compare(index, index/2)){
	    int swap = data[index];
	    data[index] = data[index/2];
	    data[index/2] = swap;
	    pushUp(index/2);
	}
    }

    public int remove(){
	int root = data[1];
	data[1] = data[data[0]];
	data[0] = data[0] - 1;
	pushDown(1);
	return root;
    }

    public void pushDown(int index){
	if(index != 1 && (!compare(index, index*2) || !compare(index,index*2+1))){
	    if(!compare(index*2,index*2+1)){
		int swap = data[index];
		data[index] = data[index*2];
		data[index/2] = swap;
		pushUp(index*2);
	    }else{
		int swap = data[index];
		data[index] = data[index*2+1];
		data[index*2+1] = swap;
		pushUp(index*2+1);
	    }
	}
    }

    public String toString(){
	return Arrays.toString(data);
    }

    public static void main(String[] args){
	MyHeap h = new MyHeap();
	h.add(1);
	System.out.println(h);
	h.add(51);
	System.out.println(h);
	h.add(25);
	System.out.println(h);
	h.add(4);
	System.out.println(h);
	System.out.println(h.remove());
	System.out.println(h);
    }
}
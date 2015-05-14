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
	if(index < data[0] && (!compare(index, index*2) || !compare(index,index*2+1))){
	    if(compare(index*2,index*2+1)){
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

    public int peek(){
	return data[1];
    }
    
    public String toString(){
	int height = (int)(Math.log(data[0])/Math.log(2)) + 1;
	int maxLength = 0;
	for(int i = 1;i <= data[0];i++){
	    int numDigits = (int)Math.log10(data[i]) + 1;
	    if(numDigits > maxLength){
		maxLength = numDigits;
	    }
	}
	String tree = "";
	for(int i = 1;i <= height;i++){
	    for(int x = 0;x < maxLength * Math.pow(2,height - i - 1) - 1;x++){
		tree += " ";
	    }
	    for(int x = i;x < Math.pow(2,i) && x <= data[0];x++){
		tree += data[x];
		for(int a = 0;a < maxLength;a++){
		    tree += " ";
		}
	    }
	    tree += "\n";
	}
	return tree;
    }

    public String cushioning(int data, int maxLength){
	int rem = maxLength - (int)Math.log10(data);
	String result = "";
	int i = 0;
	while(i < rem/2 + rem % 2){
	    result += " ";
	}
	result += data;
	while(i < rem){
	    result += " ";
	}
	return result;
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

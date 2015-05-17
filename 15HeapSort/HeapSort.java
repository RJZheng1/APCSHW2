import java.util.*;

public class HeapSort{

    public static void heapSort(int[] n){
	heapify(n);
	for(int i = n.length - 1;i > 0;i--){
	    int swap = n[0];
	    n[0] = n[i];
	    n[i] = swap;
	    pushDown(0,i, n);
	}
    }
    
    public static void heapify(int[] n){
	for(int i = n.length - 1;i >= 0;i--){
	    pushDown(i,n.length,n);
	}
	System.out.println(Arrays.toString(n));
    }
    

    private static void pushDown(int index, int stop, int[] n){
	if(index * 2 + 2 < stop && (n[index] < n[index*2+1] || n[index] < n[index*2+2])){
	    if(n[index*2+1] > n[index*2+2]){
		int swap = n[index];
		n[index] = n[index*2+1];
		n[index*2+1] = swap;
		    pushDown(index*2+1,stop,n);
	    }else{
		int swap = n[index];
		n[index] = n[index*2+2];
		n[index*2+2] = swap;
		pushDown(index*2+2,stop,n);
	    }
	}else if(index * 2 + 1 < stop && n[index] < n[index*2+1]){
	    int swap = n[index];
	    n[index] = n[index*2+1];
	    n[index*2+1] = swap;
	}
    }
    
    public static void main(String[] args){
        int[] n = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
	System.out.println(Arrays.toString(n));
	HeapSort.heapSort(n);
	System.out.println(Arrays.toString(n));
    }
}

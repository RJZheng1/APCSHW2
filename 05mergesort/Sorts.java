import java.util.*;

public class Sorts{
    public static void main(String[] args){
	int[] test;
	int whichSort;
	try{
	    test = new int[Integer.parseInt(args[0])];
	    whichSort = Integer.parseInt(args[1]);
	}catch(ArrayIndexOutOfBoundsException e){
	    test = new int[1000000];
	    whichSort = 0;
	}
	Random r = new Random();
	for(int x = 0;x < test.length;x++){
	    test[x] = r.nextInt(2000000)-1000000;
	}
	if(whichSort == 0)
	    Sorts.mergeSort(test);
	else
	    Arrays.sort(test);
    }

    public static void mergeSort(int[] array){
	int[] result = mergeSort1(array);
	for(int x = 0;x < array.length;x++){
	    array[x] = result[x];
	}
    }

    public static int[] mergeSort1(int[] array){
	if(array.length == 1)
	    return array;
	return merge(mergeSort1(Arrays.copyOfRange(array,0,array.length/2)),mergeSort1(Arrays.copyOfRange(array,array.length/2,array.length)));
    }

    public static int[]  merge(int[] first,int[] second){
	int[] result = new int[first.length + second.length];
	int i1 = 0;
	int i2 = 0;
	for(int x = 0;x < result.length;x++){
	    if(i1 >= first.length){
		result[x] = second[i2];
		i2++;
	    }else if(i2 >= second.length){
		result[x] = first[i1];
		i1++;
	    }else if(first[i1] < second[i2]){
		result[x] = first[i1];
		i1++;
	    }else{
		result[x] = second[i2];
		i2++;
	    }
	}
	return result;
    }

    public String name(){
	return "zheng.rijiu";
    }
}
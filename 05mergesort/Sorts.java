import java.util.*;

public class Sorts{
    public static void main(String[] args){
	int[][] tests = new int[4][10];
	tests[0] = new int[]{0,1,2,3,4,5,6,7,8,9};
	tests[1] = new int[]{9,8,7,6,5,4,3,2,1,0};
	Random r = new Random();
	for(int x = 0;x < 10;x++){
	    tests[2][x] = r.nextInt(3) + 1;
	}
	for(int x = 0;x < 10;x++){
	    tests[3][x] = r.nextInt(2000000) - 1000000;
	}
	for(int n = 0;n < tests.length;n++){
	    mergeSort(tests[n]);
	    for(int x = 0;x < tests[n].length;x++)
		System.out.print(tests[n][x] + " ");
	    System.out.println("");
	}
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
}
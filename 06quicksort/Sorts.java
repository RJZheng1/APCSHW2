import java.util.*;

public class Sorts{
    public static int quickSelect(int[] ary, int n){
	return quickSelect(ary,0,ary.length,n);
    }

    public static int quickSelect(int[] ary,int s,int e, int n){
	int p = (int)(Math.random()*(e-s))+s;
	int cs = s;
	int ce = e - 1;
	int swap = ary[p];
	ary[p] = ary[ce];
	ary[ce] = swap;
	while(cs != ce){
	    for(;cs != ce && ary[cs] <= ary[e-1];cs++);
	    for(;cs != ce && ary[ce] >= ary[e-1];ce--);
	    swap = ary[ce];
	    ary[ce] = ary[cs];
	    ary[cs] = swap;
	}
	swap = ary[e-1];
	ary[e-1] = ary[cs];
	ary[cs] = swap;
	if(cs == n)
	    return cs;
	else if(cs > n)
	    return quickSelect(ary,s,cs,n);
	else
	    return quickSelect(ary,cs+1,e,n);
    }

    public static void quickSort(int[] ary){
	quickSort(ary,0,ary.length);
    }

    public static void quickSort(int[] ary,int s,int e){
	if(s != e -1 && s != e){
	    int p = (int)(Math.random()*(e-s))+s;
	    int cs = s;
	    int ce = e - 1;
	    int swap = ary[p];
	    ary[p] = ary[ce];
	    ary[ce] = swap;
	    while(cs != ce){
		for(;cs != ce && ary[cs] <= ary[e-1];cs++);
		for(;cs != ce && ary[ce] >= ary[e-1];ce--);
		swap = ary[ce];
		ary[ce] = ary[cs];
		ary[cs] = swap;
	    }
	    swap = ary[e-1];
	    ary[e-1] = ary[cs];
	    ary[cs] = swap;
	    quickSort(ary,s,cs);
	    quickSort(ary,cs+1,e);
	}
    }

    public static void main(String[] args){
	int[] ary = new int[]{3,5,9,2,0,1,4,8,6,7};
	System.out.println(quickSelect(ary,Integer.parseInt(args[0])));
	quickSort(ary);
	System.out.println(Arrays.toString(ary));
    }
}
import java.util.*;

public class QuickSelect{
    public static int quickSelect(int[] ary, int n){
	return quickSelect(ary,0,ary.length,n);
    }

    public static int quickSelect(int[] ary,int s,int e, int n){
	int p = ary[(int)(Math.random()*(e-s))+s];
	int cs = s;
	int ce = e-1;
	int swap;
	for(int i = s;cs != ce;i++){
	    if(ary[i] < p){
		swap = ary[cs];
		ary[cs++] = ary[i];
		ary[i] = swap;
	    }else if(ary[i] > p){
		swap = ary[ce];
		ary[ce--] = ary[i];
		ary[i] = swap;
		i--;
	    }
	}
	if(cs == n)
	    return cs;
	else if(cs > n)
	    return quickSelect(ary,s,p,n);
	else
	    return quickSelect(ary,p+1,e,n);
    }

    public static void main(String[] args){
	int[] a = new int[]{3,5,9,2,0,1,4,8,6,7};
	System.out.println(quickSelect(a,Integer.parseInt(args[0])));
    }
}
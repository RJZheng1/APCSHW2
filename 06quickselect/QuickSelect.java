import java.util.*;

public class QuickSelect{
    public static void partition(int[] ary,int s,int e){
	Random r = new Random();
	int p = ary[r.nextInt(e-s)+s];
	System.out.println(p);
	int[] d = new int[e-s];
	int cs = 0;
	int ce = d.length-1;
	for(int i = s;i < e;i++){
	    if(ary[i] < p)
		d[cs++] = ary[i];
	    if(ary[i] > p)
		d[ce--] = ary[i];
	}
	d[cs] = p;
	for(int i = 0;i < d.length;i++)
	    ary[i+s] = d[i];
    }

    public static void main(String[] args){
	int[] a = new int[]{79,84,56,12,97,65,48,73,98,46};
	for(int i = 0;i < a.length;i++)
	    System.out.print(a[i] + " ");
	System.out.println("");
	partition(a,0,a.length);
	for(int i = 0;i < a.length;i++)
	    System.out.print(a[i] + " ");
	System.out.println("");
    }
}
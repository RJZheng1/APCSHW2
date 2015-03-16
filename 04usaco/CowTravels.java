import java.io.*;
import java.util.*;

public class CowTravels{
    private int N, M, T, R1, C1, R2, C2;
    private char[][] pasture;
    
    public static void main(String[] args) throws FileNotFoundException{
	CowTravels ct = new CowTravels(args[0]);
	System.out.println(ct.move());
    }
	    
    public CowTravels(String file) throws FileNotFoundException{
	File inFile = new File(file);
	Scanner in = new Scanner(inFile);
	String[] NMT = in.nextLine().split(" ");
	N = Integer.parseInt(NMT[0]);
	M = Integer.parseInt(NMT[1]);
	T = Integer.parseInt(NMT[2]);
	pasture = new char[N][M];
	for(int r = 0;r < N;r++){
	    String row = in.nextLine();
	    for(int c = 0;c < M;c++)
		pasture[r][c] = row.charAt(c);
	}
	String[]  R1C1R2C2 = in.nextLine().split(" ");
	R1 = Integer.parseInt(R1C1R2C2[0]) - 1;
	C1 = Integer.parseInt(R1C1R2C2[1]) - 1;
	R2 = Integer.parseInt(R1C1R2C2[2]) - 1;
	C2 = Integer.parseInt(R1C1R2C2[3]) - 1;
    }

    public int move(){
	return move(R1,C1,T);
    }

    public int move(int r, int c,int t){
	if(r < 0 || r >= N || c < 0 || c >= M)
	    return 0;
      	if(Math.abs(r - R2) + Math.abs(c - C2) > t)
	    return 0;
	if(pasture[r][c] == '*')
	    return 0;
	if(r == R2 && c == C2 && t == 0)
	    return 1;
	if(t == 0)
	    return 0;
	t--;
	return move(r+1,c,t) + move(r,c+1,t) + move(r-1,c,t) + move(r,c-1,t);
    }

    public static String name(){
	return "zheng.rijiu";
    }
}
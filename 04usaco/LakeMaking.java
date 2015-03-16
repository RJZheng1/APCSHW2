import java.io.*;
import java.util.*;

public class LakeMaking{
    private int R, C, E, N;
    private int[][] pasture, instructions;

    public static void main(String[] args) throws FileNotFoundException{
	LakeMaking lm = new LakeMaking(args[0]);
	lm.stomp();
	System.out.println(lm.calc());
    }

    public LakeMaking(String file) throws FileNotFoundException{
	File inFile = new File(file);
	Scanner in = new Scanner(inFile);
	String[] RCEN = in.nextLine().split(" ");
	R = Integer.parseInt(RCEN[0]);
	C = Integer.parseInt(RCEN[1]);
	E = Integer.parseInt(RCEN[2]);
	N = Integer.parseInt(RCEN[3]);
	pasture = new int[R][C];
	for(int r = 0;r < R;r++){
	    String[] row = in.nextLine().split(" ");
	    for(int c = 0;c < C;c++)
		pasture[r][c] = Integer.parseInt(row[c]);
	}
	instructions = new int[N][3];
	for(int n = 0;n < N;n++){
	    String[] ins = in.nextLine().split(" ");
	    for(int x = 0;x < 3;x++)
		instructions[n][x] = Integer.parseInt(ins[x]);
	}
    }

    public void stomp(){
	for(int n = 0;n < N;n++){
	    int max = pasture[instructions[n][0]-1][instructions[n][1]-1];
	    for(int x = instructions[n][0] - 1;x < instructions[n][0] + 2;x++){
		for(int y = instructions[n][1] - 1;y < instructions[n][1] + 2;y++){
		    if(pasture[x][y] > max){
			max = pasture[x][y];
		    }
		}
	    }
	    max -= instructions[n][2];
	    for(int x = instructions[n][0] - 1;x < instructions[n][0] + 2;x++){
		for(int y = instructions[n][1] - 1;y < instructions[n][1] + 2;y++){
		    if(pasture[x][y] > max){
			pasture[x][y] = max;
		    }
		}
	    }
	}
    }

    public int calc(){
	int e = 0;
	for(int x = 0;x < R;x++){
	    for(int y = 0;y < C;y++){
		if(pasture[x][y] < E)
		    e += E - pasture[x][y];
	    }
	}
	return e * 72 * 72;
    }
    
    public String name(){
	return "zheng.rijiu";
    }
}
import java.util.*;
import java.io.*;

public class KnightsTour{
    //constants for the class
    //terminal specific character to clear screen , or hide/show cursor
    final static String clear =  "\033[2J";
    final static String hide =  "\033[?25l";
    final static String show =  "\033[?25h";

    //instance variable
    private int[][]board;

    //terminal specific character to move the cursor
    private String go(int x,int y){
	return ("\033[" + x + ";" + y + "H");
    }
    
    public void wait(int millis){
	try {
	    Thread.sleep(millis);
	}
	catch (InterruptedException e) {
	}
    }
    
    public String toString(){
	String ans = "\n";
	//build your knights tour here...
	for(int x = 0;x < board.length;x++){
	    for(int y = 0;y < board.length;y++){
		if(board[x][y] < 0)
		    ans += " ";
		else if(board[x][y] < 10)
		    ans += "  ";
		else if(board[x][y] < 100)
		    ans += " ";
		ans += board[x][y];
	    }
	    ans += "\n";
	}
	return clear + hide + go(0,0) + ans + "\n" + show;
    }
    
    public KnightsTour(int size){
        board = new int[size][size];
	for(int x = 0;x < size;x++){
	    for(int y = 0;y < size;y++)
		board[x][y] = -1;
	}
    }

    public boolean solve(){
	return solve(0,0);
    }
    
    public boolean solve(int startx, int starty){
	return solve(0,0,0);
    }
    
    public boolean solve(int x,int y,int currentMoveNumber){
	if(currentMoveNumber == board.length * board.length)
	    return true;
	if(x >= 0 && x < board.length && y >= 0 && y < board.length){
	    //	    System.out.println(this);
	    if(board[x][y] == -1){
		board[x][y] = currentMoveNumber;
		//		wait(20);
		if(solve(x-1,y+2,currentMoveNumber+1) ||
		   solve(x+1,y+2,currentMoveNumber+1) ||
		   solve(x+2,y+1,currentMoveNumber+1) ||
		   solve(x+2,y-1,currentMoveNumber+1) ||
		   solve(x-1,y-2,currentMoveNumber+1) ||
		   solve(x+1,y-2,currentMoveNumber+1) || 
		   solve(x-2,y-1,currentMoveNumber+1) ||
		   solve(x-2,y+1,currentMoveNumber+1))
		    return true;
		board[x][y] = -1;
	    }
	}
	return false;
    }
    
    public static void main(String[] args){
	
	KnightsTour kt = new KnightsTour(Integer.parseInt(args[0]));
	if(kt.solve())
	    System.out.println(kt);
	else
	    System.out.println("No solution");
    }
}
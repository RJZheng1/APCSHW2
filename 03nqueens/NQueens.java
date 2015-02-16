import java.util.*;
import java.io.*;


public class NQueens{
    //constants for the class	
    //terminal specific character to clear screen , or hide/show cursor
    final static String clear =  "\033[2J";
    final static String hide =  "\033[?25l";
    final static String show   =  "\033[?25h";

    //instance variable
    private char[][]board;

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
	for(int x = 0;x < board.length;x++){
	    for(int y = 0;y < board.length;y++){
		if(board[y][x] == 'Q')
		    ans += board[y][x] + " ";
		else
		    ans += "X ";
	    }
	    ans += "\n";
	}
	return hide + clear + go(0,0) + ans + "\n" + show;
    }

    public NQueens(int size){
	board = new char[size][size];
	for(int x = 0;x < size;x++){
	    for(int y = 0;y < size;y++){
		board[x][y] = 'O';
	    }
	}
    }

    public void mark(int x,int y){
	for(int n = 1;n < board.length - x;n++){
	    if(y - n >= 0)
		board[x+n][y-n] = 'X';
	    board[x+n][y] = 'X';
	    if(y + n < board.length)
		board[x+n][y+n] = 'X';
	}
    }

    public boolean checkQueens(int x,int y,int n){
	for(int m = 1;m <= x;m++){
	    if(n == -1 || n == 0){
		if(y - m >= 0){
		    if(board[x - m][y - m] == 'Q')
			return false;
		}
	    }
	    if(n == 1 || n == 0){
		if(y + m < board.length){
		    if(board[x - m][y + m] == 'Q')
			return false;
		}
	    }
	    if(n != 0){
		if(board[x-m][y] == 'Q')
		    return false;
	    }
	}
	return true;
    }

    public void reversemark(int x,int y){
	for(int n = 1;x + n < board.length;n++){
	    if(y - n >= 0){
		if(checkQueens(x + n, y - n, -1))
		    board[x+n][y-n] = 'O';
	    }
	    if(checkQueens(x + n, y, 0))
	       board[x+n][y]= 'O';
	    if(y + n < board.length){
		if(checkQueens(x + n, y + n, 1))
		    board[x+n][y+n] = 'O';
	    }
	}
    }
	    
    public boolean solve(){
	for(int y = 0;y < board.length;y++){
	    if(solve(0,y))
		return true;
	}
	return false;
    }

    public boolean solve(int y){
	return solve(0,y);
    }

    public boolean solve(int x,int y){
	board[x][y] = 'Q';
	mark(x,y);
	if(x + 1 == board.length)
	    return true;
	for(int y0 = 0;y0 < board.length;y0++){
	    if(board[x+1][y0] == 'O'){
		if(solve(x+1,y0))
		    return true;
	    }
	}
	board[x][y] = 'O';
	reversemark(x,y);
	return false;
    }

    public static void main(String[] args){
	NQueens nq = new NQueens(Integer.parseInt(args[0]));
	if(nq.solve(Integer.parseInt(args[1])))
	    System.out.println(nq);
	else
	    System.out.println("No solution");
    }
}

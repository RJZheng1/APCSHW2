import java.util.*;
import java.io.*;
public class Maze{
    private Deque<Coordinate> frontier;
    private char[][]maze;
    private int startx,starty;
    private int maxx,maxy;
    private static final String clear =  "\033[2J";
    private static final String hide =  "\033[?25l";
    private static final String show =  "\033[?25h";
    
    private class Coordinate{
	int x,y,size;
	Coordinate prev;
	public Coordinate(int x, int y){
	    this.x = x;
	    this.y = y;
	    size = 0;
	    prev = null;
	}
	public Coordinate(int x, int y, Coordinate prev){
	    this.x = x;
	    this.y = y;
	    this.prev = prev;
	    size = prev.getSize() + 1;
	}

	public int[] getCoords(){
	    return new int[]{x,y};
	}

	public int getSize(){
	    return size;
	}
    }

    public Maze(String filename){
	frontier = new LinkedList<Coordinate>();
	startx = -1;
	starty = -1;
	String ans = "";
	try{
	    Scanner in = new Scanner(new File(filename));
	    while(in.hasNext()){
		String line= in.nextLine();
		if(maxy==0)
		    maxx=line.length();
		maxy++;
		ans+=line;
	    }
	}
	catch(Exception e){
	    System.out.println("File: "+filename+" could not be opened.");
	    e.printStackTrace();
	    System.exit(0);
	}
	maze = new char[maxx][maxy];
	for(int i=0;i<ans.length();i++){
	    char c = ans.charAt(i);
	    maze[i%maxx][i/maxx]= c;
	    if(c=='S'){
		startx = i%maxx;
		starty = i/maxx;
	    }
	}
    }

    private String go(int x,int y){
	return ("["+x+";"+y+"H");
    }

    public void wait(int millis){
	try {
	    Thread.sleep(millis);
	}
	catch (InterruptedException e) {
	}
    }

    public String toString(){
	String ans = ""+maxx+","+maxy+"\n";
	for(int i=0;i<maxx*maxy;i++){
	    if(i%maxx ==0 && i!=0)
		ans+="\n";
	    ans += maze[i%maxx][i/maxx];
	}
	return ans;
    }

    public String toString(boolean animate){
	if(!animate)
	    return toString();
	wait(60);
	String ans = ""+maxx+","+maxy+"\n";
	for(int i=0;i<maxx*maxy;i++){
	    if(i%maxx ==0 && i!=0)
		ans+="\n";
	    ans += maze[i%maxx][i/maxx];
	}
	return clear + hide + go(0,0) + ans + "\n" + show;
    }

    public boolean solveBFS(boolean animate){
	Coordinate current = new Coordinate(startx,starty);
	char[] good = new char[]{' ','E'};
	while(maze[current.getX()][current.getY()] != 'E'){
	    if(Arrays.binarySearch(good,maze[current[0]+1][current[1]]) >= 0)
		frontier.addLast(new Coordinate(current[0]+1,current[1],current));
	    if(Arrays.binarySearch(good,maze[current[0]][current[1]+1]) >= 0)
		frontier.addLast(new Coordinate(current[0]+1,current[1],current));
	    if(Arrays.binarySearch(good,maze[current[0]-1][current[1]]) >= 0)
		frontier.addLast(new Coordinate(current[0]+1,current[1],current));
	    if(Arrays.binarySearch(good,maze[current[0]][current[1]-1]) >= 0)
		frontier.addLast(new Coordinate(current[0]+1,current[1],current));
	    if(animate){
		maze[current[0]][current[1]] = 'x';
		System.out.println(toString(true));
	    }
	    if(frontier.isEmpty())
		return false;
	    current = frontier.removeFirst();
	}
	return true;
    }

    public static void main(String[] args){
	Maze m = new Maze(args[0]);
	m.solveBFS(true);
    }
}

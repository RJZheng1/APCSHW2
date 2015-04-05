import java.util.*;
import java.io.*;
public class Maze{
    private char[][]maze;
    private int coords;
    private int startx,starty;
    private int maxx,maxy;
    private static final String clear =  "\033[2J";
    private static final String hide =  "\033[?25l";
    private static final String show =  "\033[?25h";
    private static final char[] good = {' ','E'};
    
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

	public Coordinate getNext(){
	    return prev;
	}
	
	public int getSize(){
	    return size;
	}
	
	public int getX(){
	    return x;
	}

	public int getY(){
	    return y;
	}
    }

    private class Frontier{
	private Deque<Coordinate> frontier;
	private boolean BFS;
	
	public Frontier(boolean BFS){
	    frontier = new LinkedList<Coordinate>();
	    this.BFS = BFS;
	}

	public void add(Coordinate c){
	    if(BFS)
		frontier.addLast(c);
	    else
		frontier.addFirst(c);
	}
	
	public void addMore(Coordinate c){
	    if(Arrays.binarySearch(good,maze[c.getX()+1][c.getY()]) >= 0)
		add(new Coordinate(c.getX()+1,c.getY(),c));
	    if(Arrays.binarySearch(good,maze[c.getX()][c.getY()+1]) >= 0)
		add(new Coordinate(c.getX(),c.getY()+1,c));
	    if(Arrays.binarySearch(good,maze[c.getX()-1][c.getY()]) >= 0)
		add(new Coordinate(c.getX()-1,c.getY(),c));
	    if(Arrays.binarySearch(good,maze[c.getX()][c.getY()-1]) >= 0)
		add(new Coordinate(c.getX(),c.getY()-1,c));
	}

	public boolean isEmpty(){
	    return frontier.isEmpty();
	}

	public Coordinate remove(){
	    return frontier.removeFirst();
	}
    }
    
    public Maze(String filename){
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
	wait(100);
	String ans = ""+maxx+","+maxy+"\n";
	for(int i=0;i<maxx*maxy;i++){
	    if(i%maxx ==0 && i!=0)
		ans+="\n";
	    ans += maze[i%maxx][i/maxx];
	}
	return clear + hide + go(0,0) + ans + "\n" + show;
    }

    public boolean solveBFS(boolean animate){
	Frontier f = new Frontier(true);
	Coordinate current = new Coordinate(startx,starty);
	while(maze[current.getX()][current.getY()] != 'E'){
	    f.addMore(current);
	    maze[current.getX()][current.getY()] = 'x';
	    if(animate)
		System.out.println(toString(true));
	    if(f.isEmpty())
		return false;
	    current = f.remove();
	}
	while(current != null){
	    System.out.println(Arrays.toString(current.getCoords()));
	    current = current.getNext();
	}
	return true;
    }

    public static void main(String[] args){
	Maze m = new Maze(args[0]);
	m.solveBFS(true);
    }
}

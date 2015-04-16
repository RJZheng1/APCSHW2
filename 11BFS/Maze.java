import java.util.*;
import java.io.*;
public class Maze{
    private char[][]maze;
    private int coords;
    private int startx,starty;
    private int endx,endy;
    private int maxx,maxy;
    private int[] solution;
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

	public String toString(){
	    return "(" + x + "," + y + ")";
	}
    }

    private class Frontier{
	private MyDeque<Coordinate> frontier;
	private int mode;
	
	public Frontier(int mode){
	    frontier = new MyDeque<Coordinate>();
	    this.mode = mode;
	}

	public void add(Coordinate c){
	    if(mode == 0)
		frontier.addLast(c);
	    else if(mode == 1)
		frontier.addFirst(c);
	    else if(mode == 2)
		frontier.add(c,Math.abs(c.getX() - endx) + Math.abs(c.getY() - endy));
	    else if(mode == 3)
		frontier.add(c,Math.abs(c.getX() - endx) + Math.abs(c.getY() - endy) + c.getSize());
	}
	
	public void addMore(Coordinate c){
	    if(Arrays.binarySearch(good,maze[c.getX()+1][c.getY()]) >= 0){
		add(new Coordinate(c.getX()+1,c.getY(),c));
		if(maze[c.getX()+1][c.getY()] != 'E')
		    maze[c.getX()+1][c.getY()] = '?';
	    }if(Arrays.binarySearch(good,maze[c.getX()][c.getY()+1]) >= 0){
		add(new Coordinate(c.getX(),c.getY()+1,c));
		if(maze[c.getX()][c.getY()+1] != 'E')
		    maze[c.getX()][c.getY()+1] = '?';
	    }if(Arrays.binarySearch(good,maze[c.getX()-1][c.getY()]) >= 0){
		add(new Coordinate(c.getX()-1,c.getY(),c));
		if(maze[c.getX()-1][c.getY()] != 'E')
		    maze[c.getX()-1][c.getY()] = '?';
	    }if(Arrays.binarySearch(good,maze[c.getX()][c.getY()-1]) >= 0){
		add(new Coordinate(c.getX(),c.getY()-1,c));
		if(maze[c.getX()][c.getY()-1] != 'E')
		    maze[c.getX()][c.getY()-1] = '?';
	    }
	}

	public boolean isEmpty(){
	    return frontier.isEmpty();
	}

	public Coordinate remove(){
	    if(mode == 2 || mode == 3)
		return frontier.removeSmallest();
	    return frontier.removeFirst();
	}

	public String toString(){
	    return frontier.toString();
	}
    }
    
    public Maze(String filename){
	startx = -1;
	starty = -1;
	endx = -1;
	endy = -1;
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
	    }else if(c == 'E'){
		endx = i%maxx;
		endy = i/maxx;
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

    public boolean solve(boolean animate, int mode){
	Frontier f = new Frontier(mode);
	Coordinate current = new Coordinate(startx,starty);
	while(maze[current.getX()][current.getY()] != 'E'){
	    f.addMore(current);
	    maze[current.getX()][current.getY()] = 'x';
	    if(animate){
		System.out.println(toString(true));
		System.out.println(f);
	    }
	    if(f.isEmpty())
		return false;
	    current = f.remove();
	}
	solution = new int[current.getSize()*2+2];
	for(int n = solution.length;n > 0;){
	    solution[--n] = current.getY();
	    solution[--n] = current.getX();
	    current = current.getNext();
	}
	maze[startx][starty] = 'S';
	for(int i = 2;i < solution.length-2;i++)
	    maze[solution[i]][solution[++i]] = 'O';
	return true;
    }

    public boolean solveBFS(boolean animate){
	return solve(animate,0);
    }

    public boolean solveDFS(boolean animate){
	return solve(animate,1);
    }

    public boolean solveBFS(){
	return solve(false,0);
    }

    public boolean solveDFS(){
	return solve(false,1);
    }

    public int[] solutionCoordinates(){
	if(solution == null)
	   return new int[0];
	else
	    return solution;
    }
    
    public static void main(String[] args){
	Maze m;
	if(args.length > 2){
	    m = new Maze(args[0]);
	    m.solve(true,Integer.parseInt(args[1]));
	}else if(args.length > 1){
	    m = new Maze(args[0]);
	    m.solve(false,Integer.parseInt(args[1]));
	}else{
	    m = new Maze("data1.dat");
	}
	System.out.println(m);
    }
}

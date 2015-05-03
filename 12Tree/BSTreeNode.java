import java.io.*;
import java.util.*;

public class BSTreeNode<T extends Comparable<T>>{
    private T data;
    private BSTreeNode<T> left;
    private BSTreeNode<T> right;

    public BSTreeNode( T d ) {
	data = d;
	left = right = null;
    }
    
    //accessors
    public T getData() {
	return data;
    }
    public BSTreeNode<T> getLeft() {
	return left;
    }
    public BSTreeNode<T> getRight() {
	return right;
    }

    //mutators
    public void setData( T d ) {
	data = d;
    }
    public void setLeft( BSTreeNode<T> l ) {
	left = l;
    }
    public void setRight( BSTreeNode<T> r ) {
	right = r;
    }
    
    public int compareTo( BSTreeNode<T> other){
	return data.compareTo(other.getData());
    }

    public String toString(){
	return data.toString();
    }
}

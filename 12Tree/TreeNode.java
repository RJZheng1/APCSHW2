public class TreeNode<T>{
    TreeNode<T> left, right;
    T data;

    public TreeNode(T data){
	this.data = data;
    }

    public T getData(){
	return data;
    }

    public void setData(T data){
	this.data = data;
    }

    public TreeNode<T> getLeft(){
	return left;
    }

    public void setLeft(TreeNode<T> left){
	this.left = left;
    }

    public TreeNode<T> getRight(){
	return right;
    }

    public void setRight(TreeNode<T> right){
	this.right = right;
    }

    public String toString(){
	return data.toString();
    }
}
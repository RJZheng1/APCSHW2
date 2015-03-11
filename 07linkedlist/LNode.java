public class LNode{
    private int value;
    private LNode next;

    public LNode(int value){
	this(value,null);
    }

    public LNode(int value, LNode next){
	this.value = value;
	this.next = next;
    }

    public void setValue(int value){
	this.value = value;
    }

    public int getValue(){
	return value;
    }

    public void setNext(LNode next){
	this.next = next;
    }

    public LNode getNext(){
	return next;
    }

    public String toString(){
	return "" + value;
    }
}
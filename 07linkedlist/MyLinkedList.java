public class MyLinkedList{
    LNode first;

    public MyLinkedList(){
    }

    public void add(int value){
	LNode now = first;
	LNode last = new LNode(value);
	try{
	    while(now.getNext() != null)
		now = now.getNext();
	    now.setNext(last);
	}catch(NullPointerException e){
	    first = last;
	}
    }

    public String toString(){
	String result = "";
	LNode now = first;
	while(now.getNext() != null){
	    result += now.toString() + ", ";
	    now = now.getNext();
	}
	result += now.toString();
	return result;
    }

    public static void main(String[] args){
	MyLinkedList l = new MyLinkedList();
	l.add(2);
	l.add(6);
	l.add(9);
	System.out.println(l);
    }
}
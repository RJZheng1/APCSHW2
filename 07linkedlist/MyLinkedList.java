public class MyLinkedList{
    LNode first;

    public MyLinkedList(){
    }

    public void add(int value){
	LNode now = first;
	try{
	    while(now.getNext() != null)
		now = now.getNext();
	    now.setNext(new LNode(value));
	}catch(NullPointerException e){
	    first = new LNode(value);
	}
    }

    public void add(int index, int value){
	LNode now = first;
	if(index < 0)
	    throw new IndexOutOfBoundsException();
	try{
	    for(int i = 0;i < index - 1;i++)
		now = now.getNext();
	    now.setNext(new LNode(value,now.getNext()));
	}catch(NullPointerException e){
	    throw new IndexOutOfBoundsException();
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
	l.add(1,4);
	System.out.println(l);
    }
}
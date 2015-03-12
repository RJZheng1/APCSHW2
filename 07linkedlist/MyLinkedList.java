public class MyLinkedList{
    private LNode first;

    public MyLinkedList(){
    }

    public boolean add(int value){
	LNode now = first;
	try{
	    while(now.getNext() != null)
		now = now.getNext();
	    now.setNext(new LNode(value));
	}catch(NullPointerException e){
	    first = new LNode(value);
	}
	return true;
    }

    public void add(int index, int value){
	if(index < 0 || index > size())
	    throw new IndexOutOfBoundsException();
	LNode now = first;
	for(int i = 0;i < index - 1;i++)
	    now = now.getNext();
	now.setNext(new LNode(value,now.getNext()));
    }

    public int get(int index){
	if(index < 0 || index >= size())
	    throw new IndexOutOfBoundsException();
	LNode now = first;
	for(int i = 0;i < index - 1;i++)
	    now = now.getNext();
	return now.getValue();
    }

    public int indexOf(int element){
	LNode now = first;
	for(int i = 0;now != null;i++){
	    if(now.getValue() == element)
		return i;
	    now = now.getNext();
	}
	return -1;
    }

    public int remove(int index){
	if(index < 0 || index >= size())
	    throw new IndexOutOfBoundsException();
	LNode now = first;
	for(int i = 0;i < index - 2;i ++)
	    now = now.getNext();
	int old = now.getNext().getValue();
	now.setNext(now.getNext().getNext());
	return old;
    }

    public void set(int index, int value){
	if(index < 0 || index >= size())
	    throw new IndexOutOfBoundsException();
	LNode now = first;
	for(int i = 0;i < index - 1;i++)
	    now = now.getNext();
	now.setValue(value);
    }

    public int size(){
	LNode now = first;
	int s = 0;
	while(now != null){
	    now = now.getNext();
	    s++;
	}
	return s;
    }

    public String toString(){
	String result = "[ ";
	LNode now = first;
	while(now.getNext() != null){
	    result += now + ", ";
	    now = now.getNext();
	}
	result += (now.toString() + " ]");
	return result;
    }

    public static void main(String[] args){
	MyLinkedList l = new MyLinkedList();
	l.add(2);
	l.add(6);
	l.add(9);
	l.add(1,4);
	l.add(4,5);
	System.out.println(l);
	System.out.println(l.get(0));
	l.set(0,1);
	System.out.println(l);
	l.remove(1);
	System.out.println(l);
    }
}
public class MyLinkedList{
    private LNode first;
    private LNode last;
    private int size;

    public MyLinkedList(){
	first = new LNode(0);
	last = first;
	size = 0;
    }

    public boolean add(int value){
	last.setNext(new LNode(value));
	last = last.getNext();
	size++;
	return true;
    }

    public void add(int index, int value){
	if(index < 0 || index > size())
	    throw new IndexOutOfBoundsException();
	LNode now = first;
	for(int i = 0;i < index;i++)
	    now = now.getNext();
	now.setNext(new LNode(value,now.getNext()));
	size++;
    }

    public int get(int index){
	if(index < 0 || index >= size())
	    throw new IndexOutOfBoundsException();
	LNode now = first.getNext();
	for(int i = 0;i < index;i++)
	    now = now.getNext();
	return now.getValue();
    }

    public int indexOf(int element){
	LNode now = first.getNext();
	for(int i = 0;now.getNext() != null;i++){
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
	for(int i = 0;i < index;i ++)
	    now = now.getNext();
	int old = now.getNext().getValue();
	now.setNext(now.getNext().getNext());
	size--;
	return old;
    }

    public int set(int index, int value){
	if(index < 0 || index >= size())
	    throw new IndexOutOfBoundsException();
	LNode now = first.getNext();
	for(int i = 0;i < index;i++)
	    now = now.getNext();
	int old = now.getValue();
	now.setValue(value);
	return old;
    }

    public int size(){
	return size;
    }

    public String toString(){
	String result = "[ ";
	LNode now = first.getNext();
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
	System.out.println(l.set(0,1));
	System.out.println(l);
	System.out.println(l.remove(1));
	System.out.println(l);
	System.out.println(l.indexOf(6));
	System.out.println(l.indexOf(53));
	System.out.println(l.size());
    }
}
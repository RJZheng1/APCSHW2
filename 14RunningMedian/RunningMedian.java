public class RunningMedian{
    MyHeap maxHeap;
    MyHeap minHeap;
    
    public RunningMedian(){
	maxHeap = new MyHeap(true);
	minHeap = new MyHeap(false);
    }

    public double getMedian(){
	if(maxHeap.size() == 0 && minHeap.size() == 0){
	    throw new IllegalStateException();
	}else if(maxHeap.size() > minHeap.size()){
	    return maxHeap.peek();
	}else if(minHeap.size() > maxHeap.size()){
	    return minHeap.peek();
	}else{
	    return (maxHeap.peek() + minHeap.peek()) / 2.0;
	}
    }

    public void add(int n){
	try{
	    double median = getMedian();
	    if(n > median){
		minHeap.add(n);
	    }else{
		maxHeap.add(n);
	    }
	    if(maxHeap.size() - minHeap.size() == 2){
		minHeap.add(maxHeap.remove());
	    }else if(minHeap.size() - maxHeap.size() == 2){
		maxHeap.add(minHeap.remove());
	    }
	}catch(IllegalStateException e){
	    maxHeap.add(n);
	}
    }

    public static void main(String[] args){
	RunningMedian med = new RunningMedian();
	med.add(3);
	System.out.println(med.getMedian());
	med.add(5);
	System.out.println(med.getMedian());
	med.add(4);
	System.out.println(med.getMedian());
	med.add(3);
	med.add(3);
	System.out.println(med.getMedian());
    }
}

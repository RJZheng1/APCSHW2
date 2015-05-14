public class RunningMedian{
    MyHeap maxHeap;
    MyHeap minHeap;
    
    public RunningMedian(){
	maxHeap = new MyHeap(true);
	minHeap = new MyHeap(false);
    }

    public double getMedian(){
	if(maxHeap.size() == 0 && minHeap.size() == 0){
	    throw new IllegalStateException;
	}else if(maxHeap.size() == 0){
	    return minHeap.peek();
	}else if(minHeap.size() == 0){
	    return maxHeap.peek();
	}else{
	    return (maxHeap.peek() + minHeap.peek()) / 2.0;
	}
    }

    public void add(double n){
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
}
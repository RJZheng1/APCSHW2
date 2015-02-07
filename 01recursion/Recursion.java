public class Recursion{
    public String name(){
	return "Zheng, Ri Jiu";
    }
    public int fact(int n){
	if(n < 0)
	    throw new IllegalArgumentException();
	return fact2(n,1);
    }
    public int fact2(int n,int ans){
	if(n <= 1)
	    return ans;
	return fact2(n-1,ans*n);
    }
    public int fib(int n){
	if(n < 0)
	    throw new IllegalArgumentException();
	return fib2(0,1,n);
    }
    public int fib2(int first,int second,int n){
	if(n==0)
	    return first;
	return fib2(second,first+second,n-1);
    }
    public double sqrt(double n){
	if(n < 0)
	    throw new IllegalArgumentException();
	if(n == 0)
	    return 0;
	return sqrt2(n,n/2);
    }
    public double sqrt2(double n,double guess){
	if(Math.abs(guess*guess-n)/n <= 0.01)
	    return guess;
	return sqrt2(n,(n / guess + guess)/ 2);
    }
}

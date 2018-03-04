package javaProgramms;
//tail recursion
public class Factorial {
	//recursive method
	public static int factorialRecursion(int n) {
		if(n==1) {//base case-termination condition
			return 1;
		}
		return n*factorialRecursion(n-1);//create new stack frame
	}
	//iterative method
	public static int factorialIterative(int n) {
		int result=n;
		while(n>1){//termination condition
			result*=n-1;
			n--;
		}
		return result;//create new stack frame
	}
	
	public static void main(String[] args) {
		System.out.println(factorialRecursion(5));
		System.out.println(factorialIterative(5));
	}

}

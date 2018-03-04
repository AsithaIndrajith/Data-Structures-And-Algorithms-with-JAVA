package javaProgramms;

public class GreatestCommonDiviser {

	//recursive method-Tail Recursion
	public static int gcdRecursion(int a,int b) {
		if(b==0) {//termination condition,when compairing two numbers and finding gcd if second number's value is 0 then first number is the gcd
			return a;
		}
		else if(b>a) {//if second number is greater than first we exchange them to work the code correctly
			gcdRecursion(b,a);
		}
		return gcdRecursion(b,a%b);
	}
	
	//iterative method
	public static int gcdIterative(int a,int b) {
		while(b>0) {
			int c=a%b;//greate third variable to store a%b's value
			a=b;//interchange a's and b's value
			b=c;
		}
		return a;
	}
	
	public static void main(String[] args) {
		System.out.println(gcdRecursion(10,2));
		System.out.println(gcdIterative(10,2));
	}

}

package javaProgramms;
class Stack{
	//Attributes
	private int index=-1;//add index variable to keep track on items in stack
	private int[] sArray;//container for stack
	
	//Constructor
	Stack(int size){
		sArray=new int[size];//create stack with a initial size
	}
	
	//Methods
	public int pop() {//pop method to remove top element
		index-=1;
		return sArray[index+1];
	}
	public void push(int item) {//push elements in to the stack
		if(index!=sArray.length-1) {
			sArray[index+1]=item;
			index+=1;
		}
		else {
			System.out.println("Stack is full!");
		}
	}
}
public class stackJava {

	public static void main(String[] args) {
		Stack s=new Stack(5);
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		System.out.println(s.pop());
		s.push(6);
		s.push(7);
	}

}

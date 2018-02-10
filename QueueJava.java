package javaProgramms;

class Queue{
	private int front;//Queue accessing front index
	private int back;//Queue accessing back index
	private int[] qList;//Queue container
	
	public Queue(int size) {//Constructor initialise queue's size to size and index to 0
		front=0;
		back=-1;
		qList=new int[size];
	}
	
	public void enqueue(int item) {//enqueing item from back
		qList[back+1]=item;
		back++;
	}
	public int dequeue() {//dequeing itemin front
		int temp=qList[front];
		qList[front]=0;
		front++;
		return temp;
	}
	public void veiwQueue() {
		for(int i=0;i<qList.length;i++) {
			System.out.print("|"+qList[i]);
		}
		System.out.println("|");
	}
	
	
}
public class QueueJava {

	public static void main(String[] args) {
		Queue queue=new Queue(5);
		queue.veiwQueue();//no elements in queue
		queue.enqueue(4);
		queue.enqueue(3);
		queue.enqueue(1);
		queue.veiwQueue();//after 3 elements in the queue;
		queue.dequeue();
		queue.veiwQueue();//delete one element
		queue.dequeue();
		queue.veiwQueue();//delete second element

	}

}

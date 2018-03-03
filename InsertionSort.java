package javaProgramms;

public class InsertionSort {
	
	public static int[] insertionSort(int[] array) {
		for(int i=1;i<array.length;i++) {//Outer loop iterate over array
			int key=array[i];//start with storing second element in variable key because the first element is already in sorted array according to our assumption
			int j=i-1;//initialise second j variable
			try {
			while(j>-1&&array[j]>key) {//key variable check with previous element each iteration until j==0 or previous element less than key
										//reason for end loop at when previous element is less than key is we don't need to check until j==0 because that part of array is in sorted form
				array=swap(array,j,j+1);//if previous element is less than key swap it
				j--;//decrement j to check until 0 to i part of array is sorted
			}
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return array;
		
	}
	//Swap function
	public static int[] swap(int[] array,int i,int j) {
		int temp=array[i];
		array[i]=array[j];
		array[j]=temp;
		return array;
	}
	
	public static void main(String[] args) {
		int[] array= {3,4,2,6,1};
		array=insertionSort(array);
		for(int i: array) {System.out.printf("|%d|",i);}
	}

}

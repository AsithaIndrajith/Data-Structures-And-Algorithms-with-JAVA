import java.util.Scanner;

/*Longest Increasing Subsequence*/
public class LIS {
	
	/*Steps:
		1.First create random numbers generating funtion. Note: Numbers are between (1-10).
		2.Then write two functions to get maxmium between two numbers and to get the maxmium values's index in a array.
		3.Then write a function to get subsequences' lengths. Beacause we need to find which subsequence has maxmimum length.
		4.In that function first we check numbers in the sequence in increasing order. 
		5.If compairing numbers are in increasing order we increment the length of the subsequence.
		6.If not we we bypass it and continues.
		7.According subsequnces's length array we note down which elements are in that sequence using another array.
		8.We do that using another function that return a array with indexes that we can find longest subsequence from it.
		9.After we get both arrays(subsequence lenghts array and longest subsequnce indexs array) we maximum length from length array.
		10.And then we go to corresponding array position in indexes array.
		11.Then after going through in index array we can found the subsequence which is longest and with increasing order. 
	*/
	
	/*random number sequence generator*/
	static int[] random_number_generator(int n) {
		int random_number_list[]=new int[n];
		for(int i=0;i<n;i++) {
			random_number_list[i]=(int)(Math.random()*10);
			while(random_number_list[i]==0) {
				random_number_list[i]=(int)(Math.random()*10);
			}
		}
		return random_number_list;
	}
	
	/*max finding between two given numbers*/
	static int max(int a,int b) {
		return (a>b)?a:b;
	}
	
	/*max finding in a integer array*/
	static int array_max_index(int a[]) {
		int size=a.length;
		int max=a[0];
		int max_index=0;
		for(int i=1;i<size;i++) {
			if(a[i]>=max) {
				max=a[i];
				max_index=i;
			}
		}
		return max_index;
	}
	
	/*genearate sub secequences' lengths from original number sequence*/	
	static int[] lis_subsequence_lengths_finder(int number_sequence_array[]) {
		int size=number_sequence_array.length;/*length of original number sequence array*/
		int subsequences_lengths_array[]=new int[size];/*Create array to store subsequences'lengths*/
		
		for(int i=0;i<size;i++) {/*first we set all sub sequnses length in to 1. Because the minimum value that any subsequence cantake is 1*/
			subsequences_lengths_array[i]=1;
		}
		
		for(int i=1;i<size;i++) {/*Then we start from index 1 to end of the array, while looking all the elements before this elements are greater or less or equal*/ 
			for(int j=0;j<i;j++) {/*This loop checks all elements behind (i)th elements are greater or less or equal*/
				if(number_sequence_array[i]>=number_sequence_array[j]) {/*If jth number is less than ith one that means thay are in increasing order*/
					if(subsequences_lengths_array[j]+1>=subsequences_lengths_array[i]) {/*Then we check jth position length is grater than ith position length.Then only we change the ith position length*/
						subsequences_lengths_array[i]=max(subsequences_lengths_array[j]+1,subsequences_lengths_array[i]);//
					}	
				}
			}
		}
		return subsequences_lengths_array;
	}
	
	/*genearate longest sub secequences' index sequence from original number sequence*/	
	static int[] lis_index_sequence_finder(int number_sequence[],int subsequences_lengths_array[]) {
		int size=number_sequence.length;
		int longest_sub_sequence_index_array[]=new int[size];/*Create a array to store indexes to get longest subsequence*/
		
		/*continue the same process int lengths array finding function*/
		for(int i=1;i<size;i++) {
			for(int j=0;j<i;j++) {
				if(number_sequence[i]>=number_sequence[j]) {
					if(subsequences_lengths_array[j]+1>=subsequences_lengths_array[i]) {
						longest_sub_sequence_index_array[i]=j;
					}
				}
			}
		}
		return longest_sub_sequence_index_array;
	}
	
	/*Find longest increasing subsequence using longest increasing subsequence index array*/
	static int[] longest_increasing_subsequence_finder(int subsequences_lengths_array[],int longest_subsequence_index_array[],int number_sequence[]) {
		
		int max_subsequence_length_index=array_max_index(subsequences_lengths_array);/*We get index of maximum length in lengths array*/

		int longest_increasing_subsequence[]=new int[subsequences_lengths_array[max_subsequence_length_index]];/*Then we create array with size of maximum length*/
		
		int i=max_subsequence_length_index;
		int j=subsequences_lengths_array[max_subsequence_length_index]-1;
		
		
		do{
			longest_increasing_subsequence[j]=number_sequence[i];/*We store elements in array with respect to indexes in index array*/
			i=longest_subsequence_index_array[i];
			j--;
		}while(j>-1);/*Until j reach to 0*/ 
		
		return longest_increasing_subsequence;
	}
	
	public static void main(String[]args) {
		Scanner in=new Scanner(System.in);
		int input=in.nextInt();
		in.nextLine();
		int a[]=random_number_generator(input);//{0,4,12,2,10,6,9,13,3,11,7,15};////{5,5,5,5,1,2,8,9,4,6,8};
		System.out.print("Number sequence: ");
		for(int i:a) {
			System.out.print(i+" ");
		}
		System.out.println("");
			
		int b[]=lis_subsequence_lengths_finder(a);
		int c[]=lis_index_sequence_finder(a,b);
		
		System.out.print("Subsequences' lengths: ");
		for(int i:b) {
			System.out.print(i+" ");
		}
		System.out.println("");

		System.out.print("Longest Increasing Subsequence's indexes: ");
		for(int i:c) {
			System.out.print(i+" ");
		}
		System.out.println("");

		int d[]=longest_increasing_subsequence_finder(b,c,a);
		
		System.out.print("Longest Increasing Subsequence: ");
		for(int i:d) {
			System.out.print(i+" ");
		}
		System.out.println("");
		
	}
}

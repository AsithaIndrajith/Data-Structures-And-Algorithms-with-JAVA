package javaProgramms;

public class StringMatching {
	//Naive base String Matching Algorithm
	//Time Complexity O(len(pattern)*len(text))
	
	public static String naive_string_matcher(String t,String p ){
		int len_p=p.length();//get length of pattern
		int len_t=t.length();//get length of text
		
		int counter_one=0;//Outer loop counter
		int counter_two=0;//Inner loop counter
		int counter_three=0;//counter for when we scan pattern we need to increment pattern position
		
		char[] pat=p.toCharArray();//we convert pattern string to character array
		char[] tex=t.toCharArray();//same for the text
		
		boolean match_found=true;//create boolean for find that math arises
		
		while(counter_one<(len_t-len_p+1)) {
			counter_two=0;
			match_found=true;
			
			while(counter_two<len_p) {
				if(tex[counter_three]!=pat[counter_two]) {
					match_found=false;
					break;//if characters not equal we break from the loop and make boolean false
				}
				counter_two++;
				counter_three++;
			}
			
			if(match_found) {
				return "Pattern "+p+" found at "+Integer.toString(counter_one)+" index of text "+t+" using naive based method";
			}
			
			counter_one++;
			counter_three=counter_one;//we need to check next text character with pattern.
		}
		return "Pattern not found";
	}
	
	//KMP Method
	//Prefix finding function
	public static int[] preFixFn(String p) {
		int m=p.length();
		int k=0;
		int [] PI=new int[m];
		
		PI[0]=0;
		
		char[] P=p.toCharArray();
		
		for(int q=1;q<m;q++) {
			do{
				k=PI[k];
			}while(k>0 && (P[k]!=P[q]));
			if(P[k]==P[q]) {
				k++;
			}
			PI[q]=k;
		}
		return PI;		
	}
	
	public static String KMP(String t,String p) {
		int n=t.length();
		int m=p.length();
		
		int q=0;
		
		int[] PI= new int[m];
		PI=preFixFn(p);
		
		//Print Prefix Function
		System.out.print("[Prefix Function:");
		for(int j=0;j<PI.length;j++) {
			System.out.print(PI[j]);
		}
		System.out.print("]");
		
		char[] T=t.toCharArray();
		char[] P=p.toCharArray();
		
		for(int i=0;i<n;i++) {
			while(q>0 && (P[q]!=T[i])) {
				q=PI[q];
			}
			if(P[q]==T[i]) {
				q++;
			}
			if(q==m) {
				return "Pattern "+p+" found at "+Integer.toString(i-m+1)+" index of text "+t+" using KMP method";
			}
			}
		return "Pattern not found";
	}
	//Robin-Carp method
	
	public static String robin_carp(String Text,String Pattern,int d,int q) {
		int n=Text.length();
		int m=Pattern.length();
		int h=(int) ((Math.pow(d,m-1))%q);
		int p=0;
		int t=0;
		//first generate hash value of the pattern and pattern length size substring part in text
		for(int i=0;i<m;i++) {
			p=(d*p+Pattern.charAt(i))%q;
			t=(d*t+Text.charAt(i))%q;
		}
		for(int s=0;s<n-m;s++) {
			if(p==t) {//If two hash values are equal then we check that the pattern is matching using loop
				int j=0;
				int flag=1;
				while(j<m) {
					if(Pattern.charAt(j)!=Text.charAt(s+2+j)) {
						flag=0;
						break;
					}
					j++;
				}
				if(flag==1) {
					return "Pattern "+Pattern+" found at "+Integer.toString(s+3)+" index of text "+Text+" using Robin Carp method";
				}
			}
			if(s<n-m) {//if no match of hash values occur then create next hash value using previous one as below
				int s_m=Integer.parseInt(Text.substring(s,m));//take the first hash value calculated string
				int s_s1=Integer.parseInt(Text.substring(s,s+1));//get first character in that string
				int sm_sm1=Integer.parseInt(Text.substring(s+m,s+m+1));//get next character after first string
				t=(d*(s_m-s_s1*h)+(sm_sm1))%q;//calculate next hash value
				
			}
		}
		return "Pattern not found";
	}
	
	public static void main(String[] args) {
		String result=naive_string_matcher("abbabbaaccac","babbaa");
		System.out.println(result);
		result=KMP("abbabbaaccac","babbaa");
		System.out.println(result);
		result=robin_carp("2359023141526739921","31415",10,13);
		System.out.println(result);
	}
}


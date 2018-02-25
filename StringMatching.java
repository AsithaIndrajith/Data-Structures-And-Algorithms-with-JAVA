public class StringMatching {
	//Naive base String Matching Algorithm
	//Time Complexity O(len(pattern)*len(text))
	
	public static String naive_string_matcher(String t,String p ){
		int m=p.length();//get length of pattern
		int n=t.length();//get length of text
						
		for(int i=0;i<n-m+1;i++) {
			int j=0;
			while(j<m&&(t.charAt(i+j)==p.charAt(j))) {
				j++;//if characters not equal we break from the loop and make boolean false
			}			
			if(j==m) {
				return "Pattern "+p+" found at "+i+" index of text "+t+" using naive based method";
			}
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
	//Rabin-Carp method
	
	public static String rabin_carp(String Text,String Pattern,int d,int q) {
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
	//Boyer_Moore Method
	public static String boyer_moor(String T,String P) {
		int n=T.length();//get Text 's length
		int m=P.length();//get pattern's length
		
		int[] last=lastOccurenceFinder(P);
		
		int s=0;//initialise shifts to 0
		
		while(s<n-m+1) {//while shifts are less than this
			//System.out.println("s:"+s);
			int j=m-1;//new variable j use to search from right to left
			try {//use try block to handle Exceptions
				while((j>0)&&(P.charAt(j)==T.charAt(s+j))) {//while characters are equal and j is not go beyond 0
					//System.out.println("P.charAt("+j+"):"+P.charAt(j)+","+"T.charAt("+s+j+")"+T.charAt(s+j));
					j--;//decrement j and check characters
				}
				//System.out.println("j:"+j);
				//System.out.println("last["+((int)(T.charAt(s+j))-97)+"]:"+last[(int)(T.charAt(s+j))-97]);
				if(j==0) {//if j=0 then it means j has gone until the end of the match
					return "matchedAt:"+(s);
				}
				else if(last[(int)(T.charAt(s+j))-97]<j) {//if not we take the last occurrence of mismatch
					if(last[(int)(T.charAt(s+j))-97]==-1) {
						s++;
					}
					else {//if not, increment s by below
						s=s+j-last[(int)(T.charAt(s+j))-97];
					}
				}
				else {//if last occurrence has passed then we shift only 1
					s++;
				}
				
			}catch(Exception e) {
				System.out.println(e);
				break;
				
			}
			
		}
		
		return "notMatch";
	}
	public static int[] lastOccurenceFinder(String P) {//last occurrence place finding method
		int m=P.length();//takes pattern's length
		String alphabet= "abcdefghijklmnopqrstuvwxyz";//give the alphabet
		int[] lastChar=new int[26];//create new array to store last occurrence of each character
		
		System.out.print("Last Ocurences Table|");
		for(int i=0;i<26;i++) {//iterate until 26 letters
			int j=m-1;//scan from right to find last occurrences
			while(j>-1&&(alphabet.charAt(i)!=P.charAt(j))) {//loop until character found
				j--;
				}
			lastChar[i]=j;//store the place of character and if character is not in the patterns it stores as -1
			System.out.print(alphabet.charAt(i)+":"+lastChar[i]+"|");
			}
		System.out.println("");
			
		return lastChar;//return array 
	}
	
	public static void main(String[] args) {
		String result=naive_string_matcher("abbabbaaccac","babbaa");
		System.out.println(result);
		
		result=KMP("abbabbaaccac","babbaa");
		System.out.println(result);
		
		result=rabin_carp("2359023141526739921","31415",10,13);
		System.out.println(result);
		
		result=boyer_moor("aacababb","babb");
		System.out.println(result);
	}
}


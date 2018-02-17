package Tasks;
import java.util.Arrays;
import java.util.LinkedList;

import InputOutput.PrintOptimalAlignment;
import Matrix.Blosum50;


public class Task5_GapPenalty {
	/*******Iterative Version*******/
	public static void computeAlignmentMatrix(String s1, String s2, float[][]m, char[][] op,float inc_pen, float gap_pen ){
		/*computeAlignmentMatrix */
		m[0][0]=0;
    	for (int i=1; i<=s1.length();i++){
    		m[i][0]=0;
    		op[i][0]='d';
    	}
		for (int j=1; j<=s2.length();j++){
    		m[0][j]=0;
    		op[0][j]='i';
    	}
    	
		for (int i=1; i<=s1.length();i++){
			for (int j=1; j<=s2.length();j++){
				float max;
				max=m[i-1][j-1]+Blosum50.getScore(s1.charAt(i-1), s2.charAt(j-1));
				if (s1.charAt(i-1)==s2.charAt(j-1)){
					op[i][j]='e';
				}
				else{
	            	op[i][j]='r';
	            }
				
				float aux;
				for (int k=1;k<=i;k++){
					aux=m[i-k][j]-((j==s2.length())?0:(gap_pen+(k-1)*inc_pen));
					if (aux>max) {
						max=aux;
						op[i][j]='d';
					}
				}
				
				for (int k=1;k<=j;k++){
					aux=m[i][j-k]-((i==s1.length())?0:(gap_pen+(k-1)*inc_pen));
					if (aux>max) {
						max=aux;
						op[i][j]='i';
					}
				}
												
	            m[i][j]=max;
			}
		}
	}
	public static void computeOptimalAlignment(String s1, String s2, char[][] op, LinkedList<Boolean> bool, LinkedList<Character> l1, LinkedList<Character> l2){
		int i=s1.length();
		int j=s2.length();
		while (i>0 || j>0){

			if (op[i][j]=='d'){
	    		bool.add(false);
	    		l1.add(s1.charAt(i-1));
	    		l2.add('-');
	    		i--;
	    	}
			else if (op[i][j]=='i'){
	    		bool.add(false);
	    		l1.add('-');
	    		l2.add(s2.charAt(j-1));
	    		j--;
	    	}
			else if (op[i][j]=='r'){
	    		
	    		bool.add(false);
	    		l1.add(s1.charAt(i-1));
	    		l2.add(s2.charAt(j-1));
	    		i--;
	    		j--;
	    	} 	
			else if (op[i][j]=='e'){
				
	    		bool.add(true);
	    		l1.add(s1.charAt(i-1));
	    		l2.add(s2.charAt(j-1));
	    		i--;
	    		j--;

	    		
	    	    }}
		reverse(bool);
	    reverse(l1);
	    reverse(l2);
		}
	    	
	private static <E> void reverse(LinkedList<E> l) {
		LinkedList<E> l2=new LinkedList<E>();
		while (!l.isEmpty()) l2.add(l.removeLast());
		l.addAll(l2);
	}
	    	
	public static void optimalAlignment(String s1, String s2, float inc_pen, float gap_pen) {
	    	int n1=s1.length();
	    	int n2=s2.length();
	    	float[][] d = new float [n1+1][n2+1]; 
	    	 
	    	char[][] op = new char [n1+1][n2+1]; 

	    	
	    	LinkedList<Boolean> bool=new LinkedList<Boolean>();
	    	LinkedList<Character> l1=new LinkedList<Character>();
	    	LinkedList<Character> l2=new LinkedList<Character>();
	    	/*******Iterative Version*******/
	    	computeAlignmentMatrix(s1, s2, d, op, inc_pen, gap_pen);
	    	computeOptimalAlignment(s1,  s2,  op, bool, l1, l2);
	    		
	    	new PrintOptimalAlignment(bool, l1, l2);

	    }
			

}

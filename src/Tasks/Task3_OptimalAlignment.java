package Tasks;
import java.util.LinkedList;

import InputOutput.PrintOptimalAlignment;


public class Task3_OptimalAlignment {
	/*computeAlignmentMatrix computes the matrix of costs of transformations 
	 * the cost of transforming the sequence s1[0 … i-1] into s2[0 … j-1] 
	 * is in the coefficient (i,j)*/
	
	//When one of the two strings is empty the cost is the length of the remaining sequence
	
	public static void computeAlignmentMatrix(String s1, String s2, int[][]m, char[][] op){
		//When one of the two strings is empty the cost is the length of the remaining sequence
    	for (int i=0; i<=s1.length();i++){
    		m[i][0]=i;
    		op[i][0]='d';
    	}
		for (int j=0; j<=s2.length();j++){
    		m[0][j]=j;
    		op[0][j]='i';
    	}
    	
		for (int i=1; i<=s1.length();i++){
			for (int j=1; j<=s2.length();j++){
				int min;
				if (s1.charAt(i-1)==s2.charAt(j-1)){
					min=m[i-1][j-1];
					op[i][j]='e';
				}
				else{
	            	min=m[i-1][j-1]+1;
	            	op[i][j]='r';
	            }
				int aux=m[i][j-1]+1;
				if (aux<min){
					min=aux;
					op[i][j]='i';
				}
				aux=m[i-1][j]+1;
				if (aux<min){
					min=aux;
					op[i][j]='d';
				} 
				m[i][j]=min;
			}
		}
	}
    public static void computeOptimalAlignment(String s1, int i, String s2, int j, char[][] op, LinkedList<Boolean> bool, LinkedList<Character> l1, LinkedList<Character> l2){
    	
    	if ((i>=0 && j>=0) && (!(j==0 & i==0))){

		if (op[i][j]=='d'){
			computeOptimalAlignment(s1,i-1,s2,j,op,bool, l1, l2);
    		bool.add(false);
    		l1.add(s1.charAt(i-1));
    		l2.add('-');
    	}
		else if (op[i][j]=='i'){
    		computeOptimalAlignment(s1,i,s2, j-1,op,bool, l1, l2);
    		bool.add(false);
    		l1.add('-');
    		l2.add(s2.charAt(j-1));
    	}
		else if (op[i][j]=='r'){
    		computeOptimalAlignment(s1,i-1,s2, j-1, op, bool, l1, l2);
    		bool.add(false);
    		l1.add(s1.charAt(i-1));
    		l2.add(s2.charAt(j-1));
    	} 	
		else if (op[i][j]=='e'){
    		computeOptimalAlignment(s1,i-1,s2,j-1, op, bool, l1, l2);
    		bool.add(true);
    		l1.add(s1.charAt(i-1));
    		l2.add(s2.charAt(j-1));

    	}}
    	
    }

		    
    public static void optimalAlignment(String s1, String s2) {
    	int[][] d = new int [s1.length()+1][s2.length()+1]; 
   
    	char[][] op = new char [s1.length()+1][s2.length()+1]; 
    	
    	LinkedList<Boolean> bool=new LinkedList<Boolean>();
    	LinkedList<Character> l1=new LinkedList<Character>();
    	LinkedList<Character> l2=new LinkedList<Character>();

    		
    	computeAlignmentMatrix(s1, s2, d, op);
    	computeOptimalAlignment(s1, s1.length(), s2, s2.length(), op, bool, l1, l2); 	
    	
    	new PrintOptimalAlignment(bool, l1, l2);

    }
			
}

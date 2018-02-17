package Tasks;
import java.util.Arrays;
import java.util.LinkedList;

import InputOutput.PrintOptimalAlignment;
import Matrix.Blosum50;


public class Task4_SubstitutionMatrices {
	public static void computeAlignmentMatrix(String s1, String s2, float[][]m, char[][] op){
		m[0][0]=0;
    	for (int i=1; i<=s1.length();i++){
    		m[i][0]=m[i-1][0]+Blosum50.getScore(s1.charAt(i-1), '-');
    		op[i][0]='d';
    	}
		for (int j=1; j<=s2.length();j++){
    		m[0][j]=m[0][j-1]+Blosum50.getScore('-', s2.charAt(j-1));
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
				aux=m[i-1][j]+Blosum50.getScore(s1.charAt(i-1), '-');
				if (aux>max){
					max=aux;
					op[i][j]='d';
				}
				
				aux=m[i][j-1]+Blosum50.getScore('-',s2.charAt(j-1));
				if (aux>max){
					max=aux;
					op[i][j]='i';
				}	
				
	            m[i][j]=max;
			}
		}
	}
	
	public static void computeAlignmentMatrix(String s1, int i, String s2, int j, float[][]m, char[][] op){

			if (j==0){
	    		for (int k=1;k<i;k++){
	    			m[k][0]=m[k-1][0]+Blosum50.getScore(s1.charAt(k-1), '-');
	    			op[k][0]='d';
	    		}
	    	}
	    	else if (i==0){
	    		for (int k=1;k<j;k++){
	    			m[0][k]=m[0][k-1]+Blosum50.getScore('-', s2.charAt(k-1));
	    			op[0][k]='i';
	    		}
	    	}
	    	
			else{
				float max;
				computeAlignmentMatrix(s1, i-1,s2, j-1, m, op);
            	max=m[i-1][j-1]+Blosum50.getScore(s1.charAt(i-1),s2.charAt(j-1));
				if (s1.charAt(i-1)==s2.charAt(j-1)){
					op[i][j]='e';
				}
	            else{
	            	op[i][j]='r';
	            }
				
				float aux;
				float insertCost = Blosum50.getScore('-',s2.charAt(j-1));
				computeAlignmentMatrix(s1, i, s2, j-1, m, op);
				aux=m[i][j-1]+insertCost;
				if (aux>max){
					max=aux;
					op[i][j]='i';
				}	
				
				
				float deleteCost = Blosum50.getScore(s1.charAt(i-1), '-');
				computeAlignmentMatrix(s1, i-1, s2, j, m, op);
				aux=m[i-1][j]+deleteCost;
				if (aux>max){
					max=aux;
					op[i][j]='d';
				}
				
	            m[i][j]=max;
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
	    	int n1=s1.length();
	    	int n2=s2.length();
	    	float[][] d = new float [n1+1][n2+1]; 
	    	 
	    	char[][] op = new char [n1+1][n2+1]; 
	    	
	    	
	    	LinkedList<Boolean> bool=new LinkedList<Boolean>();
	    	LinkedList<Character> l1=new LinkedList<Character>();
	    	LinkedList<Character> l2=new LinkedList<Character>();

	    		
	    	computeAlignmentMatrix(s1, s2, d, op);
	    	
	    		
	    	computeOptimalAlignment(s1, s1.length(), s2, s2.length(), op, bool, l1, l2);
	    	
	    	
	    	new PrintOptimalAlignment(bool, l1, l2);

	    }
			
}

package Tasks;

public class Task2_LongestCommonSub {

	public static void computeAlignmentMatrix(String s1, String s2, int[][]m){
		/*computeAlignmentMatrix computes the matrix of costs of transformations 
		 * the cost of transforming the sequence s1[0 … i-1] into s2[0 … j-1] 
		 * is in the coefficient (i,j)*/
		
		//When one of the two strings is empty the length of the subsequence is 0
    	for (int i=0; i<=s1.length();i++)
    		m[i][0]=0;
    	for (int j=0; j<=s2.length();j++)
    		m[0][j]=0;
    	
    	for (int i=1; i<=s1.length();i++)
    		for (int j=1; j<=s2.length();j++){
    			//If the characters are the same the length is increased by one
    			if (s1.charAt(i-1)==s2.charAt(j-1)) m[i][j]=m[i-1][j-1]+1;
    			// If they are different, we try to suppress one of the two
    			//last letters
    			else m[i][j]=Math.max(m[i-1][j],m[i][j-1]);
    		}
	}
    
    public static String computeOptimalAlignment(String s1, String s2,int[][]m){
    	int i=s1.length();
    	int j=s2.length();
    	String sub="";
    	while (i>0 && j>0) {
			if (s1.charAt(i-1)==s2.charAt(j-1)){
				sub+=s1.charAt(i-1);
				i--;
				j--;
	    	}
			else if (m[i-1][j]<m[i][j-1]){
	    		j--;
	    	}
			else {
	    		i--;
	    	} 
		}
    	return new StringBuilder(sub).reverse().toString();    	
    
    	}
    
    public static void LongCommonSub(String s1, String s2) {
    	
    	int n1=s1.length();
    	int n2=s2.length();
    	int[][] m = new int [n1+1][n2+1]; 
    	computeAlignmentMatrix(s1, s2, m);
    	String sub=computeOptimalAlignment(s1, s2,  m);
    	System.out.println(sub.length());
    }
	
}
package InputOutput;
import  javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;

public  class PrintOptimalAlignment extends JFrame{
    
	private static final long serialVersionUID = 1L;

	public PrintOptimalAlignment(LinkedList<Boolean> bool,LinkedList<Character> l1, LinkedList<Character> l2 ){
		
        int n= bool.size(); //number of letters
        int m=30; //number of letters printed on each line
        int q=n/m+1; //number of lines needed to print the output
        int r=n%m; //remainder of the division algorithm

        
	    //sets a title for our window
	    setTitle("The optimal alignment");
	    //sets its size
	    setSize(35*m+10, 70*q+20);
	    //we place the object in the center of the window
	    setLocationRelativeTo(null);
	    //the red cross permits to terminate the execution
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    //Instantiation of a JPanel object
	    JPanel pan = new JPanel();
	    JScrollPane scrPane = new JScrollPane(pan);
	    add(scrPane);
	    
	    Font police = new Font("Courier", Font.BOLD,15);
	    Font small = new Font("Courier", Font.PLAIN, 8);
	    Box lines=Box.createVerticalBox();
	    int compteur=0;


	    //at each step, we create a new line
	    for (int i=0; i<q;i++){
		    
		    //we create a box
		    Box alignment=Box.createHorizontalBox();
		    for (int j=0; j<(i<q-1?m:r);j++){
		    	compteur++;
		    	Box colonne = Box.createVerticalBox();
		    	boolean b=bool.removeFirst();

			//Sets the place and the letter to print at this moment
		    	JLabel num=new JLabel("  "+String.valueOf(compteur)+"  ");
		    	num.setFont(small);
		    	JLabel letter1=new JLabel(" "+l1.removeFirst().toString()+" ");
		    	letter1.setFont(police);
		    	JLabel letter2=new JLabel(" "+l2.removeFirst().toString()+" ");
		    	letter2.setFont(police);

			//if the letters are in the longest common subsequence chosen
		    	if (b){
		    		colonne.setOpaque(true);
		    		letter1.setForeground(Color.blue);
		    		letter2.setForeground(Color.blue);
		    		colonne.setBackground(Color.yellow);
		    	}
		    	colonne.add(num);
		    	colonne.add(letter1);
		    	colonne.add(letter2);
		    	alignment.add(colonne);
		    }	
		    lines.add(alignment);
		    lines.add(Box.createRigidArea(new Dimension(0,10)));
	    }
		pan.add(lines);
        setVisible(true);  //prints the JFrame

       
    }


}
import java.io.IOException;
//import java.util.Scanner;

import InputOutput.ReadFile;
import Tasks.Task2_LongestCommonSub;
import Tasks.Task3_OptimalAlignment;
import Tasks.Task4_SubstitutionMatrices;
import Tasks.Task5_GapPenalty;
import Tasks.Task6_OptimalLocalAlignment;
import Tasks.Task7_BeginningPerfectMatches;
import Tasks.Task8_Blast;

public class Main {
	
	
	private static String L1;
	private static String L2;
	
	
	public static void task2(String name) throws IOException{
		ReadFile file=new ReadFile(name);
		L1=file.first_line;
		L2=file.second_line;
		Task2_LongestCommonSub.LongCommonSub(L1, L2);
		
    }
	
	public static void task3(String name) throws IOException{
		ReadFile file=new ReadFile(name);
		L1=file.first_line;
		L2=file.second_line;
	    
		Task3_OptimalAlignment.optimalAlignment(L1, L2);
	    
	}
	
	public static void task4(String name) throws IOException{
		
		ReadFile file=new ReadFile(name);
		L1=file.first_line;
		L2=file.second_line;
		
		Task4_SubstitutionMatrices.optimalAlignment(L1, L2);
	}
	
	public static void task5(String name) throws IOException{
		
		ReadFile file=new ReadFile(name);
		L1=file.first_line;
		L2=file.second_line;
		Task5_GapPenalty.optimalAlignment(L1, L2, 1, 10);
	    
	}
	
	public static void task6(String name) throws IOException{
		
		ReadFile file=new ReadFile(name);
		L1=file.first_line;
		L2=file.second_line;
		
		Task6_OptimalLocalAlignment.optimalAlignment(L1, L2, 1, 10);
	    
	}
	
	public static void task7(String name) throws IOException{
		
		ReadFile file=new ReadFile(name);
		L1=file.first_line;
		L2=file.second_line;
		float th=(float) 0.9;
		int k=4;
	    
		Task7_BeginningPerfectMatches.printTask7(L1, L2, th,k);
	    
	}
	
	public static void task8(String name) throws IOException{
		
		ReadFile file=new ReadFile(name);
		L1=file.first_line;
		L2=file.second_line;
		float th=(float) 0.9;
		float thl=(float) 0.1;
		int k=4;
	    
		Task8_Blast.printTask8(L1, L2, th, thl,k);
	    
	}

	public static void main(String[]args) throws IOException{
		long startTime = System.currentTimeMillis();

		//task2("SubSeq_Res320.txt");
		//task2("SubSeq_Res3275.txt");
		//task2("SubSeq_Res52.txt");
		
		//task3("SubSeq_Res320.txt");
		//task3("SubSeq_Res3275.txt");
		//task3("SubSeq_Res52.txt");
		
		//task4("SubSeq_Res320.txt");
		//task4("SubSeq_Res3275.txt");
		//task4("SubSeq_Res52.txt");
		
		//task5("SubSeq_Res320.txt");
		//task5("SubSeq_Res3275.txt");//That took 3786399 milliseconds (~1 hour) // A éviter
		task5("SubSeq_Res52.txt");
		
		//task6("SubSeq_Res320.txt");
		//task6("SubSeq_Res3275.txt");//That took 3489424 milliseconds (~1 hour) // A éviter
		//task6("SubSeq_Res52.txt");
		
		//task7("TestBlast.txt");
		//task8("TestBlast.txt");
		
		/*Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a number from 2 to 9 representing the Task : ");
		int n = reader.nextInt();
		System.out.println("Enter a file txt exact name from the library");
		String name = reader.nextLine();
		reader.close();
		if (n==2) task2(name);
		if (n==3) task3(name);
		if (n==4) task4(name);
		if (n==5) task5(name);
		if (n==6) task6(name);
		if (n==7) task7(name);
		if (n==8) task8(name);
		else System.out.println("Sorry, that's all there is");;*/
		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		
	}
	
	
	
	
	
		
	}

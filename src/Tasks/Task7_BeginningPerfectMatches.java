package Tasks;
import java.util.*;
import java.util.Map.Entry;

import AuxiliaryDataStructure.StringIndexSearchTree;
import Matrix.Blosum50;

public class Task7_BeginningPerfectMatches {
	public static float computeScore(String s,String t){
		/*computeScore computes the score between s and t using the Blosum50 matrix*/
		int n=s.length();
		float score=0;
		for (int i=0; i<n;i++){
			char c=s.charAt(i);
			char d=t.charAt(i);
			score+=Blosum50.getScore(c, d);
		}
		return score;
		
	}
	
	public static Stack<String> computeAllWords(int k){
		/*computeAllWords takes an integer k, computes from the alphabet
		 *all possible k-words and puts them in a stack with no specific order*/
		char[] alphabet=new char[]{'A','R','N','D','C','Q','E','G','H','I','L','K','M','F','P','S','T','W','Y','V'};
		Stack<String> possibleWords =new Stack<String>();
		computeAllWordsAux(k,alphabet,new String(),possibleWords);
		return possibleWords;
	}
	
	private static void computeAllWordsAux(int k, char[] alphabet, String curr,Stack<String> possibleWords) {
		/*computeAllWordsAux takes in argument :
		 * k the length of the words we are looking for
		 * alphabet the alphabet
		 * curr the current word we are building
		 * and the Stack of possible words we have already computed*/
		
		//Stops when the current word has the correct length
		if (curr.length()==k){
			possibleWords.add(curr);
		}
		//If it's not long enough, then add all possible letters at the end of the word
		else {
			for (char c:alphabet){
				computeAllWordsAux(k, alphabet, curr+c,possibleWords);
			}
		}
	}
	
	public static Stack<String> breakInto(int k,String s){
		/*breakInto takes in argument k and s and computes the stack of all 
		 * possible k-subwords from s (a k-subwords being k consecutive letter)*/
		Stack<String> w=new Stack<String>();
		int n=s.length();
		//We chose to put the k first letters on top of the stack
		//Thus we add it last
		for (int i=n; i>=k; i--) {
			w.push(s.substring(i-k,i));
		}
		return w;
	}
	
	public static Map<Integer,String> findSeeds(int k,String s, float th){
		/*findSeeds takes in argument k, s and the threshold th
		 * returns in a Map all k words that have a score with a k-subword of s 
		 * that's superior to th times the score of the subword with itself 
		 * and of the beginning index in s*/
		Map<Integer,String> seeds=new TreeMap<Integer,String>();
		Stack<String> w=Task7_BeginningPerfectMatches.breakInto(k, s);
		//We need a auxiliary Stack to save the subwords of s as we empty w
		Stack<String> waux=new Stack<String>();
		Stack<String> allPossibleWords=Task7_BeginningPerfectMatches.computeAllWords(k);
		//Loop on all possible k-words
		while (!allPossibleWords.isEmpty()){
			String possible_match=allPossibleWords.pop();
			int indexInS=0;
			//We look for a k-subword of s that matches enough with 
			//this word "possible_match"
			while (!w.isEmpty()){
				String word=w.pop();
				//If his score is high enough, it's a seed
				if (Task7_BeginningPerfectMatches.computeScore(word,possible_match)>th*Task7_BeginningPerfectMatches.computeScore(word,word)) {
					seeds.put(indexInS,possible_match);
				}
				indexInS++;
				waux.push(word);
			}
			//Once we emptied w, we reverse waux to have w back
			while (!waux.isEmpty()){
				String word=waux.pop();
				w.push(word);
			}
		
		}
		return seeds;
	}
	
	public static LinkedList<Integer> task7(String s, String t, float th, int k){
		/*Given the query sequence s, the database sequence t, the threshold th and an int k
		 * task7 computes the list of indices that are beginning of perfect (k-)matches 
		 * between a seed and a k-subword of t*/
		
		//We need to transform the Map of seeds into an iterator
		Map<Integer,String> seeds=findSeeds(k,s,th);
		Set<Entry <Integer,String>> entry_seeds=seeds.entrySet();
		Iterator<Entry <Integer,String>> it_seeds=entry_seeds.iterator();
		
		//Then we create our Binary Search Tree
		Entry <Integer,String> first=it_seeds.next();
		StringIndexSearchTree tree=new StringIndexSearchTree(first,null,null);
		
		while (it_seeds.hasNext()) tree.add(it_seeds.next());
		
		//We compute all the k-subwords of t
		Stack<String> wordsDatabase=breakInto(k,t);
		LinkedList<Integer> beginningPerfectMatches=new LinkedList<Integer>();
		//As we go through the words in the database we increment the index of the first letter 
		//of the subword considered
		//It is correct because we sorted the words from first to last in breakInto
		
		while (!wordsDatabase.isEmpty()){
			String w=wordsDatabase.pop();
			int index=tree.search(w);
			if	(index>=0) beginningPerfectMatches.add(index);
		}
		return beginningPerfectMatches;
		
	}
	
	public static void printTask7(String s, String t, float th, int k){
		System.out.println(task7(s, t, th, k));
	}
}

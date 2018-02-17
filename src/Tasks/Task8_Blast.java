package Tasks;

import java.util.*;
import java.util.Map.Entry;

import AuxiliaryDataStructure.IndexesLengthScore;
import AuxiliaryDataStructure.StringIndexSearchTree;
import Matrix.Blosum50;

public class Task8_Blast {
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
	public static LinkedList<IndexesLengthScore> findAlignments( String s, String t,float th, float thl,int k){
		/*findAlignments computes the BLAST algorithm
		 * k is the length of the subwords used
		 * s is the new sequence
		 * t is the database sequence
		 * th and thl are the threshold used in the algorithm*/
		
		//Step 1 : find the seeds of the query sequence
		//We need to transform it into an iterator
		Map<Integer,String> seeds=findSeeds(k,s,th);
		Set<Entry <Integer,String>> entry_seeds=seeds.entrySet();
		Iterator<Entry <Integer,String>> it_seeds=entry_seeds.iterator();
		
		//Then we create our Binary Search Tree
		Entry <Integer,String> first=it_seeds.next();
		StringIndexSearchTree tree=new StringIndexSearchTree(first,null,null);
		
		while (it_seeds.hasNext()) tree.add(it_seeds.next());
		
		//Step 2 : we compute the k-subwords of our database sequence
		Stack<String> wordsDatabase=Task7_BeginningPerfectMatches.breakInto(k,t);
		
		//Step 3 : we search for k-words in the database matching our seeds
		
		//When we do, we store the begin indexes of the match in both sequences
		//in IndexesPerfectMatches
		Map<Integer,Integer> IndexesPerfectMatches=new TreeMap<Integer,Integer>();
		int indexInDatabase=0;
		while (!wordsDatabase.isEmpty()){
			int indexInS=tree.search(wordsDatabase.pop());
			if	(indexInS>=0) {
				IndexesPerfectMatches.put(indexInS,indexInDatabase);
			}
			indexInDatabase++;
		}
		//We need to transform it into an iterator
		Set<Entry <Integer,Integer>> entry_set=IndexesPerfectMatches.entrySet();
		Iterator<Entry <Integer,Integer>> it=entry_set.iterator();
		
		//Step 4 : we try to extend our alignments
		
		//We store them in a list of IndexesLengthScore (see corresponding class)
		LinkedList<IndexesLengthScore> indexesAndLengthAndScore=new LinkedList<IndexesLengthScore>();
		//We need to compare our alignments' score to the score of g with itself
		float scoreOfG=Task7_BeginningPerfectMatches.computeScore(s, s);
		while (it.hasNext()){
			Entry <Integer,Integer> e=it.next();
			int indexInS=e.getKey();
			int indexInT=e.getValue();
			float curr_score=Task7_BeginningPerfectMatches.computeScore(t.substring(indexInT,indexInT+k), t.substring(indexInT,indexInT+k));
			//Our current match is the alignment of a seed and a k-subword of t (length k)
			IndexesLengthScore curr_match=new IndexesLengthScore(indexInS,indexInT,k,curr_score);
			
			//We try to extend our alignment backward and forward
			back(s,t,curr_match);
			front(s,t,curr_match);
			
			//If it has a sufficient score and hasn't already been computed, we store it
			if ((curr_score>thl*scoreOfG)&&(!indexesAndLengthAndScore.contains(curr_match))){
				indexesAndLengthAndScore.add(curr_match);
			}
		}
		return indexesAndLengthAndScore;
	}
	private static void front(String s, String t, IndexesLengthScore curr_match) {
		/*front takes two strings s and t and a current local match between them
		 * and updates curr_match until it reaches its maximal length (just from left to right*/
		int curr_length=curr_match.getLength();
		//the indices we considered are those at the end of the current match
		int i=curr_match.getIndexInS()+curr_length;
		int j=curr_match.getIndexInT()+curr_length;
		//If we didn't reach the end of one of the sequences
		if ((i<s.length())&&(j<t.length())) {
			float score=Blosum50.getScore(s.charAt(i), t.charAt(j));
			//If we benefit from adding those letters, we do it...
			if (score>0){
				curr_match.setScore(curr_match.getScore() + score);
				curr_match.setLength(curr_match.getLength() + 1);
				//and try again
				front(s,t,curr_match);
			}
		}
	}
	
	private static void back(String s, String t, IndexesLengthScore curr_match) {
		/*back takes two strings s and t and a current local match between them
		 * and updates curr_match until it reaches its maximal length (just from right to left)*/
		//We consider the beginning of our current match
		int i=curr_match.getIndexInS();
		int j=curr_match.getIndexInT();
		//We check we haven't reached the end of the sequences
		if ((i>0) &&(j>0)) {
			float score=Blosum50.getScore(s.charAt(i-1), t.charAt(j-1));
			//If we benefit from adding those letters, we do it...
			if (score>0){
				curr_match.setScore(curr_match.getScore() + score);
				curr_match.setLength(curr_match.getLength() + 1);
				curr_match.setIndexInS(curr_match.getIndexInS() - 1);
				curr_match.setIndexInT(curr_match.getIndexInT() - 1);
				//... and try again.
				back(s,t,curr_match);
			}
		}
	}
	public static void printTask8(String s, String t, float th, float thl, int k){
		LinkedList<IndexesLengthScore> fA=findAlignments(s, t, th, thl,k);
		for (IndexesLengthScore e:fA) System.out.println(e);
	}
}

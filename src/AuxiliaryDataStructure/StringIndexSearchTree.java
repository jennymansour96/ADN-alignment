package AuxiliaryDataStructure;
import java.util.Map.Entry;

public class StringIndexSearchTree {
	/*StringIndexSearchTree is a binary search tree used for tasks 7 and 8
	 * nodes are sorted by alphabetical order and the index label is the index of the beginning
	 * letter of the word in the sequence */
	String word;
	int index;
	StringIndexSearchTree left;
	StringIndexSearchTree right;
	
	/*****************Constructors*******************/
	public StringIndexSearchTree(String w,int i, StringIndexSearchTree l,StringIndexSearchTree r){
		word=w;
		index=i;
		left=l;
		right=r;
	}
	//We also create a constructor that uses a pair <Integer,String> (Entry)
	public StringIndexSearchTree(Entry<Integer,String> e, StringIndexSearchTree l,StringIndexSearchTree r){
		word=e.getValue();
		index=e.getKey();
		left=l;
		right=r;
	}
	
	/*****************Additions*******************/
	
	public void add(String w,int i){
		/*add adds the word w and the index i to the tree so that is always alphabetically sorted*/
		if (word.compareTo(w)<0){
			if (left==null){
				left=new StringIndexSearchTree(w,i,null,null);
			}
			else left.add(w,i);
		}
		else if (word.compareTo(w)>0){
			if (right==null){
				right=new StringIndexSearchTree(w,i,null,null);
			}
			else right.add(w,i);
		}
	}
	//We also create a method that uses a pair <Integer,String> (Entry)
	public void add(Entry<Integer,String> e){
		add(e.getValue(),e.getKey());
	}
	/*****************Search*******************/
	public int search(String w){
		/*search is a binary search that returns the index of the word in the sequence if present 
		 * and else returns -1 */
		if (word.equals(w)) return index;
		else if (word.compareTo(w)<0){
			if (left==null) return -1;
			else return left.search(w);
		}
		else {
			if (right==null) return -1;
			else return right.search(w);
		}
	}
}

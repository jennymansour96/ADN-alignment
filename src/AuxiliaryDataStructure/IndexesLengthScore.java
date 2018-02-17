package AuxiliaryDataStructure;

public class IndexesLengthScore {
	/*IndexesLengthScore is a custom class that sotres local alignment
	 * indexInS is the index of the beginning of the alignment in the first sequence
	 * indexInT is the index of the beginning of the alignment in the second sequence
	 * length is the length of this alignment
	 * score is the score of this alignement according the Blosum50 matrix*/
	private int indexInS;
	private int indexInT;
	private int length;
	private float score;
	public IndexesLengthScore(int i,int j, int l, float s){
		setIndexInS(i);
		setIndexInT(j);
		setLength(l);
		setScore(s);
	}
	//We need to redefine the equals method from our custom class
	@Override
	public boolean equals(Object other){
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof IndexesLengthScore))return false;
	    IndexesLengthScore ils = (IndexesLengthScore)other;
	    //If the three first parameters are equal, the score will be too
	    //No need to check
		return (getIndexInS()==ils.getIndexInS())&&(getIndexInT()==ils.getIndexInT())&&(getLength()==ils.getLength());
	}
	public String toString(){
		return "Index in Database : "+getIndexInT()+"; Index in Query Seq : "+getIndexInS()+"; Length : "+getLength()+"\n" ;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getIndexInS() {
		return indexInS;
	}
	public void setIndexInS(int indexInS) {
		this.indexInS = indexInS;
	}
	public int getIndexInT() {
		return indexInT;
	}
	public void setIndexInT(int indexInT) {
		this.indexInT = indexInT;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
}

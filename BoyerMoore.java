import java.util.*;

public class BoyerMoore{

	private char[] haystack;
	private char[] needle;
	private int hLength;
	private int nLength;
	public HashMap<Character,ArrayList<Integer>> occurences;
	public int[] suffixe;
	public int[] prefixe;


	public BoyerMoore(char[] s1, char[] s2, int l1, int l2){

		this.haystack = s1;
		this.needle = s2;
		this.hLength = l1;
		this.nLength = l2;

		this.occurences = new HashMap<Character,ArrayList<Integer>>();

		this.suffixe = new int[l2];
		this.prefixe = new int[l2];

    System.out.println("Search using Boyer-Moore algorithm");
    System.out.println("Haystack has size: " + hLength);
    System.out.println("Needle has size: " + nLength);

	}

	public void setOccurences(){
		for(int i=nLength-1; i>=0; i--){
			ArrayList<Integer> l;
			if (occurences.containsKey(needle[i])) {
				l = occurences.get(needle[i]);
			}
			else {
				l = new ArrayList<Integer>();
				occurences.put(needle[i],l);
			}
			l.add(i);
		}

	}

	public void setSuffixe(){

		for(int i=0; i<nLength; i++){
			for(int j=0; j<nLength; j++){
				boolean ok = true;
				int max_k = Math.max(i-1, nLength-1);
				for(int k=0; k<=max_k; k++){
					if(needle[nLength-1-k] != needle[nLength-1-j-k])
						ok = false;
				}
				if(ok){
					suffixe[nLength-1-i] = nLength-1-j-i;
					break;
				}
			}
		}
 	}

	public int search(){

		int offset = 0;
		int comparedIndex = nLength-1;
		int shift;
		int mismatch;
		char car;
		int nextSuffix;

		setOccurences();
		setSuffixe();

		while(offset<=hLength-nLength){


			//System.out.println(offset);

			mismatch=compare(offset);
			if(mismatch==-1)
				return offset;

			//check bad character


			shift = mismatch+1; //dans le cas ou le caractere de haystack nest pas dans needle

			car = haystack[offset+mismatch];

			ArrayList<Integer> l = occurences.get(car);
			if (l != null){
				for(int index : l){

					if(index<mismatch){
						shift = mismatch-index;
						break;
					}
				}
			}


			//check good suffix

			nextSuffix = suffixe[mismatch];

			// if(nextSuffix==-1){
			// 	nextSuffix = -(nLength-mismatch-prefixe[mismatch]);
			// }

			if(shift<mismatch-nextSuffix){
				shift=mismatch-nextSuffix;
			}
			offset = offset+shift;
		}
		return -1;
	}

	public int compare(int offset){
	// compare la chaine needle avec haystack de droite à gauche

		for(int i=nLength-1; i>=0;i--){
			if(needle[i]!=haystack[i+offset])
				return(i);
		}

		return -1;

	}

}

import java.util.*;

public class BoyerMoore{

	private char[] haystack;
	private char[] needle;
	private int hLength;
	private int nLength;
	public ArrayList<ArrayList<Integer>> occurrences;
	public int[] suffixe;
	public int[] prefixe;
        final private int MAX_ALPHABET_SIZE = 1111998;
	
	public BoyerMoore(char[] s1, char[] s2, int l1, int l2){
	
		this.haystack = s1;
		this.needle = s2;
		this.hLength = l1;
		this.nLength = l2;
		
		this.occurrences = new ArrayList<ArrayList<Integer>>();
			
		this.suffixe = new int[l2];
		this.prefixe = new int[l2];
		
	
	}
	
	public void setOccurrences(){
	
                for (int i =0; i< MAX_ALPHABET_SIZE; i++) {
                    occurrences.add(new ArrayList<Integer>());
                }
		for(int i=nLength-1; i>=0; i--){
			occurrences.get(needle[i]).add(i);
		}
		
	}
	
	public void setSuffixe(){
	
	//rechercher le premier emplacement en partant de droite d'une sschaine egale suffixe a partir de i
	
		int nextIndex = nLength-1;
		int[] longSuffixCom = new int[nLength];
		int[] reponsePossible = new int[nLength];
		
		//set reponsePossible : donne a l'indice j la longueur du suffixe commun, needle[j] exclu.
		
		for(int i=0; i<nLength; i++)
			suffixe[i] = -1;
		
		for(int i=nLength-2; i >= 0; i--){
			
			if(needle[i]==needle[nextIndex]){
				nextIndex--;
				longSuffixCom[i] = longSuffixCom[i+1]+1;
			}
			
			else{
				nextIndex = nLength-1;
				reponsePossible[i]= longSuffixCom[i+1];
			}
			
		}
		
	//	int longueurActuelle = 0;
	//	int minIndiceTraite = nLength;
		
		for(int j=nLength-2; j>=0; j--){
			
			if(suffixe[nLength-1-reponsePossible[j]]<0)
				suffixe[nLength-1-reponsePossible[j]] = j;
				
		}
		
	//la ou on a des -1 il faut remplir avec autchose.
	
		
	
	}
	
	public int search(){
	
		int offset = 0;
		int comparedIndex = nLength-1;
		int shift;
		int mismatch;
		char Char;
		int nextSuffix;
		
		while(offset<=hLength-nLength){
		
			shift=1;
			
			mismatch=compare(offset);
			if(mismatch==-1)
				return offset;
				
			
			//check bad character
			shift = mismatch+1; //dans le cas ou le caractere de haystack nest pas dans needle
			
	//		Char = haystack[offset+mismatch];
	//		int value = (int)Char;
	//		
	//		for(int index : occurrences.get(value){
				
	//			if(index<mismatch){
	//				shift = mismatch-index;
	//				break;
	//			}
	//		}
			
			//check good suffix
			nextSuffix = suffixe[mismatch];
			if(nextSuffix==-1){
				nextSuffix=prefixe[mismatch];
			}
			if(shift<mismatch-nextSuffix)
				shift=mismatch-nextSuffix;
			
			
			
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

import java.util.*;

public class BoyerMoore{

	private char[] haystack;
	private char[] needle;
	private int hLength;
	private int nLength;
	private LinkedList[] occurrences;
	private int sizeAlphabet;
	public int[] suffixe;
	
	public BoyerMoore(char[] s1, char[] s2, int l1, int l2, int sizeAlphabet){
	
		this.haystack = s1;
		this.needle = s2;
		this.hLength = l1;
		this.nLength = l2;
		this.sizeAlphabet = sizeAlphabet;
		
		this.occurrences = new LinkedList[sizeAlphabet];
		
		for(int i=0; i<sizeAlphabet; i++)
			occurrences[i] = new LinkedList();
			
		this.suffixe = new int[l2];
		
	
	}
	
	public void setOccurrences(){
		
		for(int i=0; i<nLength; i++){
			occurrences[needle[i]].addFirst(i);
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
		
		for(int i=nLength-2; i>=0; i--){
			
			if(needle[i]==needle[nextIndex]){
				nextIndex--;
				longSuffixCom[i] = longSuffixCom[i+1]+1;
			}
			
			else{
				nextIndex = nLength-1;
				reponsePossible[i]= longSuffixCom[i+1];
			}
			
		}
		
		int longueurActuelle = 0;
		int minIndiceTraite = nLength;
		
		for(int j=nLength-2; j>=0; j--){
			
			if(suffixe[nLength-1-reponsePossible[j]]<0)
				suffixe[nLength-1-reponsePossible[j]] = j;
				
		}
		
	
	}
	
	
	

}
public class KnuthMorrisPratt{

	private char[] haystack;
	private char[] needle;
	private int[] next;
	
	
	public KnuthMorrisPratt(char[] s1, char[] s2){
		
		this.haystack = s1;
		this.needle = s2;
		this.next = new int[s2.length];
		
	}
	
	public void setNext(char[] s){
		//remplir le tableau next
		
		int nextIndex=0;
		int[] lengthMaxPrefix = new int[s.length];
		
		for(int i=1;i<s.length;i++){
			
			if(s[i]==s[nextIndex]){
				
				lengthMaxPrefix[i]=lengthMaxPrefix[i-1]+1;
				nextIndex++;
			}
			
			else{
				next[i]=lengthMaxPrefix[i-1];
				nextIndex=0;
			}
			
		}
		return;
		
	}
	
	public int compare(int position, int debutRecherche){
		//compares needle with haystack at position position, beginning at character debutRecherche
		//returns index of first mismatch, else -1
		
		for(int i=debutRecherche; i<needle.length; i++){
			
			if(needle[i]!=haystack[i+position])
				return(i);
			
		}
		
		return -1;
	
	}
	
	public int search(){
		int debutRecherche = 0;
		int position = 0;
		int maxPos = haystack.length-needle.length+1;
		int mismatch;
		
		setNext(needle);

		
		while(position<=maxPos){
						
			mismatch = compare(position, debutRecherche);
			
			if(mismatch==-1)
				return(position);
			
			else if(mismatch==0){
				position++;
				debutRecherche=0;
			}
			else{
				position=position+mismatch-next[mismatch];
				debutRecherche=next[mismatch];
			}
			
		}
		
		return -1;
	}

}
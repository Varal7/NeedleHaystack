public class KnuthMorrisPratt{

	private char[] haystack;
	private char[] needle;
	private int hLength;
	private int nLength;
	private int[] next;

	public KnuthMorrisPratt(char[] s1, char[] s2, int l1, int l2) {
		this.haystack = s1;
		this.needle = s2;
		this.hLength = l1;
		this.nLength = l2;
		this.next = new int[nLength];
		setNext();
		System.out.println("Search using Knuth-Morris-Pratt algorithm");
		System.out.println("Haystack has size: " + hLength);
		System.out.println("Needle has size: " + nLength);
	}

	public void setNext(){
		//fills the array next
		int nextIndex=0;
		int[] lengthMaxPrefix = new int[nLength];

		for(int i=1;i<nLength;i++) {
			if(needle[i]==needle[nextIndex]){
				lengthMaxPrefix[i]=lengthMaxPrefix[i-1]+1;
				nextIndex++;
			}	else {
				next[i]=lengthMaxPrefix[i-1];
				nextIndex=0;
			}
		}
		return;
	}

	public int compare(int position, int debutRecherche){
		//compares needle with haystack at position position, beginning at character debutRecherche
		//returns index of first mismatch, else -1

		for(int i=debutRecherche; i<nLength; i++) {
			if(needle[i]!=haystack[i+position])
				return(i);
		}
		return -1;
	}

	public int search() {
		int debutRecherche = 0;
		int position = 0;
		int maxPos = hLength-nLength+1;
		int mismatch;

		while(position<=maxPos){
			mismatch = compare(position, debutRecherche);
			if (mismatch==-1)
				return(position);
			else if(mismatch==0) {
				position++;
				debutRecherche=0;
			} else {
				position=position+mismatch-next[mismatch];
				debutRecherche=next[mismatch];
			}
		}
		return -1;
	}
}

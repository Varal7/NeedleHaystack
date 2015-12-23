public class KarpRabin {
  /*
   * This class implements KarpRabin algorithm described for question 1
   */
  private char[] haystack;
  private char[] needle;
  private int hLength;
  private int nLength;
  private int w;
  private int last_hash;
  private int powerOfTwo;
  private int needle_hash;


  public KarpRabin(char[] s1, char[] s2, int l1, int l2) {
    this.haystack = s1;
    this.needle = s2;
    this.hLength = l1;
    this.nLength = l2;
    w = 3000000;
    last_hash = -1;
    powerOfTwo = twoToThe(nLength-1);
    needle_hash = hash(needle);
    System.out.println("Search using Karp-Rabin algorithm");
    System.out.println("Haystack has size: " + hLength);
    System.out.println("Needle has size: " + nLength);
  }



  private int hash(char[] s) {
    /**
     *Calculates the hash of a char array s
     * Will stop at nLength'th character
     */
    int result=0;
    for (int i = 0; i < nLength; i++) {
      result *= 2;
      result += s[i];
      result %= w;
    }
    return result;
  }

  private int hash_index(int index) {
    /**
     * Calculaltes the hash of substring of haystack starting at index
     * and with length nLength.
     * Assumes that previous hash is stored in last_hash
     */
    if (index==0) {
      return last_hash = hash(haystack);
    }
    return last_hash = ((last_hash - haystack[index-1]*powerOfTwo)*2 + haystack[index+nLength-1])%w;
  }

  public int search() {
    /* Returns index of first appearance of needle in haystack.
     * Returns -1 if not exists
     */
    for (int i = 0; i + nLength <= hLength ; i++) {
      if (checkHash(i) && checkPos(i)) {
        return i;
      }
    }
    return -1;
  }

  private int twoToThe(int exp) {
    if (exp == 0) {
      return 1;
    }
    int previous = twoToThe(exp/2);
    if (exp%2 == 0) {
      return previous*previous;
    }
    return previous*previous*2;
  }

  private boolean checkHash(int pos) {
    return (needle_hash == hash_index(pos));
  }

  private boolean checkPos(int pos) {
    System.out.println("Checking one candidate at pos:" + pos);
     for (int i = 0; i < nLength; i++) {
       if (needle[i] != haystack[pos+i]) {
         return false;
       }
     }
     return true;
  }

}

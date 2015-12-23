public class Naive {
  /*
   * This class implements the naive algorithm described for question 1
   */
   private char[] haystack;
   private char[] needle;
   private int hLength;
   private int nLength;

   public Naive(char[] s1, char[] s2, int l1, int l2) {
     this.haystack = s1;
     this.needle = s2;
     this.hLength = l1;
     this.nLength = l2;
     System.out.println("Search using naive algorithm");
     System.out.println("Haystack has size: " + hLength);
     System.out.println("Needle has size: " + nLength);
   }

   public int search() {
     /* Returns index of first appearance of needle in haystack.
      * Returns -1 if not exists
      */
     for (int i = 0; i + nLength <= hLength ; i++) {
       if (checkPos(i)) {
         return i;
       }
     }
     return -1;
   }
   private boolean checkPos(int pos) {
      for (int i = 0; i < nLength; i++) {
        if (needle[i] != haystack[pos+i]) {
          return false;
        }
      }
      return true;
   }
}

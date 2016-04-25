    import java.io.File;
import java.nio.ByteBuffer;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.lang.Math;


public class Main {

    public static final int precedingChars = 50; //Only used for esthetic purposes
    public static final int displayedChars = 500; //Only used for esthetic purposes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";


    public static void main (String[] args) {
        String hFileName = "source.txt"; //Text file to search in
        String nFileName = "query.txt"; //Text file to search for
        if (args.length >=1) {
            hFileName = args[0];
        }
        if (args.length >=2) {
            nFileName = args[1];
        }

        char[] haystack  = readFile(hFileName);
        char[] needle = readFile(nFileName);
        int hLength = haystack.length;
        int nLength = needle.length;

        if (haystack[hLength-1] == '\n'){
          hLength--;
//          System.out.println("Removed Trailing newline");
        }
        if (needle[nLength-1] == '\n'){
          nLength--;
//          System.out.println("Removed Trailing newline");
        }

        long t0, tf;
        int result;


        t0 = System.currentTimeMillis();
	    result = new Naive(haystack, needle,hLength,nLength).search();
        tf = System.currentTimeMillis();

         System.out.println("Time : " +(tf -t0) +"ms") ;
        if (result == -1) {
          System.out.println("Needle not found");
        }
        else {
          System.out.println("Needle found at index: " + result);
          printComparisons(result, haystack, needle,hLength,nLength);
        }

        t0 = System.currentTimeMillis();
        result = new KarpRabin(haystack, needle,hLength,nLength).search();
        tf = System.currentTimeMillis();

         System.out.println("Time : " +(tf -t0) +"ms") ;
        if (result == -1) {
          System.out.println("Needle not found");
        }
        else {
          System.out.println("Needle found at index: " + result);
          printComparisons(result, haystack, needle,hLength,nLength);
        }

        t0 = System.currentTimeMillis();
        result = new KnuthMorrisPratt(haystack, needle,hLength,nLength).search();
        tf = System.currentTimeMillis();

         System.out.println("Time : " +(tf -t0) +"ms") ;
        if (result == -1) {
          System.out.println("Needle not found");
        }
        else {
          System.out.println("Needle found at index: " + result);
          printComparisons(result, haystack, needle,hLength,nLength);
        }

        t0 = System.currentTimeMillis();
        result = new BoyerMoore(haystack, needle,hLength,nLength).search();
        tf = System.currentTimeMillis();

         System.out.println("Time : " +(tf -t0) +"ms") ;
        if (result == -1) {
          System.out.println("Needle not found");
        }
        else {
          System.out.println("Needle found at index: " + result);
          printComparisons(result, haystack, needle,hLength,nLength);
        }

    }

    static char[] readFile(String fileName) {
           /**
            * Reads file and returns array of chars
            **/
        File file = new File(fileName);
        long size = file.length();
        ByteBuffer buffer;
        try {
        buffer = new FileInputStream(file).getChannel().map(MapMode.READ_ONLY, 0, size);
        } catch (IOException e) {
        throw new IllegalArgumentException(e.getMessage());
        }
        CharBuffer charBuffer = Charset.defaultCharset().decode(buffer);
        char[] charArray = new char[charBuffer.limit()];
        charBuffer.get(charArray);
        return charArray;
        }

      static void printComparisons(int index, char[] haystack, char[] needle,int hLength,int nLength) {
        /**
         * Displays result of search in a beautiful way
         *
         **/
         int start = Math.max(0,index-precedingChars);
         int bodyLength = Math.min(nLength, displayedChars-precedingChars);
         int end = Math.min(hLength, start+displayedChars);
         for (int i = start; i < index; i++)
           System.out.print(haystack[i]);
         System.out.print(ANSI_GREEN);
         for (int i = index; i < index+bodyLength; i++)
           System.out.print(haystack[i]);
         System.out.print(ANSI_RESET);
         for (int i = index+bodyLength; i < end ; i++)
           System.out.print(haystack[i]);
         System.out.println("");
      }
}

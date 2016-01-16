import java.io.File;
import java.nio.ByteBuffer;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.lang.*;
import java.util.Random;

public class Test2{


	public static int nombreTests = 100;
	public static int nLength = 15000;

	public static void main(String[] args){


		char[][] needles = new char[nombreTests][];

		String hFileName = args[0];

		char[] haystack = Main.readFile(hFileName);
		int hLength = haystack.length;


		//tableau des durees
    char[][] needleLists = new char[nombreTests][nLength];


		for (int iTest=0; iTest < nombreTests; iTest++){
                        Random rg = new Random();
			double r = rg.nextDouble();
			int debut = (int) (r*(hLength-nLength));
			for(int iCar = 0; iCar<nLength; iCar++){
				needleLists[iTest][iCar]=haystack[debut+iCar];
			}
    }

    long t0, tf, tunit0, tunit1;
		int result;
		int count_naive = 0;
		int count_kmp = 0;
		int count_kr = 0;
		int count_bm = 0;


		t0 = System.currentTimeMillis();
    for (int iTest = 0; iTest< nombreTests; iTest++) {
			tunit0 = System.currentTimeMillis();
    	result=new Naive(haystack, needleLists[iTest], hLength, nLength).search();
			tunit1 = System.currentTimeMillis();
			System.out.println(tunit1 - tunit0);
			if (result == -1) {
				  System.out.print("Needle was :");
					for (int i = 0; i < nLength; i++) {
						System.out.print(needleLists[iTest][i]);
					}
          System.out.println(" - Not found");
					count_naive++;
      }
    }
		tf = System.currentTimeMillis();
		long naive = tf-t0;


		t0 = System.currentTimeMillis();
    for (int iTest = 0; iTest< nombreTests; iTest++) {
			tunit0 = System.currentTimeMillis();
    	result=new KnuthMorrisPratt(haystack, needleLists[iTest], hLength, nLength).search();
			tunit1 = System.currentTimeMillis();
			System.out.println(tunit1 - tunit0);
			System.out.println(result);
      if (result == -1) {
				  System.out.print("Needle was :");
					for (int i = 0; i < nLength; i++) {
						System.out.print(needleLists[iTest][i]);
					}
          System.out.println(" - Not found");
					count_kmp++;
      }
    }
		tf = System.currentTimeMillis();
		long knuthmorrispratt = tf-t0;

		t0 = System.currentTimeMillis();
		for (int iTest = 0; iTest< nombreTests; iTest++) {
			tunit0 = System.currentTimeMillis();
			result=new KarpRabin(haystack, needleLists[iTest], hLength, nLength).search();
			tunit1 = System.currentTimeMillis();
			System.out.println(tunit1 - tunit0);
			System.out.println(result);

			if (result == -1) {
				  System.out.print("Needle was :");
					for (int i = 0; i < nLength; i++) {
						System.out.print(needleLists[iTest][i]);
					}
          System.out.println(" - Not found");
					count_kr++;
      }
		}
		tf = System.currentTimeMillis();
		long karprabin = tf-t0;

		t0 = System.currentTimeMillis();
		for (int iTest = 0; iTest< nombreTests; iTest++) {
			tunit0 = System.currentTimeMillis();
			result=new BoyerMoore(haystack, needleLists[iTest], hLength, nLength).search();
			tunit1 = System.currentTimeMillis();
			System.out.println(tunit1 - tunit0);
			System.out.println(result);
			if (result == -1) {
				  System.out.print("Needle was :");
					for (int i = 0; i < nLength; i++) {
						System.out.print(needleLists[iTest][i]);
					}
          System.out.println(" - Not found");
					count_bm++;
      }
		}
		tf = System.currentTimeMillis();
		long boyermoore = tf-t0;

		System.out.println("Naive took: " + naive +"ms but did not find: " + count_naive + "needles");
		System.out.println("Knuth-Morris-Pratt took: " + knuthmorrispratt + "ms but did not find: " + count_kmp + "needles");
		System.out.println("Karp-Rabin took: " + karprabin +"ms but did not find: " + count_kr + "needles");
		System.out.println("Boyer-Moore took: " + boyermoore +"ms but did not find: " + count_bm + "needles");

	}
}

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        char[] needle = {'a','l','p','h','a','b','e','t'};
        int length = needle.length;
        ArrayList<ArrayList<Integer>> occurences = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 1111998; i ++) {
            occurences.add(new ArrayList<Integer>());
        }

         for (int i = 0; i < length; i++) {
            char c = needle[i];
            int value = (int)c;
            //System.out.println(value);
            (occurences.get(value)).add(i);
         }

         for (int i = 0; i < 1111998; i ++) { //This is only for testing purposes
             ArrayList<Integer> cur_list = occurences.get(i); 
             if (!cur_list.isEmpty()) {
                 int l = (cur_list.toArray()).length;
                 for (int j = 0; j<l; j++) {
                 System.out.println((char)i+":"+cur_list.get(j)+" ");
                 }
             }
         }
    }
}

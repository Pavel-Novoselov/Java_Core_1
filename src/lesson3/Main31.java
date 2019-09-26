package lesson3;

import java.util.HashMap;

public class Main31 {
    public static void main(String[] args) {
        String [] arrS = new String[]{"One", "Two", "Three", "One", "Two", "Four", "Five", "Six", "Five", "Five", "Seven", "Five", "One", "Eight","Nine", "Ten", "Two", "Five", "Four", "Ten"};
        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < arrS.length; i++) {
            String word = arrS[i];
            Integer count = hm.get(word);
            hm.put(word, count==null ? 1 : count+1);
        }
        System.out.println(hm);
    }

}

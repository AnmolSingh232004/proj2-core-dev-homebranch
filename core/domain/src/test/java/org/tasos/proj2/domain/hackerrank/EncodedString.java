package org.tasos.proj2.domain.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;

public class EncodedString {

    // Create an array with each index matching to alphabet letters.
    // Each index value should contain the frequency of letter appearances in a string.

    // Note: IF there's a #, calculate the two chars before, for example in 1226#24#, 26# matches to z.
    // 12(2)26#24# This case has (2) meaning consecutive number of instances of the "2" / b  before.

    public static void main(String[] args) {

        String s = "1(3)226#24#(2)2(4)";

        int[] result = new int[26];

        HashMap<Integer, List<Integer>> charNumbersMap = new HashMap<>();
        List<Integer> indexesPerChar = new ArrayList<>();

        for (int charNumber=1; charNumber<27; charNumber++) {

            for (int i=0; i<s.length(); i++) {
                if (Integer.valueOf(s.charAt(i)) == charNumber)  {
                    if (s.charAt(i+1) != ')') {
                        if (s.charAt(i+3) != '#') {
                            indexesPerChar.add(charNumber);
                        }
                    }
                }
            }

            charNumbersMap.put(charNumber, indexesPerChar);
        }

        for (Map.Entry<Integer, List<Integer>> entry : charNumbersMap.entrySet()) {
            Integer charNumber = entry.getKey();
            List<Integer> indexes = entry.getValue();
            for (Integer idx : indexes) {

                if (s.charAt(idx+1) == '(') {
                    int incr = s.charAt(idx+2);
                    result[charNumber-1] = result[charNumber-1] + incr;
                } else {
                    result[charNumber-1]++;
                }
            }
        }



    }
}

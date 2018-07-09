package com.bparent.dojo.dojoSpring.util;

public class StringUtils {

    public static int countOccurence(String str, char c) {
        String lowerCaseString = str.toLowerCase();
        char lc = Character.toLowerCase(c);
        int count = 0;
        for (int i = 0; i < lowerCaseString.length(); i++) {
            if (lowerCaseString.charAt(i) == lc) {
                count++;
            }
        }
        return count;
    }

}

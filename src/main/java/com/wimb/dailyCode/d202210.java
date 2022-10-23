package com.wimb.dailyCode;

public class d202210 {

    /**
     * 1768. 交替合并字符串
     * @param word1
     * @param word2
     * @return
     */
    public String mergeAlternately(String word1, String word2) {
        StringBuffer sb = new StringBuffer();
        int i = 0, j = 0;
        int len1 = word1.length();
        int len2 = word2.length();
        for (; i < len1 && j < len2; ++i, ++j) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(j));
        }
        if (i < word1.length()) {
            sb.append(word1.substring(i));
        }
        if (j < word2.length()) {
            sb.append(word2.substring(j));
        }
        return sb.toString();
    }
}
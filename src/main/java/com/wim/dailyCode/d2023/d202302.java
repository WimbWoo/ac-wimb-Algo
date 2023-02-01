package com.wim.dailyCode.d2023;

import java.util.HashMap;
import java.util.Map;

public class d202302 {
    public static void main(String[] args) {

    }
    /**
     * 2325. 解密消息
     */
    public String decodeMessage(String key, String message) {
        char c = 'a';
        Map<Character, Character> map = new HashMap();
        for (int i = 0; i < key.length();i++) {
            char cur = key.charAt(i);
            if (cur != ' ' && !map.containsKey(key.charAt(i))) {
                map.put(cur, c);
                c++;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < message.length();i++) {
            sb.append(map.get(message.charAt(i)));
        }
        return sb.toString();
    }
}

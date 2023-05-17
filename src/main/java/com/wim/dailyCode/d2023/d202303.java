package com.wim.dailyCode.d2023;

import java.util.*;

public class d202303 {

    public static void main(String[] args) {
        System.out.println("02-01".compareTo("04-00"));
    }

    public String lastSubstring(String s) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                list.add(s.substring(i, j));
            }
        }
        Collections.sort(list);
        return list.get(list.size() - 1);
    }
}

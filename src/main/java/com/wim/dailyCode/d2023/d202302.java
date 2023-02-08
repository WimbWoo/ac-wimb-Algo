package com.wim.dailyCode.d2023;

import java.util.*;

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

    /**
     * 1604. 警告一小时内使用相同员工卡大于等于三次的人
     */
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> nameMapToTime = new HashMap<>();
        // 1. put into map <Name List<Time>>
        for (int i = 0; i < keyName.length; i++) {
            String hour = keyTime[i].substring(0, 2);
            String min = keyTime[i].substring(3);
            int time = Integer.parseInt(hour) * 60 + Integer.parseInt(min);
            nameMapToTime.computeIfAbsent(keyName[i], t -> new ArrayList<>()).add(time);
        }
        // 2.compare time to find answer
        List<String> res = new ArrayList<>();
        for (String name : nameMapToTime.keySet()) {
            List<Integer> timesList = nameMapToTime.get(name);
            int size = timesList.size();
            Collections.sort(timesList);
            if (size > 2) {
                for (int i = 2; i < size; i++) {
                    if (timesList.get(i) - timesList.get(i - 2) <= 60) {
                        res.add(name);
                        break; // remove duplicate name
                    }
                }
            }
        }
        Collections.sort(res);
        return res;
    }

    /**
     * 1233. 删除子文件夹
     * method1: sort
     * method2: 字典树
     */
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);
        for (int i = 1; i < folder.length; ++i) {
            int m = ans.get(ans.size() - 1).length();
            int n = folder[i].length();
            if (m >= n || !(ans.get(ans.size()-1).equals(folder[i].substring(0, m)) && folder[i].charAt(m) == '/')) {
                ans.add(folder[i]);
            }
        }
        return ans;
    }
}

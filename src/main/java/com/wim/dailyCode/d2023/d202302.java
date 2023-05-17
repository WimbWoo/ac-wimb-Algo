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

    /**
     * 1138. 字母板上的路径
     */
    public String alphabetBoardPath(String target) {
        int cx = 0, cy = 0;
        StringBuffer res = new StringBuffer();
        for (int i = 0;i < target.length();i++) {
            char cur = target.charAt(i);
            int nx = (cur - 'a') / 5;
            int ny = (cur - 'a') % 5;
            if (nx < cx) {
                for (int j = 0; j < cx - nx; j++) {
                    res.append('U');
                }
            }
            if (ny < cy) {
                for (int j = 0; j < cy - ny; j++) {
                    res.append('L');
                }
            }
            if (nx > cx) {
                for (int j = 0; j < nx - cx; j++) {
                    res.append('D');
                }
            }
            if (ny > cy) {
                for (int j = 0; j < ny - cy; j++) {
                    res.append('R');
                }
            }
            res.append("!");
            cx = nx;
            cy = ny;
        }
        return res.toString();
    }

    /**
     * 1234. 替换子串得到平衡字符串
     * 滑动窗口题解
     * https://leetcode.cn/problems/replace-the-substring-for-balanced-string/solutions/2108942/javahua-dong-chuang-kou-de-fan-xiang-si-2dz8w/
     */
    public int balancedString(String s) {
        int []counts = new int[26];
        int len = s.length();
        int limit = len / 4;
        int left = 0;
        int right = -1;
        int minLen = len;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length;i++) {
            counts[charArray[i] - 'A']++;
        }
        while (left < len) {
            if (checkNoNeedChange(counts, limit)) {
                minLen = Math.min(minLen, right - left + 1);
                counts[s.charAt(left++) - 'A']++;
            } else if (right < len - 1) {
                counts[s.charAt(++right) - 'A']--;
            } else {
                break;
            }
        }
        return minLen;
    }

    // 判断 counts 数组是否对应不可替换字符串
    public boolean checkNoNeedChange(int counts[], int limit) {
        if(counts['Q' - 'A'] > limit || counts['W' - 'A'] > limit || counts['E' - 'A'] > limit && counts['R' - 'A'] > limit) {
            return false;
        }
        return true;
    }

    /**
     * 2341. 数组能形成多少数对
     */
    public int[] numberOfPairs(int[] nums) {
        Arrays.sort(nums);
        int l = 0, r = 0;
        int left = 0, right = 0;
        int pair = 0, count = 0;
        int curCount = 0;
        while (left < nums.length) {
            while (right < nums.length && nums[right] == nums[left]) {
                right++;
            }
            curCount = right - left;
            pair +=  curCount/2;
            if (curCount % 2 == 1) {
                count++;
            }
            left = right;
        }
        return new int[]{pair, count};
    }
}

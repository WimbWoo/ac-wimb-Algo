package com.wim.dailyCode;

import java.util.*;

public class d202209 {
    public static void main(String[] args) {
        System.out.println(rotatedDigits2(10));
    }

    /**
     * 698. 划分为k个相等的子集
     * 深搜 + 剪枝
     */
    int nums[];
    int len, groupSum, k;
    public boolean canPartitionKSubsets(int[] nums, int k) {
       nums = nums; k = k;

       int totalSum = 0;
       for (int x : nums) totalSum += x;
       Arrays.sort(nums);
       if (totalSum % k != 0) return false; // feasible pruning: totalSum cannot be divide

       len = nums.length;
       groupSum = totalSum / k;
       if (nums[len - 1] > groupSum) return false;
       return dfs(len - 1, 0, 0, new boolean[len]);
    }

    /**
     *
     * @param idx 搜索至当前位置
     * @param cur 当前路径综合
     * @param cnt 组成group的组的个数
     * @param visited 记录nums[]数组中各个数字是否已经被选择
     */
    private boolean dfs(int idx, int cur, int cnt, boolean[] visited) {
        if (cnt == k) return true;
        if (cur == groupSum) return dfs(len - 1, 0, cnt + 1, visited);
        for (int i = idx; i >= 0; i--) {
            if(visited[i] || cur + nums[i] > groupSum) continue;
            visited[i] = true;
            // check whether left num can be divide into a group
            if (dfs(i - 1, cur + nums[i], cnt, visited)) return true;
            visited[i] = false;
        }
        return false;
    }


    /**
     * 854. 相似度为 K 的字符串
     * 朴素BFS：求解 s1 转换为 s2 的最小转换次数 k
     */
    public int kSimilarity(String s1, String s2) {
        Deque<String> q = new ArrayDeque<>();
        Set<String> vis = new HashSet<>();
        q.offer(s1);
        vis.add(s1);
        int ans = 0;
        while (true) {
            for (int i = q.size(); i > 0; i--) {
                String s = q.pollFirst();
                if (s.equals(s2)) {
                    return ans;
                }
                for (String nxt : next(s, s2)) {
                    if (!vis.contains(nxt)) {
                        q.offer(nxt);
                        vis.add(nxt);
                    }
                }
            }
            ++ans;
        }
    }

    private List<String> next(String s, String s2) {
        int i = 0, n = s.length();
        char[] cs = s.toCharArray();
        List<String> res = new ArrayList<>();
        for (; cs[i] == s2.charAt(i); i++){}
        for (int j = i + 1; j < n; j++) {
            if (cs[i] == s2.charAt(j) && cs[j] != s2.charAt(j) ) {
                swap(cs, i, j);
                res.add(new String(cs));
                swap(cs, i, j);
            }
        }
        return res;
    }

    private void swap(char[] ch, int i, int j){
        char t = ch[i];
        ch[i] = ch[j];
        ch[j] = t;
    }

    /**
     * 788. 旋转数字
     * 方法一：Base Solution
     */
    public static int rotatedDigits(int n) {
        int sum = 0;
        for (int i = 1; i <=n; i++) {
            if (reserve(i)) sum++;
        }
        return sum;
    }

    public static boolean reserve(int num) {
        int reserve_num = 0;
        int pow = 1;
        int tmp = num;
        while (num != 0) {
            int cur = num % 10;
            num /= 10;
            if (cur == 3 || cur == 4 || cur == 7) return false;
            reserve_num += getReserve(cur) * pow;
            pow *= 10;
        }
        return reserve_num != tmp;
    }

    public static int getReserve(int n) {
        switch (n) {
            case 0: return 0;
            case 1: return 1;
            case 2: return 5;
            case 5: return 2;
            case 6: return 9;
            case 8: return 8;
            case 9: return 6;
        }
        return -1;
    }

    /**
     * 方法二：找规律
     * 1）不出现 3 4 7
     * 2）至少出现一次 2 5 6 9
     */
    public static int rotatedDigits2(int n) {
        int []check = {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            String cur = "" + i;
            boolean isValid = true, isDiff = false;
            for (int j = 0; j < cur.length(); j++) {
                char c = cur.charAt(j);
                if (check[c - '0'] == -1) {
                    isValid = false;
                } else if (check[c - '0'] == 1) {
                    isDiff = true;
                }
            }
            if (isValid && isDiff) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * 方法三：数位DP模板题（补充）
     */
}

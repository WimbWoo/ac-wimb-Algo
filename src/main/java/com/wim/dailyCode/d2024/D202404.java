package com.wim.dailyCode.d2024;

import java.util.*;

public class D202404 {


    public int minOperations(int[] nums) {
        // left    1 2 3 4 = 1
        // right = left + n - 1 = 1 + 4 - 1 = 4
        // sort = [left, right] << the number which is not belongs to this array is k
        // n - k


        // 1 2 3 3 4
        // 1 2 3 4
        // left = 0
        // right = true value
        // n - (j - i + 1)
        // j ++ '

        // 10 100 1000 10000
        // 10 11 12 13
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> sortedList = new ArrayList<>(set);
        Collections.sort(sortedList);
        int res = n;
        int j = 0;
        for (int i = 0; i < sortedList.size(); i++) {
            int left = sortedList.get(i);
            int right = left + n - 1;
            while (j < sortedList.size() && sortedList.get(j) <= right) {
                res = Math.min(res, n - (j - i + 1));
                j++;
            }
        }
        return res;
    }
}
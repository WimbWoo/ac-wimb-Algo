package com.wim.dailyCode.d2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.sort;

public class d202307 {

    public static void main(String[] args) {

    }

    /**
     * 15. 三数之和 no-true
     */
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return ans;
        }
        sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            while (i < len - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                if (nums[i] + nums[L] + nums[R] == 0){
                    List<Integer> curAns = Arrays.asList(nums[i], nums[L], nums[R]);
                    ans.add(curAns);
                    L += 1;
                    R -= 1;
                } else if (nums[i] + nums[L] + nums[R] < 0) {
                    R -= 1;
                } else {
                    L += 1;
                }
            }
        }
        return ans;
    }

}

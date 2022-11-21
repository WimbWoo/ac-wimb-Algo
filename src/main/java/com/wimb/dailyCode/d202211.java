package com.wimb.dailyCode;

public class d202211 {

    /**
     * 775. 全局倒置与局部倒置
     */
    public boolean isIdealPermutation(int[] nums) {
        // timeout solution
        int gloablSum = 0;
        int localSum = 0;
        for (int i = 0;i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    gloablSum++;
                }
            }
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                localSum++;
            }
        }
        return gloablSum == localSum? true : false;
    }

    // 维护后缀数组最小值
    public boolean isIdealPermutation2(int[] nums) {
        int len = nums.length;
        int min = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            min = min < nums[i - 1]? min : nums[i - 1];
            if (nums[i - 2] > min) {
                return false;
            }
        }
        return true;
    }

}

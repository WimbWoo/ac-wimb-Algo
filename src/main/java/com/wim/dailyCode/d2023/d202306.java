package com.wim.dailyCode.d2023;

public class d202306 {

    public static void main(String[] args) {
        int arr[] = {1, 2, 2, 1, 1, 0};
        applyOperations(arr);
    }

    public static int[] applyOperations(int[] nums) {
        int n = nums.length;
        for (int i = 0, j = 0; i < n; i++) {
            if (i + 1 < n && nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
            if (nums[i] != 0) {
                swap(nums, i, j);
                j++;
            }
            for (int t : nums) {
                System.out.print(t);
            }
            System.out.println();
            System.out.println("i=" + i + "," + "j=" + j);
        }

        return nums;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

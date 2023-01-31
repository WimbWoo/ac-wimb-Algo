package com.wim.dailyCode.d2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class d202301 {

    public static void main(String[] args) {
        areNumbersAscending("1 box has 3 blue 4 red 6 green and 12 yellow marbles");
    }

    /**
     * 2042. 检查句子中的数字是否递增
     */
    public static boolean areNumbersAscending(String s) {
        String[] words = s.split(" ");
        List<Integer> list  = new ArrayList<>();
        for (String word : words) {
            char firstWord = word.charAt(0);
            if (firstWord >= '0' && firstWord <= '9') {
                list.add(Integer.valueOf(word));
            }
        }
        for(int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1802. 有界数组中指定下标处的最大值
     * 贪心 + 二分查找
     * 模拟法
     */
    public int maxValue(int n, int index, int maxSum) {
        int l = index, r = index;
        int res = maxSum - n;
        int ans = 1;
        while (l > 0 || r < n - 1) { // len 约束设置？
            int len = r - l + 1;
            if (res >= len) {
                res -= len;
                ans++;
                l = Math.max(0, l - 1);
                r = Math.min(n - 1, r + 1);
            } else {
                break;
            }
        }
        ans += res/n;
        return ans;
    }

    /**
     * 二分模板
     */
    public int binarySearch(int nums[], int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) >> 1;
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 2185. 统计包含给定前缀的字符串
     * startWih api
     * 模拟
     */
    public int prefixCount(String[] words, String pref) {
        int res = 0;
        for (String s : words) {
            if (s.startsWith(pref)) ++res;
        }
        return res;
    }

    /**
     * 1658. 将 x 减到 0 的最小操作数
     * 1.滑动窗口： 维护左边界 + 右边界的总和
     * lsum + rsum == sum 一组答案
     * lsum + rsum < sum  左边界向左移动
     * lsum + rsum > sum  右边界向右移动
     *
     * 2.双指针
     */
    public int minOperations(int[] nums, int x) {
        return -1;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 1669. 合并两个链表
     * 双指针
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode p = list1, q = list1;
        // 注意控制移动的位置
        while (--a > 0) {
            p = p.next;
        }
        // 注意控制移动的位置
        while (b-- > 0) {
            q = q.next;
        }
        p.next = list2;
        while (p.next != null) {
            p = p.next;
        }
        p.next = q.next;
        q.next = null;
        return list1;
    }

    /**
     * 2319. 判断矩阵是否是一个 X 矩阵
     */
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == j) || (i + j == n - 1)) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else {
                    if (grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
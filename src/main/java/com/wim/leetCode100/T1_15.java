package com.wim.leetCode100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class T1_15 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(7);
        l1.next = l2;

        ListNode l4 = new ListNode(9);
        ListNode l5 = new ListNode(2);
        l4.next = l5;
        ListNode listNode = addTwoNumbers(l1, l4);

        String ababa = longestPalindrome("abbbc");

        System.out.println("abcd".substring(0, 2));

    }

    // 1.两数求和
    // 暴力 / HashMap
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int []{map.get(target - nums[i]), i};
            }
        }
        return new int [2];
    }

    // 2.两数相加
    // 2.1 模拟
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1;
        }

        ListNode head = new ListNode();
        int headVal = (l1 == null?0 : l1.val) + (l2 == null?0 : l2.val);
        int flag = headVal / 10;
        head.val = headVal % 10;
        l1 = l1.next;
        l2 = l2.next;
        ListNode l3 = head;

        while (l1 != null || l2 != null) {
            int tmp = (l1 == null?0 : l1.val) + (l2 == null?0 : l2.val) + flag;
            flag = tmp / 10;
            ListNode tmpNode = new ListNode(tmp % 10);
            l3.next = tmpNode;
            l3 = tmpNode;
            l1 = l1 != null? l1.next : null;
            l2 = l2 != null? l2.next : null;
        }

        if (flag > 0) {
            ListNode tmpNode = new ListNode(flag);
            l3.next = tmpNode;
        }

        return head;
    }

    // 3. 无重复字符的最长子串
    // 滑动窗口模板题目
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int rk = -1, ans = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                set.remove(s.charAt(i-1));
            }
            while (rk + 1 < len && !set.contains(s.charAt(rk + 1)) ) {
                set.add(s.charAt(rk + 1));
                rk++;
            }
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    // 4. 寻找两个正序数组的中位数
    // 二分
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
       int len1 = nums1.length;
       int len2 = nums2.length;
       int totalLeftNum = (len1 + len2 + 1) / 2;
       int left = 0;
       int right = len1;
       while (left < right) {
            int i = (left + right + 1) / 2;
            int j = totalLeftNum - i;
            if (nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
       }
       int i = left;
       int j = totalLeftNum - i;
        // i 左右数字
        int nums1LeftMax = i == 0? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == len1? Integer.MAX_VALUE : nums1[i];
        // j 左右数字
        int nums2LeftMax = j == 0? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == len2? Integer.MAX_VALUE : nums2[j];
        // 结果
        if ((len1 + len2) % 2 == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (double)(Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2;
        }
    }

    // 二分查找
    public int BinSearch(int a[], int target) {
        int l = 0;
        int r = a.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (a[mid] < target) {
                l = mid + 1;
            } else if (a[mid] > target) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 5.最长回文子串问题
    /**
     * 1）中心拓展算法
     * 2）DP模板题目
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        int maxLen = -1;
        for (int i = 0; i < s.length(); i++) {
            int oddLen = getLen(s, i, i); // 奇数情况
            int evenLen = getLen(s, i, i + 1); // 偶数情况
            int currenLen = Math.max(oddLen, evenLen);
            if (currenLen > end - start) {
                start = i - (currenLen - 1) / 2; // 偶数情况 abbc  i = 1时 maxLen = 2 情况，类似于向下取整
                end = i + currenLen /2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int getLen(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i + 1 - 2;
    }

    /**
     * DP模板题目
     * 注意循环方向：↙️
     */
    public static String longestPalindrome2(String s) {
        int len = s.length();
        int maxLen = 0;
        int start = -1;
        if (len < 2) {
            return s;
        }

        boolean dp[][] = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < len; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i + 1 <= 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                if (dp[i][j] && (j - i + 1) > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }

        return s.substring(start, start + maxLen); // 截子串动作包含endIndex
    }

    /**
     * 11.盛最多水的容器
     * 双指针: 注意取值是长度，不是个数
     */
    public int maxArea(int[] height) {
        int len = height.length;
        int l = 0;
        int r = len - 1;
        int ans = 0;
        while (l < r) {
            int currentArea  = Math.min(height[l], height[r]) * (r - l);
            if (currentArea > ans) {
                ans = currentArea;
            }
            if (height[l] < height[r]) {
                l++;
            } else {
                r++;
            }
        }
        return ans;
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
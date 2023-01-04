package com.wim.dailyCode.d2022;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class d202008 {
    public static void main(String[] args) {
//        int a[] = {2,2,1,1,5,3,3,5};
//        int i = maxEqualFreq(a);
//        System.out.println(i);
//        int[] ints = Arrays.copyOfRange(a, 0, 2);
//        for (int t: ints
//             ) {
//            System.out.println(t);
//        }

//        boolean b = sentenceContains("abc", "abd");
//        System.out.println(b);
        int a[] = {1,2,3,4};
        int b[] = {2,4,1,3};
        boolean b1 = canBeEqual(a, b);
        System.out.println(b1);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public int getVal() {
            return val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }
    }

    /**
     * 1302. 层数最深叶子节点的和
     * @param root
     * @return
     */
    public int deepestLeavesSum(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        return getLastLayerSum(queue, 1);
    }

    public int maxSum = 0;
    private int getLastLayerSum(LinkedList<TreeNode> queue, int nodeSum) {
        if (queue.isEmpty()) {
            return maxSum;
        }
        maxSum = 0;
        int layerNodeSum = 0;
        for (int i = 0; i < nodeSum; i++) {
            TreeNode node = queue.removeFirst();
            maxSum += node.val;
            if (node.left != null) {
                queue.add(node.getLeft());
                layerNodeSum++;
            }
            if (node.right != null) {
                queue.add(node.getRight());
                layerNodeSum++;
            }
        }
        return getLastLayerSum(queue, layerNodeSum);
    }

    /**
     * 1450. 在既定时间做作业的学生人数
     * 1）遍历
     * 2）差分数组
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        // 差分数组
        int n = startTime.length;
        int max = Arrays.stream(endTime).max().getAsInt();
        if (queryTime > max) {
            return 0;
        }
        int []cnt = new int[max + 1];
        for (int i = 0; i < n; i++) {
            cnt[startTime[i]]++;
            cnt[endTime[i] + 1]--;
        }
        int ans = 0;
        for (int j = 0; j <= queryTime; j++) {
            ans += cnt[j];
        }
        return ans;
    }

    /**
     * 1224. 最大相等频率
     */
    public static int maxEqualFreq(int[] nums) {
        int []cnt = new int[100010];
        int []sum = new int[100010];
        int max = 0;
        int cur = 0;
        int len = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            cur = ++cnt[n];
            sum[cur]++;
            sum[cur - 1]--;
            max = Math.max(max, cur);
            len = i + 1;
            if (max == 1) ans = len;
            if (max * sum[max] + 1 == len) ans = len;
            if ((max - 1) * (sum[max - 1] + 1) + 1 ==  len) ans = len;
        }
        return ans;
    }

    /**
     * 1455. 检查单词是否为句中其他单词的前缀
     */
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] senArr = sentence.split(" ");
        for (int i = 0; i < senArr.length; i++) {
            if (sentenceContains(senArr[i], searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }

    private static boolean sentenceContains(String sentence, String searchWord) {
        if (searchWord == null || searchWord.length() == 0) {
            return true;
        }
        for (int i = 0; i < searchWord.length(); i++) {
            if (sentence.charAt(i) != searchWord.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1460. 通过翻转子数组使两个数组相等
     */
    public static boolean canBeEqual(int[] target, int[] arr) {
        for (int i = 0; i < target.length; i++) {
            // 1. find number
            for (int j = i;j < arr.length; j++) {
                if (target[i] == arr[j]) {
                    swap(arr, i, j);
                    break;
                } else {
                    if (j == arr.length - 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void swap(int []arr, int s, int e) {
        while (s < e) {
            int t = arr[s];
            arr[s] = arr[e];
            arr[e] = t;
            s++;
            e--;
        }
    }

    public static boolean canBeEqual2(int[] target, int[] arr) {
        HashMap<Integer, Integer> c1 = new HashMap<>();
        HashMap<Integer, Integer> c2 = new HashMap<>();
        for (int t : target) {
            c1.put(t, c1.getOrDefault(t, 0) + 1);
        }
        for (int t : arr) {
            c2.put(t, c2.getOrDefault(t, 0) + 1);
        }
        if (c1.size() != c2.size()) {
            return false;
        }
        for (Map.Entry<Integer, Integer> entry: c1.entrySet()) {
            Integer key = entry.getKey();
            Integer value = c1.get(key);
            if (!c2.containsKey(key) || c2.get(key) != value) {
                return false;
            }
        }
        return true;
    }
}






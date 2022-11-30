package com.wim.leetCode100;

import java.util.*;

public class T46 {
    public static void main(String[] args) {
         reformat("covid2019");
    }
    /**
     * 每三个数字逆序
     */
    public static void reseaver(int a[]) {
        Stack<Integer> s = new Stack<>();
        if (a == null) {
            return;
        }
        int index = -1;
        for (int i = 0; i < a.length; i = i + 3) {
            for (int j = 0; (j < 3 && i < a.length); j++) {
                if (i + j < a.length) {
                    s.push(a[i + j]);
                }
            }
            while (!s.isEmpty()) {
                a[++index] = s.pop();
            }
        }
    }

    /**
     * 快速排序
     */
    public static int[] quickSort(int []arr, int low, int high) {
        int start = low;
        int end = high;
        int key = arr[low];
        while (start < end) {
            while (start < end && arr[end] >= key) {
                end --;
            }
            if (key >= arr[end]) {
                int tmp = key;
                key = arr[end];
                arr[end] = tmp;
            }
            while (start < end && arr[start] <= key) {
                start ++;
            }
            if (key <= arr[start]) {
                int tmp = key;
                key = arr[start];
                arr[start] = tmp;
            }
        }
        if (start < end) {
            quickSort(arr, low, start - 1);
        }
        if (end < high) {
            quickSort(arr, end + 1, high);
        }
        return arr;
    }

    /**
     * 46. 全排列
     * dfs模板题
     * 思路：将右边的数字依次填入左侧位置，通过交换位置代替填写的动作，全部填写完毕保存结果
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(ans, path, nums, 0);
        return ans;
    }

    public static void dfs(List<List<Integer>> ans, List<Integer> path, int []nums, int index) {
        if (index == nums.length - 1) {
            ArrayList<Integer> cur = new ArrayList<>();
            for (int k : nums) {
                cur.add(k);
            }
            ans.add(cur);
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            dfs(ans, path, nums, index + 1);
            swap(nums, index, i);
        }
    }

    public static void swap(int []nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

    /**
     * 48. 旋转图像
     * 数组/翻转
     */

    /**
     * 49. 字母异位词分组
     * Hash使用
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedChars = new String(chars);
            List<String> stringsList = map.getOrDefault(sortedChars, new ArrayList<>());
            stringsList.add(str);
            map.put(sortedChars, stringsList);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 53. 最大子数组和  | 区间最长连续上升序列问题，区间最大子段和问题
     * 最大子序列和
     *  1）DP     f(i) = Math.max(f(i - 1) + nums[i], nums[i])
     *  2）分治
     */
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int k : nums) {
            pre = Math.max(pre + k, k);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    /**
     * 55. 跳跃游戏
     * 贪心
     */
    public boolean canJump(int[] nums) {
        int maxJump = 0;
        for (int i = 0; i< nums.length; i++) {
            if (i <= maxJump) {
                maxJump = Math.max(maxJump, i + nums[i]);
            }
            if (maxJump >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 56. 合并区间
     * 思路：DP  状态转移方程：dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
     */
    public int uniquePaths(int m, int n) {
        int arr[][] = new int[m][n];
        for (int i = 0;i < m; i++) {
            arr[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            arr[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr[m - 1][n -1];
    }

    /**
     * 64. 最小路径和
     * DP
     */
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 1; i < row; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int j = 1; j < col; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[row - 1][col - 1];
    }

    /**
     * 75. 颜色分类
     * 因为还有0，1，2，使用双指针两次遍历交换位置
     */

    /**
     * 76. 最小覆盖子串
     * 滑动窗口
     */
    public String minWindow(String s, String t) {
        return "";
    }

    /**
     * 78. 子集
     * 1）迭代法
     * 2）递归
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> t = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0;i < (1 << n);i++) {
            t.clear();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    t.add(nums[j]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }

    /**
     * 递归实现
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        dfsSubsets(ans, t, nums, 0);
        return ans;
    }

    public void dfsSubsets(List<List<Integer>> ans, List<Integer> path, int []nums, int index) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 添加该数
        path.add(nums[index]);
        dfsSubsets(ans, path, nums, index + 1);
        // 不添加该数
        path.remove(path.size() - 1);
        dfsSubsets(ans, path, nums, index + 1);
    }

    /**
     * 79. 单词搜索
     */
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || word.length() == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfsBoard(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfsBoard(char[][] board, String word, int i, int j, int index) {
        // 越界判断
        if (i < 0 || i > board.length -1  || j < 0 || j > board[0].length - 1 || board[i][j] == '*' || board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        } else {
            char curChar = word.charAt(index);
            board[i][j] = '*';
            boolean flag = dfsBoard(board, word, i - 1, j, index + 1) || dfsBoard(board, word, i + 1, j, index + 1)
                    || dfsBoard(board, word, i, j - 1, index + 1) || dfsBoard(board, word, i, j + 1, index + 1);
            board[i][j] = curChar;
            return flag;
        }
    }

    /**
     * 128. 最长连续序列
     * Hash遍历
     */
    public int longestConsecutive(int[] nums) {
        return -1;
    }


    /**
     * 139. 单词拆分
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return false;
    }


    /**
     * 146.LRU
     * 哈希 + 双向链表
     */
    public Map<Integer, DlinkedNode> cache = new HashMap<>();
    public int size;
    public int capacity;
    public DlinkedNode head, tail;


    /**
     * 148. 排序链表
     */
    public ListNode sortList(ListNode head) {
        // 归并排序
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        for (int subLen = 1; subLen < length; subLen <<= 1) {
            // list1
            ListNode pre = dummyHead;
            ListNode curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1;i < subLen && head1 != null && head1.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int j = 1;j < subLen && head2 != null && head2.next != null; j++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merged = mergeTwoListNode(head1, head2);
                pre.next = merged;
                while (pre != null) {
                    pre = pre.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;

    }

    private ListNode mergeTwoListNode(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(0);
        ListNode cur = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return preHead.next;
    }


    /**
     * 152.乘积最大子数组
     */
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = max, mn = min;
            max = Math.max(nums[i], Math.max(nums[i] * mx, mn * nums[i]));
            min = Math.min(nums[i], Math.min(nums[i] * mn, mx * nums[i]));
            res = Math.max(max, res);
        }
        return res;
    }

    /**
     * 640.求解方程
     */
    public static String solveEquation(String equation) {
        int left = 0, right = 0;
        String[] equations = equation.split("=");
        String leftStr = equations[0];
        String rightStr = equations[1];
        leftStr = leftStr.replaceAll("-", "+-");
        rightStr = rightStr.replaceAll("-", "+-");
        String[] leftEquation = leftStr.split("\\+");
        String[] rightEqualition = rightStr.split("\\+");

        for (int i = 0; i < leftEquation.length; i++) {
            String cur = leftEquation[i];
            if (cur.equals("x")) {
                left++;
            } else if (cur.equals("-x")) {
                left--;
            } else if (cur.contains("x")) {
                left += Integer.parseInt(cur.substring(0, cur.length() - 1));
            } else {
                right += -1 * Integer.parseInt(cur);
            }
        }

        for (int j = 0; j < rightEqualition.length; j++) {
            String cur = rightEqualition[j];
            if (cur.equals("x")) {
                left--;
            } else if (cur.equals("-x")) {
                left++;
            } else if (cur.contains("x")) {
                left += -1 * Integer.parseInt(cur.substring(0, cur.length() - 1));
            } else {
                right += Integer.parseInt(cur);
            }
        }

        if (left == 0) {
            if (right == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }
        return "x=" + (right / left);
    }

    /**
     * 1417. 重新格式化字符串
     */
    public static String reformat(String s) {
        char[] ch = s.toCharArray();
        int digitSum = 0, charSum = 0;
        for (int i = 0; i < ch.length; i++) {
           if (Character.isDigit(ch[i])) {
               digitSum++;
           } else {
               charSum++;
           }
        }
        if (Math.abs(digitSum - charSum) > 1) {
            return "";
        }
        boolean flag = digitSum > charSum;
        for (int i = 0, j = 1; i < ch.length; i += 2) {
            if (Character.isDigit(ch[i]) != flag) {
                while (Character.isDigit(ch[j]) != flag) {
                    j += 2;
                }
                swap(ch, i, j);
            }
        }
        return new String(ch);
    }

    public static void swap(char [] ch, int i, int j) {
        char t = ch[i];
        ch[i] = ch[j];
        ch[j] = t;
    }
}

class DlinkedNode {
    int key;
    int val;
    DlinkedNode pre;
    DlinkedNode next;
    public DlinkedNode(){}
    public DlinkedNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
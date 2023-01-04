package com.wim.leetCodeTop100;

import java.util.*;

public class T17_33 {
    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(lists);
    }

    /**
     * 17. 电话号码的字母组合
     * 回溯/深搜DFS
     */
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        List<String> ans = new ArrayList<>();
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        StringBuffer sb = new StringBuffer();
        dfs(ans, phoneMap, digits, 0, sb);
        return ans;
    }

    public static void dfs(List<String> ans, Map<Character, String> phoneMap, String digits, int index, StringBuffer sb){
        if (digits.length() == index) {
            ans.add(sb.toString());
        } else {
            // 转译过程
            String characterList = phoneMap.get(digits.charAt(index));
            for (int i = 0; i < characterList.length(); i++) {
                sb.append(characterList.charAt(i));
                dfs(ans, phoneMap, digits, index + 1, sb);
                sb.deleteCharAt(index);
            }
        }
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * 双指针（辅助头节点）
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode t1 = head;
        ListNode t2 = new ListNode(0, head);
        ListNode helpHead = t2;
        if (head == null || n < 0) {
            return head;
        }
        for (int i = 0; i< n; i ++) {
            t1 = t1.next;
        }
        while (t1 != null) {
            t1 = t1.next;
            t2 = t2.next;
        }
        t2.next = t2.next.next;
        return helpHead.next;
    }

    /**
     * 20. 有效的括号
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char value = stack.pop().charValue();
                switch (c) {
                    case '(':
                        if (value != ')') {
                            return false;
                        }
                        break;
                    case '{':
                        if (value != '}') {
                            return false;
                        }
                        break;
                    case '[':
                        if (value != ']') {
                            return false;
                        }
                        break;
                }
            }
        }
        return stack.isEmpty();
    }


    /**
     * 22. 括号生成
     * 1⃣ 暴力枚举 + 校验串合理性
     * 2⃣ DFS
     */
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        List<String> ans = new ArrayList<>();
        char []help = new char[2 * n];
        dfs(help, 0, ans);
        return ans;
    }

    public void dfs(char []help, int pos, List<String> ans) {
        if (pos == help.length) {
            if (isValid(help)) {
                ans.add(new String(help));
            }
        } else {
            help[pos] = '(';
            dfs(help, pos + 1, ans);
            help[pos] = ')';
            dfs(help, pos + 1, ans);
        }
    }

    public boolean isValid(char []c) {
        if (c == null || c.length == 0) {
            return true;
        }
        int index = 0;
        for (char t : c) {
            if (index <  0) {
                return false;
            }
            if (t == '(') {
                index++;
            } else if (t == ')') {
                index--;
            }
        }
        return index == 0;
    }

    // 方法二：DFS
    public static List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<>();
        generateStr(ans, "", n, n);
        return ans;
    }

    public static void generateStr(List<String> ans, String str, int left, int right) {
        if (left == 0 && right == 0) {
            ans.add(str);
        }
        if (left > 0) {
            generateStr(ans, str + "(", left - 1, right);
        }
        if (right > left) {
            generateStr(ans, str + ")", left, right - 1);
        }
    }

    /**
     *  23. 合并K个升序链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = lists[0];
        for (int i = 1;i < lists.length; i++) {
            mergeSortNode(ans, lists[i]);
        }
        return ans;
    }

    // 合并 2 个有序链表
    public ListNode mergeSortNode(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a == null? b : a;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (a != null && b != null) {
            if (a.val < b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null)? a : b;
        return head.next;
    }

    /**
     * 32. 最长有效括号
     * 1) 栈
     * 2）DP
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxDeep = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                maxDeep = Math.max(maxDeep, i - stack.peek());
            }
        }
        return maxDeep;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 二分: 分别找出（ >= target 的第一个数字） 和 （> target 的第一个数字）
     */
    public int[] searchRange(int[] nums, int target) {
        int i = binarySearch(nums, target);
        int j = binarySearch(nums, target + 1);
        if (i == nums.length || nums[i] != target) {
            return new int[]{-1, -1};
        }
        return new int []{i, j};
    }

    public int binarySearch(int a[], int target) {
        int l = 0, r = a.length;
        while (l < r) {
            int mid = l + (r - l) >> 1;
            if (a[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 39. 组合总和
     * 搜索&回溯
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracing(ans, path, 0, candidates, target, 0);
        return ans;
    }

    public static void backtracing(List<List<Integer>> ans, List<Integer> path, int sum, int []cadidates, int target, int startIndex) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < cadidates.length; i++) {
            if (sum + cadidates[i] <= target) {
                // 回溯模板
                path.add(cadidates[i]);
                backtracing(ans, path, sum + cadidates[i], cadidates, target, i); // 添加 startIndex 防止出现重复排列
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * TODO:42.接雨水
     * 1) DP
     * 2) 单调栈
     * 3) 双指针
     */
    public int trap(int[] height) {
        return -1;
    }

}
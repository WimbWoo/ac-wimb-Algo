package com.wim.dailyCode.d2023;

import java.util.ArrayList;
import java.util.List;

public class d202308 {
    public static void main(String[] args) {

    }

    /**
     * 全排列算法
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        boolean[] isVisited = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, isVisited, path, res);
        return res;
    }

    public static void dfs(int[] nums, int depth, boolean[] isVisited, List<Integer> path, List<List<Integer>> res) {
        int length = nums.length;
        if (depth == length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                path.add(nums[i]);
                depth++;
                dfs(nums, depth, isVisited, path, res);
                //开始回溯
                isVisited[i] = false;
                //depth--;
                //有好几个for循环，在第一次选择根节点1时是第一个for，在选择1下面的一个根节点时是第二个for，
                // 此时进行的是值传递，所以在根节点的for上depth是1没有变，以此类推
                path.remove(path.size() - 1);
            }
        }
    }

    // 求所有的子序列集合

}

package com.wim.labuladong;

public class C1 {

    public static void main(String[] args) {
    }
    // 1.二叉树类型题目： 涉及递归，遍历

    // 2.DP： 状态转移方程
    /**
     * 动态规划： 状态转移方程（最简子问题 - 状态压缩）
     * 322. 零钱兑换 dp[j] = Math.min{dp[j - coins[i]] + 1, d[j]}
     */
    public static int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        int memo[] = new int[amount + 1]; // memo[i]: 组成零钱数 i 所需的最小硬币数量
        memo[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                // i - coins[j]: 总金额 i 去除 coins[j] 剩余总金额
                // memo[i - coins[j]] 组成 剩余总金额 的最小数量
                if (i - coins[j] >= 0 && memo[i - coins[j]] < min) {
                    min = memo[i - coins[j]] + 1;
                }
            }
            memo[i] = min;
        }
        return memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
    }

    // 3.回溯
    /**
     * 全排列
     */


}

package com.wimb.dailyCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class d202210 {

    /**
     * 1768. 交替合并字符串
     * @param word1
     * @param word2
     * @return
     */
    public String mergeAlternately(String word1, String word2) {
        StringBuffer sb = new StringBuffer();
        int i = 0, j = 0;
        int len1 = word1.length();
        int len2 = word2.length();
        for (; i < len1 && j < len2; ++i, ++j) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(j));
        }
        if (i < word1.length()) {
            sb.append(word1.substring(i));
        }
        if (j < word2.length()) {
            sb.append(word2.substring(j));
        }
        return sb.toString();
    }

    /**
     * 915. 分割数组
     * @param nums
     * @return
     */
    public int partitionDisjoint(int []nums) {
        int idx = 0, leftMax = nums[0], max = leftMax;
        for (int i = 1; i < nums.length; i++) {
            if (leftMax > nums[i]) {
                leftMax = max;
                idx = i;
            } else {
                max = Math.max(max, nums[i]);
            }
        }
        return idx + 1;
    }

    /**
     * 934. 最短的桥(not solved)
     * 图 - 深度优先搜索
     */
    int[][] grid;
    int [][]coordinates = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 上、下、右、左四个方向
    Deque<int[]> edges; // 用户存储边缘格子
    public int shortestBridge(int[][] grid) {
        int result = 0;
        boolean findIsland = false; // 只要找到2个岛屿中其中的1个岛屿，就将其设置为true，并结束步骤1中的两层for循环
        edges = new ArrayDeque();
        this.grid = grid;
        /** 步骤1：为其中一个岛屿打标记（val=2），并保存”边界格子“到edges中 */
        for (int i = 0; !findIsland && i < grid.length; i++)
            for (int j = 0; !findIsland && j < grid[0].length; j++)
                if (findIsland = (grid[i][j] == 1)) markIsland(i, j);

        /** 步骤2：利用边界edges，一层一层扩展岛屿（val=2），直到遇到另一个岛屿（val=1）*/
        while (!edges.isEmpty()) {
            result++; // 扩展层数
            int num = edges.size();
            for (int i = 0; i < num; i++) {
                int[] edge = edges.removeFirst();
                for (int[] c : coordinates) { // 向edge的四个方向查看格子编号，并扩展岛屿边界
                    int nex = edge[0] + c[0], ney = edge[1] + c[1];
                    if(isLegal(nex, ney) && grid[nex][ney] == 0) {
                        edges.addLast(new int[]{nex, ney}); // 添加新的边界
                        grid[nex][ney] = 2;
                    }
                    else if (isLegal(nex, ney) && grid[nex][ney] == 1) return result; // 与另一个岛屿相遇，则直接返回result
                }
            }
        }
        return result;
    }

    public void markIsland(int row, int column) {
        if (!isLegal(row, column) || grid[row][column] == 2) return;
        if (grid[row][column] == 0) {
            grid[row][column] = 2; // 将边界向外扩展1层岛屿（val=2)
            edges.addLast(new int[]{row, column});
            return;
        }
        grid[row][column] = 2; // 为岛屿打标记（val=2）
        for (int[] c : coordinates) markIsland(row + c[0], column + c[1]); // 深度遍历某个格子的四个方向
    }

    public boolean isLegal(int row, int column) {
        return row >= 0 && row < grid.length && column >= 0 && column < grid[0].length;
    }
}

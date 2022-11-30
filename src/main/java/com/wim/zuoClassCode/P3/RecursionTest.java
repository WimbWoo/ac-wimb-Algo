package com.wim.zuoClassCode.P3;

/**
 * 递归
 *
 * @Author: meng.wu01
 * @DateTime: 2022/1/30
 */
public class RecursionTest {
    public static void main(String[] args) {
        int a[] = {1,2,3,4,3,9,1};
        int max = maxNum(a, 0, a.length -1);
        System.out.println(max);
    }

    // T1: 最数组最大值
    // 栈的实现原理就是底层栈的实现
    public static int maxNum(int a[], int L, int R) {
        if (L == R) {
            return a[L];
        }
        int mid = L + ((R - L) >> 1);
        int maxL = maxNum(a, L, mid);
        int maxR = maxNum(a, mid + 1, R);
        return Math.max(maxL, maxR);
    }
}

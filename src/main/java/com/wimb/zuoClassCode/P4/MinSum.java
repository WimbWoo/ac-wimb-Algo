package com.wimb.zuoClassCode.P4;

/**
 * @Author: meng.wu01
 * @DateTime: 2022/2/17
 */
public class MinSum {
    public static void main(String[] args) {

    }

    public static int process(int a[], int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + (R - L) >> 1;
        return process(a, L, M) + process(a, M + 1, R) + merge(a, L, M, R);
    }

    public static int merge(int a[], int L, int M, int R) {
        int help[] = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        while (p1 <= M && p2 <= R) {
            res = a[p1] < a[p2]? a[p1]*(R-p2+1) : 0;
            help[i++] = a[p1] < a[p2]? a[p1++] : a[p2++];
        }
        while (p1 <= M) {
            help[i++] = a[p1++];
        }
        while (p2 <= R) {
            help[i++] = a[p2++];
        }
        for (i = 0; i < help.length; i++) {
            a[L + i] = help[i];
        }
        return res;
    }
}

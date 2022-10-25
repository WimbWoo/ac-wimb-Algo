package com.wimb.zuoClassCode.P4;

/**
 * @Author: meng.wu01
 * @DateTime: 2022/2/9
 */
public class MergeSortTest {
    public static void main(String[] args) {
        int a[] = {1,3,5,4,2,2,2,2};
        mergeSort2(a);
        for (int k:a) {
            System.out.println(k);
        }
    }

    // 归并排序实现1:递归实现
    // 复杂度计算   L ... R  ==> T(N) = 2T(N/2) + O(N)
    // O(N*logN)
    public static void mergeSort(int a[]) {
        if (a == null || a.length < 2){
            return;
        }
        process(a, 0, a.length-1);
    }

    public static void process(int a[], int L, int R) {
        // 递归结束条件
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(a, L, M); // 左边变有序
        process(a, M + 1, R); // 右边变有序
        merge(a, L, M, R); // 把左边和右边整理有序刷回数组
    }

    public static void merge(int a[], int L, int M, int R) {
        int help[] = new int [R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
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
    }

    // 归并排序实现2：非递归
    // 1 3 2 4 5       mergeSize = 1 | 1 3 2 4 5
    // (1 3) (2 4) (5) mergeSize = 2 | 1 3 2 4 5
    // (1 3 2 4) (5)   mergeSize = 4 | 1 2 3 4 5
    public static void mergeSort2(int a[]) {
        if (a == null || a.length < 2) {
            return;
        }
        int N = a.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N){
                    break;
                }
                int R = Math.min(M + mergeSize, N - 1);
                merge(a, L, M, R);
                L = R + 1;
            }
            // 防止数组越界溢出
            if (mergeSize > N /2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    // 思考点1：O(N2) & O(NlogN) 排序复杂度对比，O(N2)排序大量浪费比较行为

    // T1:数组小和
    // 1 3 2 4 ==> 1 1 1 6
    // 思路：修改归并过程，左边有多少个比当前数小  <===> 右边有多少个数比当前大

    // T2:数组中的逆序对的个数
    // 思路：归并过程中找出右侧比当前小的数即可
}

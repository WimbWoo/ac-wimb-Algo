package com.wimb.ZuoClassCode.P2;

/**
 * @Author: meng.wu01
 * @DateTime: 2021/12/5
 */
public class Sort {
    public static void main(String[] args) {
        Sort sort = new Sort();
        int arr[] = {1,3,2,5,4};
        insertSort(arr);
        for(int a : arr) {
            System.out.println(a);
        }
        // [0,1)
        Math.random();
        // [0,N-1]
        // (int)(Math.random() * N)
    }
    //1.选择排序
    public static void selectSort(int []a) {
        if (a == null || a.length < 2) {
            return ;
        }
        for (int i = 0; i < a.length-1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > a[minIndex]) {
                    minIndex = j;
                }
            }
            swap(a, i, minIndex);
        }
    }

    public static void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void swap1(int arr[], int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    //2.冒泡排序
    public static void bubbleSort(int []a) {
        if (a == null || a.length < 2) {
            return;
        }
        //0 -- N-1 前后两个相互比较，一次遍历会将最大数挪到最后一个位置
        //0 -- N-2
        //...
        //0 -- 1
        for (int e = a.length - 1; e > 0; e--) {
            for (int i = 0; i < e; i++) {
                if (a[i] > a[i+1]) {
                    swap(a, i, i+1);
                }
            }
        }
    }

    //3.插入排序:往前插入，直到找到最小的位置插入
    public static void insertSort(int []a) {
        if (a == null || a.length < 2) {
            return;
        }
        //0 - i有序
        //7 3 4 5
        //3 7 4 5
        //3 4 7 5
        //3 4 5 7
        for (int i = 0; i <  a.length-1; i++) {
            for (int j = i; j >= 0 && a[j] > a[j+1]; j--) {
                 swap(a, j, j+1);
            }
        }
    }

    // T2:二分判断数组是否存在某个值 [局部最小值问题]
    public static boolean exist(int a[], int num) {
        if (a == null || a.length == 0) {
            return false;
        }
        int L = 0;
        int R = a.length - 1;
        int mid = 0;
        while (L < R) {
            // 常见位运算：
            //      N / 2 ==> N >> 1
            //      N * 2 ==> N << 1
            //      N * 2 + 1 ==> (N << 1 | 1)
            mid = L + ((R - L) >> 1);
            if (a[mid] == num) {
                return true;
            } else if (a[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return a[L] == num;
    }

    // 找满足>=num的最左位置:记录index
    // 局部最小值

    // P2: 异或运算 == 无进位相加运算
    /**
     *
     * 异或性质：
     *    0 ^ N = N;    N ^ N = 0
     *    满足交换率和结合率
     *    T1：交换两个数的值
     *    T2：数组中一个数奇数个，其他偶数个，找出这个数字 | Ans = 全部异或在一起
     *    T3：N = 00011100010 --> N=00000000010 (把一个数最后一个1之前的1转为0) | Ans =  N & (~N +1)
     *    T4: 数组中两个数是奇数个，其他偶数个，找出这两个数字
     *    T5: 二进制中1的个数
     */
    // T4
    public void findTwoNum(int []a) {
        int eor = 0;
        for(int i : a){
            eor = eor ^ i;
        }
        // eor = a ^ b
        // eor != 0 说明 必然有一个位置是1
        // 00111010
        // 00000010 进行区分
        int rights = eor & (~eor + 1);
        int onlyOne = 0;
        for (int i : a){
            if ((i & eor) != 0) {
                onlyOne ^= i;
            }
        }
        System.out.println(onlyOne + "Other one:" + (onlyOne ^ eor));
    }
}

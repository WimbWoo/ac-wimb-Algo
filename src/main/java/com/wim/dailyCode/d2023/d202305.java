package com.wim.dailyCode.d2023;

public class d202305 {
    public static void main(String[] args) {

    }

    // 时间是否有冲突
    // 解法2：直接比较字符串的大小
    public boolean haveConflict(String[] event1, String[] event2) {
        int e1Start = transferToMins(event1[0]);
        int e1End = transferToMins(event1[1]);
        int e2Start = transferToMins(event2[0]);
        int e2End = transferToMins(event2[1]);


        int len = Math.max(Math.abs(e2End - e1Start), Math.abs(e1End - e2Start));
        return len <= (e1End - e1Start) + (e2End - e2Start);
    }

    public int transferToMins(String time) {
        String[] currentTime = time.split(":");
        return Integer.parseInt(currentTime[0]) * 60 + Integer.parseInt(currentTime[0]);
    }

}

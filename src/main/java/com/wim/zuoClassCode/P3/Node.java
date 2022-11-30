package com.wim.zuoClassCode.P3;

/**
 * @Author: meng.wu01
 * @DateTime: 2022/1/30
 */
public class Node {
    public Node next;
    public int value;

    public Node(int value) {
        this.value = value;
    }
}

class NodeT {
    public int value;
    public NodeT pre;
    public NodeT next;
    public void NodeT(int value){
        this.value = value;
    }
}


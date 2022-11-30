package com.wim.zuoClassCode.P3;

/**
 * @Author: meng.wu01
 * @DateTime: 2022/1/30
 */
public class NodeTest {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n22 = new Node(2);
        Node n3 = new Node(3);
        n1.next = n2;
        n2.next = n22;
        n22.next = n3;
        Node node = deleteNode(n1, 2);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    // T1: 反转单向链表
    public static Node trverse(Node node) {
        if (node == null) {
            return node;
        }
        Node pre = null;
        Node next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    // T2: 反转双向链表
    public static NodeT trverse(NodeT node) {
        NodeT pre = null;
        NodeT next = null;
        while (node != null) {
            // next记录节点信息
            next = node.next;
            // 交换指向
            node.pre = next;
            node.next = pre;
            // 节点后移
            pre = node;
            node = next;
        }
        return pre;
    }

    // T3: 单链表中删除给定值
    public static Node deleteNode(Node head, int value) {
        if (head == null) {
            return head;
        }
        // 1.头节点处理，找到第一个不为 value 的节点
        while (head != null && head.value == value) {
            head = head.next;
        }
        // 2.删除值为 value 的节点
        Node cur = head.next;
        Node pre = head;
        while (cur != null && pre != null) {
            while (cur != null && cur.value == value) {
                cur = cur.next;
            }
            pre.next = cur;
            pre = cur;
            cur = pre.next;
        }
        return head;
    }
}

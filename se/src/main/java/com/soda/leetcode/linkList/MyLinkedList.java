package com.soda.leetcode.linkList;

/**
 * @author soda
 * @date 2022/4/16
 */
class MyLinkedList {

    private int size = 0;
    private ListNode head;
    private ListNode tail;

    public MyLinkedList() {

    }

    public int get(int index) {
        if (index == (size - 1)) {
            return tail.val;
        } else if (0 <= index && index < size) {
            ListNode curr = head;
            while (index > 0) {
                curr = curr.next;
                index--;
            }
            return curr.val;
        }
        return -1;

    }

    public void addAtHead(int val) {
        if (head == null) {
            head = new ListNode(val, null);
            tail = head;
            size++;
            return;
        }
        head = new ListNode(val, head);
        size++;
    }

    public void addAtTail(int val) {
        if (head == null) {
            head = new ListNode(val, null);
            tail = head;
            size++;
            return;
        }
        ListNode newNode = new ListNode(val, null);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index <= 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else if (index < size) {
            ListNode curr = head;
            while (index > 1) {
                curr = curr.next;
                index--;
            }
            curr.next = new ListNode(val, curr.next);
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (0 <= index && index < size) {
            ListNode curr = new ListNode(-1, head);
            ListNode dumy = curr;
            while (index > 0) {
                index--;
                dumy = dumy.next;
            }
            ListNode temp = dumy.next;
            dumy.next = dumy.next.next;
            temp.next = null;
            size--;
            head = curr.next;
            tail = curr.next;
            while (tail != null && tail.next != null) {
                tail = tail.next;
            }

        }
    }
        public static void main (String[]args){
            MyLinkedList linkedList = new MyLinkedList();
            // ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
// [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
            linkedList.addAtHead(7);
            linkedList.addAtHead(2);
            linkedList.addAtHead(1);
            linkedList.addAtIndex(3, 0);
            linkedList.deleteAtIndex(2);
            linkedList.addAtHead(2);
            linkedList.addAtTail(6);
            linkedList.get(4);
            linkedList.addAtHead(4);
            linkedList.addAtIndex(5, 0);
            linkedList.addAtHead(6);
        }
}
/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
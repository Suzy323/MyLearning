

import java.util.regex.Pattern;
import java.util.regex.Matcher;
//单向链表的    翻转。

public class MyLink {
	Node head = null;
	public static void main(String[] args) {
		MyLink list = new MyLink();
        list.addNode(5);
        list.addNode(3);
        list.addNode(1);
        list.addNode(2);
        list.addNode(55);
        list.addNode(36);
        System.out.println("linkLength:" + list.length());
        System.out.println("head.data:" + list.head.data);
        list.printList();
	}
	
	//One-way linked list
	class Node{

		Node next = null;
		int data;
		public Node(int data)
		{
			this.data = data;
		}
	}
	
	public void addNode(int d){
		Node newNode = new Node(d);
		Node tmp = head;
		if(head == null){
			head = newNode;
			return;
		}
		while(tmp.next != null){
			tmp = tmp.next;
		}
		tmp.next = newNode;				
	}
	
	public void deleteNode(int index){
		if(index < 1 || index > length()){
			System.out.println("index out of range");
		}	
		Node tmp = head;
		Node tmpPre = null;
		int length = 1;
		if(index == 1){
			head = head.next ;
			return;
		}
		while(tmp != null){
			if(index == length++){
				tmpPre.next  = tmp.next;
				return;
			}
			tmpPre = tmp;
			tmp = tmp.next;	
		}
	}
	
	public void addNodebyIndex(int index, Node node)
	{
		if(index < 1 || index > length()){
			System.out.println("index is illegal");
		}
		int length = 1;
		Node tmp = head;
		while(tmp.next != null){
			if(index == length++){
				node.next = tmp.next;
				tmp.next = node;
			}
			tmp = tmp.next;
		}
		tmp.next = node;
		node.next = null;
	}
	
	public void printList(){
		Node tmp = head;
		while(tmp != null){
			System.out.print(tmp.data+",");
			tmp = tmp.next;
		}
		System.out.println();
	}
	
	public int length(){
		int length=0;
		Node tmp = head;
		while(tmp != null){
			length++;
			tmp = tmp.next;
		}
		return length;
	}
	
	/**
     * 链表反转
     * 
     * @param head
     * @return
     */
    public Node ReverseIteratively(Node head) {
        Node pReversedHead = head;
        Node pNode = head;
        Node pPrev = null;
        while (pNode != null) {
            Node pNext = pNode.next;
            if (pNext == null) {
                pReversedHead = pNode;
            }
            pNode.next = pPrev;
            pPrev = pNode;  //下一个节点的prev
            pNode = pNext;  //下一个分析节点    维护三个Node pPre pNode pNext.主意边界分析。
        }
        this.head = pReversedHead;
        return this.head;
    }
    
    /**
     * 查找单链表的中间节点
     * 采用快慢指针的方式查找单链表的中间节点，快指针一次走两步，
     * 慢指针一次走一步，当快指针走完时，慢指针刚好到达中间节点。
     * @param head
     * @return
     */
    public Node SearchMid(Node head) {
        Node p = this.head, q = this.head;
        while (p != null && p.next != null && p.next.next != null) {
            p = p.next.next;    // 1 3 5 7   2n+1=d, p.next!=null 表示的是基数
            q = q.next;         // 1 2 3 4   4可以作为678的中间点
        }
        System.out.println("Mid:" + q.data);
        return q;
    }
    /**
     * 查找倒数 第k个元素
     * 采用两个指针P1,P2，P1先前移K步，然后P1、P2同时移动，
     * 当p1移动到尾部时，P2所指位置的元素即倒数第k个元素 。
     * @param head
     * @param k
     * @return
     */
    public Node findElem(Node head, int k) {
        if (k < 1 || k > this.length()) {
            return null;
        }
        Node p1 = head;
        Node p2 = head;
        for (int i = 0; i < k; i++)// 前移k步
            p1 = p1.next;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
    /**
     * 排序
     * 
     * @return
     */
    public Node orderList() {
        Node nextNode = null;
        int tmp = 0;
        Node curNode = head;
        while (curNode.next != null) {
            nextNode = curNode.next;
            while (nextNode != null) {
                if (curNode.data > nextNode.data) {
                    tmp = curNode.data;
                    curNode.data = nextNode.data;
                    nextNode.data = tmp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }
        return head;
    }
    /**
     * 删除重复节点
     */
    public void deleteDuplecate(Node head) {
        Node p = head;
        while (p != null) {
            Node q = p;
            while (q.next != null) {
                if (p.data == q.next.data) {
                    q.next = q.next.next;
                } else
                    q = q.next;
            }
            p = p.next;
        }

    }
    /**
     * 从尾到头输出单链表，采用递归方式实现
     * 
     * @param pListHead
     */
    public void printListReversely(Node pListHead) {
        if (pListHead != null) {
            printListReversely(pListHead.next);
            System.out.println("printListReversely:" + pListHead.data);
        }
    }
    /**
     * 判断链表是否有环，单向链表有环时，尾节点相同
     * 
     * @param head
     * @return
     */
    public boolean IsLoop(Node head) {
        Node fast = head, slow = head;
        if (fast == null) {
            return false;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                System.out.println("该链表有环");
                return true;
            }
        }
        return !(fast == null || fast.next == null);
    }

    /**
     * 找出链表环的入口
     * 
     * @param head
     * @return
     */
    public Node FindLoopPort(Node head) {
        Node fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        if (fast == null || fast.next == null)
            return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    
}

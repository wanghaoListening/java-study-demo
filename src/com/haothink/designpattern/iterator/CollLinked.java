package com.haothink.designpattern.iterator;
/*
 * 
 * 模拟LinkedList
 * 
 * */
public class CollLinked implements ICollection{
	//头指针
	Node head = null;
	//尾指针
	Node tail = null;
	int count = 0;
	public void add(Object obj){
		Node node = new Node(obj,null);
		if(head == null){
			//刚开始插入
			head = node;
			tail = node;
		}
		//将新插入的节点与尾节点连接
		tail.setNext(node);
		//令尾指针指向新插入的节点
		tail = node;
		count++;
	}

	public int size(){
		return count;
	}

	@Override
	public IIterator iiterator() {

		return new Linked();
	}
	/*
	 * 内部类访问外部类的成员
	 * 1可以直接访问head.getNext();
	 * 2也可以CollLinked.this.head.getNext();
	 * */
	private class Linked implements IIterator{
		int cursor=0;
		@Override
		public Object next() {
			Node currNode;
			Node node = head.getNext();
			cursor++;
			if(node == null){
				
				return head.getObj();
			}
			currNode = head;
			head = node;
		
			return currNode.getObj();
		}

	
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cursor!=size();
		}

	}

}

class Node{
	//数据域
	private Object obj;
	//指向下一个节点的指针域
	private Node next;

	
	public Node(Object obj, Node next) {
		super();
		this.obj = obj;
		this.next = next;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}

}

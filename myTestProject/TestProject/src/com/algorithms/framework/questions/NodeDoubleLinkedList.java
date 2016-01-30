package com.algorithms.framework.questions;

public class NodeDoubleLinkedList {
	
	private int data;
	private NodeDoubleLinkedList next;
	private NodeDoubleLinkedList prev;
	
	public NodeDoubleLinkedList(int data){
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	
	public void append(int data){
		
		NodeDoubleLinkedList end = this;
		
		while(end.next!=null){
			end = end.next;
		}
		
		end.next = new NodeDoubleLinkedList(data);
		end.next.prev = end;
		
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public NodeDoubleLinkedList getNext() {
		return next;
	}

	public void setNext(NodeDoubleLinkedList next) {
		this.next = next;
	}

	public NodeDoubleLinkedList getPrev() {
		return prev;
	}

	public void setPrev(NodeDoubleLinkedList prev) {
		this.prev = prev;
	}
	
	public void deleteElement(){
		
		NodeDoubleLinkedList element = this;
		NodeDoubleLinkedList prevElement = element.prev;
		
		if(prevElement!=null){
			prevElement.next = element.next;
		}
		
		if(element.next!=null){
			element.next.prev = prevElement;
		}
		
		element.next = null;
		element.prev = null;
		
		element = null;
	}
}

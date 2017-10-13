package com.frameclient.utils;

import java.util.LinkedList;


public class Queue
{
	LinkedList<Frame> linkedList = new LinkedList<Frame>();
	//队尾插
	 public void put(Frame o)
	 {
		 linkedList.addLast(o);
	 }
	 // 队头取 取完并删除
	 public Frame get() 
	 {
		  if (linkedList.size() > 0)
		  {
			  return linkedList.removeFirst();
			  
		  }
		  else
		  {
			  return null;
		  }
	 }
	 public boolean isEmpty()
	 {
		 return linkedList.isEmpty();
	 }
	 public int size()
	 {
		 return linkedList.size();
	 }
	 public void clear()
	 {
		 linkedList.clear();
	 }
}
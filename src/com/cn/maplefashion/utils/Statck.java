package com.cn.maplefashion.utils;

import java.util.ArrayList;
import java.util.List;

public class Statck<E extends Object> {
	private List<E> pool = new ArrayList<E>();

	public Statck() {
	}

	public void clear() {
		pool.clear();
	}

	public boolean isEmpty() {
		return pool.isEmpty();
	}

	/**
	 * 获取栈顶元素
	 * */
	public E getTopObjcet() {
		if (isEmpty()) {return null;}
		return pool.get(pool.size()-1);
	}

	/**
	 * 弹出栈操作
	 * */
	public E pop() {
		if (isEmpty()) return null;
		return pool.remove(pool.size() - 1);
	}

	/**
	 * 压入栈
	 * */
	public void push(E e) {
		pool.add(e);
	}


	/**
	 * 获取当前栈大小
	 * */
	public int getStatckSize() {
		return pool.size();
	}
}
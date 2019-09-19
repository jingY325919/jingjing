package com.jj.threadapple;

/**
 * 苹果对象
 * @author ASUS
 *
 */
public class Apple {

	int id;//编号
	
	public Apple(int id) {//构造方法
		this.id = id;
	}
	
	public String toString() {
		return "apple"+id;
	}
}

package com.jj.threadbase;

public class Thread01 {

	public static void main(String[] args){
		System.out.println("main开始.......");
		show();
		System.out.println("main结束.......");
	}

	private static void show() {
		boolean flag = true;
		while(flag){
			//操作耗时 系统假死   解决：引用线程
			System.out.println("show.......");
		}
		
	}
}

package com.jj.threadbase;

import java.util.Date;

/**
 * 线程的第一种方式 ：继承Thread
 * @author Administrator
 *
 */
public class Thread02 {

	public static void main(String[] args){
		System.out.println("main开始.......");
		//创建线程对象
		ShowTimeThread t = new ShowTimeThread();
		//启动线程
		t.start();
		System.out.println("main结束.......");
	}
	
	
}


//继承Thread类  重写run()
class ShowTimeThread extends Thread{
	boolean flag = true;

	@Override
	public void run() {
		while(flag) {
			Date date = new Date();
			System.out.println("当前系统时间："+date);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
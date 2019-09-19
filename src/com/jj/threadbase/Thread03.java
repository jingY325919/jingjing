package com.jj.threadbase;

import java.util.Date;

/**
 * 线程的第二种方式：实现Runable接口
 *    实现Runable接口的三种方式
 * @author Administrator
 *
 */
public class Thread03 {

	public static void main(String[] args){
		System.out.println("main开始.......");
/*		//使用内部类
		ShowTimeTask task = new Thread03().new ShowTimeTask();//创建内部类对象
		//创建线程对象
		Thread t = new Thread(task);
		//启动线程
		t.start();
		
*/		
		
		//使用匿名类
	/*	Runnable runable = new Runnable(){
			
			public void run(){
				while(true){
					Date date = new Date();
					System.out.println("当前系统时间："+date);
					try{
						Thread.sleep(1000);//线程睡眠  只能进行捕获
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
			}
		};
		//创建线程对象
		Thread t = new Thread();
		t.start();
	}
	*/
	
		//使用匿名内部类
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
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
			
		}).start();
		System.out.println("main结束。。。。。");
}

//创建一个内部类
/*class ShowTimeTask implements Runnable{

	boolean flag = true;
	@Override
	public void run() {
		while(flag){
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
	
}*/
}

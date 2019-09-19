package com.jj.threadpool;

import java.util.Date;
import java.util.TimerTask;

public class Test {

	public static void main(String[] args) {
		//线程管理对象  线程池
		ThreadPoolManager manager = new ThreadPoolManager(10);
		for(int i=0;i<20;i++) {
			manager.process(new TimeTask());
		}
		
		manager.process(new CountTask(new MyNotify() {

			@Override
			public void notifyResult(Object obj) {
				System.out.println(obj);
				
			}
			
		}));
	}
}

class CountTask implements Runnable{
	private MyNotify  myNotify;
	
	public CountTask(MyNotify myNotify) {
		this.myNotify = myNotify;
	}

	@Override
	public void run() {
		int i = 10;
		while(true) {
			if(null != myNotify) {
				myNotify.notifyResult(i);//没有输出
			}
			i--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if(i<=0) {
				break;
			}
		}
		
	}
	
	
}

class TimeTask implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName()+"--当前系统时间："+new Date());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
	
}
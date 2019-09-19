package com.jj.threadbase;

/**
 * 精灵线程(守护线程)
 * @author Administrator
 *
 */
public class Thread04 {

	public static void main(String[] args){
		System.out.println("main开始。。。。");
		ShowTimeTask2 task = new ShowTimeTask2();
		Thread t = new Thread(task);
		//设置精灵线程又叫守护线程  当主线程执行结束，子线程也随之结束  尤其是swt中
		t.setDaemon(true);
		t.start();
		System.out.println("main结束。。。。");
		//创建的所有线程默认的优先级都是5
		System.out.println("子线程："+Thread.currentThread().getId()+"线程名："+Thread.currentThread().getName()+"\t优先级："+Thread.currentThread().getPriority());
	}
}

class ShowTimeTask2 implements Runnable{
	boolean flag = true;

	@Override
	public void run() {
		while(flag){
			System.out.println("子线程："+Thread.currentThread().getId()+"线程名："+Thread.currentThread().getName()+"\t优先级："+Thread.currentThread().getPriority());
			try {
				Thread.sleep(1000);//线程睡眠，只能进行捕获
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}

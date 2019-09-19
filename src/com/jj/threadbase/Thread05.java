package com.jj.threadbase;

public class Thread05 {

	public static void main(String[] args) throws InterruptedException{
		LifeCricle lc = new LifeCricle();
		Thread t = new Thread(lc);
		t.start();
		System.out.println(t.isAlive());//线程的状态
		t.join();//让t线程执行完成
		System.out.println("主程序。。。。");
		System.out.println(t.isAlive()+"------");
	}
}

class LifeCricle implements Runnable{

	@Override
	public void run() {
		int i =0;
		while(i<=10){
			System.out.println(Thread.currentThread().getId()+":"+i);
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 主线程向下执行时，碰到t.join() ,t要申请及加入到运行中来，即要CPU的执行权
	 * 此时执行权在主线程手中，主线程把CPU的执行权交给t,主线程自己则冻结状态
	 * t执行完成后，主线程才能恢复运行中来
	 * 
	 */
	
}

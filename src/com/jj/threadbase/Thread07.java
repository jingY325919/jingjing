package com.jj.threadbase;

import java.util.Random;

/**
 * 资源争抢 --》锁 --》同步锁   可以锁对象   也可以锁方法
 * @author ASUS
 *
 */
public class Thread07 {

	public static void main(String[] args) {
		SellTickOp op = new SellTickOp(30);
		Thread t01 = new Thread(op,"张三");
		t01.start();
		Thread t02 = new Thread(op,"李四");
		t02.start();
		Thread t03 = new Thread(op,"wuwu");
		t03.start();
	}
}

class SellTickOp implements Runnable{

	boolean flag = true;
	int ticks;
	Random rd = new Random();
	
	public SellTickOp(int ticks) {
		this.ticks = ticks;
	}
	
	@Override
	public void run() {
		while(ticks>0) {
			sale();
		}
//		while(flag) {
//			synchronized (this) {//锁定该资源
//				if(ticks>0) {
//					try {
//						Thread.sleep(rd.nextInt(800));
//					} catch (InterruptedException e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
//					System.out.println(Thread.currentThread().getName()+"在卖第"+(ticks--)+"张票");
//				}else {
//					return;
//				}
//				
//			}
//		}
	}
	
	//买票的方法
	public synchronized void sale() {
		if(ticks>0) {
			try {
				Thread.sleep(rd.nextInt(800));
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"在卖第"+(ticks--)+"张票");
		}else {
			return;
		}
	}
	
}
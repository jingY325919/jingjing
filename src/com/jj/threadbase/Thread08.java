package com.jj.threadbase;

/**
 * 死锁问题的模拟，彼此等待对方释放资源
 * @author ASUS
 *
 */
public class Thread08 implements Runnable{
	boolean flag = false;//线程标识符
	static Zhangsan zs = new Zhangsan();
	static Lisi ls = new Lisi();
	
	public static void main(String[] args) {
		Thread08 t01 = new Thread08();
		t01.flag = true;
		new Thread(t01).start();
		Thread08 t02 = new Thread08();
		t02.flag = false;
		new Thread(t02).start();
	}

	@Override
	public void run() {
		if(flag) {
			synchronized (zs) {
				zs.say();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				//同时把Lisi锁定
				synchronized (ls) {
					zs.get();
				}
			}
		}else {
			synchronized (ls) {
				ls.say();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				synchronized (zs) {
					ls.get();
				}
			}
		}
		
	}
}


class Zhangsan{
	public void say() {
		System.out.println("张三对李四说：你给我书，我给你画");
	}
	
	public void get() {
		System.out.println("张三得到了书");
	}
}

class Lisi{
	public void say() {
		System.out.println("李四对张三说：你给我画，我给你书");
	}
	
	public void get() {
		System.out.println("李四得到了画");
	}
}
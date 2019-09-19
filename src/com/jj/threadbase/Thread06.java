package com.jj.threadbase;

/**
 * 挂起线程
 * @author Administrator
 *
 */
public class Thread06 {

	public static void main(String[] args) {
		Yieldone y = new Yieldone();
		Yieldone y2 = new Yieldone();
		Thread t = new Thread(y,"a");//创建线程时并指定线程的名字
		Thread t2 = new Thread(y2,"b");
		t.setPriority(1);
		t.start();
		t2.setPriority(10);//设置线程优先级，更大的概率
		t2.start();
		
	}
}

class Yieldone implements Runnable{

	@Override
	public void run() {
		if ("a".equals(Thread.currentThread().getName())) {
			Thread.yield();//yield将执行权交给优先级高的线程
			/*try {
				Thread.sleep(100);//sleep不管优先级，只要调用sleep方法，则当前线程睡眠，其他线程接过执行权
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
	
}
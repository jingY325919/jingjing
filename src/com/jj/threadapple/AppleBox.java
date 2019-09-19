package com.jj.threadapple;

/**
 * 缓冲区  苹果盒
 * @author ASUS
 *
 */
public class AppleBox {

	int index = 0;
	Apple [] apples = new Apple[5];//只能存储5个苹果
	
	//生产苹果
	public synchronized void prouduceApple(Apple apple) {
		while(index>=apples.length) {
			//容器已满，无法继续生产苹果
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			/*try {
				Thread.sleep(1000);//该线程处于等待状态
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}*/
		}
		this.notifyAll();
		//生产苹果
		apples[index] = apple;
		System.out.println(Thread.currentThread().getName()+"生产了编号为："+apple.id+"的苹果");
		index++;
	}
	
	//取苹果
	public synchronized Apple eatApple() {
		while(index<=0) {
			//苹果已全部消费，等待生产者生产
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}*/
		}
		this.notifyAll();//通知其他所有处于wait状态的线程被激活
		//消费苹果
		index--;
		System.out.println(Thread.currentThread().getName()+"消费了编号为："+apples[index].id+"的苹果");
		return apples[index];
	}
}

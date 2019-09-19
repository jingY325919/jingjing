package com.jj.threadapple;

import java.util.Random;

/**
 * 生产者
 * @author ASUS
 *
 */
public class Producer implements Runnable{

	private AppleBox box;
	
	public Producer(AppleBox box) {
		this.box = box;
	}
	@Override
	public void run() {
		Random rd = new Random();
		//生产20个苹果
		for (int i = 0; i < 20; i++) {
			Apple apple = new Apple(i+1);
			//存苹果
			box.prouduceApple(apple);
			//输出提示
			System.out.println("生产了："+apple.id+"个苹果");
			//模拟生产时间
			try {
				Thread.sleep(rd.nextInt(500));
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		
	}

}

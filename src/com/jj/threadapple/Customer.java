package com.jj.threadapple;

import java.util.Random;

/**
 * 消费者
 * @author ASUS
 *
 */
public class Customer implements Runnable{

	private AppleBox box;
	
	public Customer(AppleBox box) {
		this.box = box;
	}

	@Override
	public void run() {
		Random rd = new Random();
		//消费20个苹果
		for(int i=0;i<20;i++) {
			
			//取苹果
			Apple apple = box.eatApple();
			//输出提示
			System.out.println("消费了："+apple.id+"个苹果");
			//模拟生产时间
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
}

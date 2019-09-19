  package com.jj.threadapple;

  
/**
 * 测试类
 * @author ASUS
 *
 */
public class Test {

	public static void main(String[] args) {
		AppleBox box  = new AppleBox();
		//创建生产者
		Producer producer = new Producer(box);
		//创建消费者
		Customer customer = new Customer(box);
		Customer customer2 = new Customer(box);
		new Thread(producer).start();
		new Thread(customer).start();
		new Thread(customer2).start();
	}
}

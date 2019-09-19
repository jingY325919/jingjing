package com.jj.threadbank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.jj.threadpool.ThreadPoolManager;

/**
 * 服务器
 * 
 * @author ASUS
 *
 */
public class BankServer {

	public static void main(String[] args) {
		Bank bank = new Bank(10);
		// 创建线程池对象
		ThreadPoolManager manager = new ThreadPoolManager(10);
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			System.out.println("银行服务器启动。。。。。");
			System.out.println("正在监听端口：" + serverSocket.getLocalPort());
			while (true) {
				Socket s = serverSocket.accept();
				System.out.println("客户端：" + s.getRemoteSocketAddress() + "连接上了。。。。");
				BankService service = new BankService(s, bank);
				// 线程
				// new Thread(service).start();
				manager.process(service);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}

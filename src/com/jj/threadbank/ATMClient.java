package com.jj.threadbank;

/**
 * 需求：设计一个简单的银行访问协议
 * 客户机请求：                                      服务器响应                              意义 
 * BALANCE n(账号)                         n 余额                客户机获得账户n的余额
 * DEPOSIT n(账号) a(钱)              n 余额                将数量为a的款项存入账户n
 * WITHDRAM n(账号) a(钱)            n 余额                 从账户n中支取数量为a的款项
 * QUIT			                          无响应		        退出连接
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 第一步：编写客户端
 * ATM客户端
 * @author ASUS
 *
 */
public class ATMClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//创建套接字对象，指定访问服务端的地址以及端口号
		Socket socket = new Socket("localhost",9000);
		System.out.println("ATM客户端启动了，并连接了"+socket.getRemoteSocketAddress());
		Scanner sc = new Scanner(socket.getInputStream());
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		//查询余额的协议
		String protocol = "BALANCE 1\n";
		pw.println(protocol);
		pw.flush();
		//获取服务器响应数据
		String response = sc.nextLine();
		System.out.println("服务器响应："+response);
		
		//存款协议  1表示账号  100表示存款数
		protocol = "DEPOSIT 1 100\n";
		pw.println(protocol);
		pw.flush();		
		//获取服务器端响应数据
		response = sc.nextLine();
		System.out.println("服务器响应："+response);
		
		//取款协议   1表示账号  10表示取款数
		protocol = "WITHDRAM 1 10\n";
		pw.println(protocol);
		pw.flush();		
		//获取服务器端响应数据
		response = sc.nextLine();
		System.out.println("服务器响应："+response);
		
		//退出
		protocol = "QUIT\n";
		pw.println(protocol);
		pw.flush();		
		System.out.println("安全退出。。。。。");
	}
}

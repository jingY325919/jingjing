package com.jj.mutilthreadchat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 创建一个套接字（socket），向服务器的监听端口发出请求：
 * 与服务器正确连接后，调用Socket的getInputStream和getOutputStream方法，获得输入/输出流，开始网络数据的接受和发送：
 * 关闭套通信接字
 * @author ASUS
 *
 */
public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//1.创建套接字对象，绑定IP地址和端口号
		Socket socket = new Socket("localhost",20000);
		Scanner sc = new Scanner(System.in);
		
		Scanner clientReader = new Scanner(socket.getInputStream());
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		boolean flag = true;
		do {
			//与服务器进行通信
			System.out.println("请输入您要对服务器说的话：");
			String line = sc.nextLine();
			//发送数据给服务器
			pw.println(line);
			pw.flush();//刷新缓存
			if("bye".equals(line)) {
				System.out.println("我主动与服务器断开连接。。。。。");
				break;
			}
			//接受服务器会送的数据
			String response = clientReader.nextLine();
			System.out.println("服务器应答："+response);
			if("bye".equals(response)) {
				System.out.println("服务器主动与我断开连接。。。。。");
				break;
			}
			
		}while(flag);
			
			//关闭连接
			socket.close();
			System.out.println("关闭客户端。。。。。");
	}
}

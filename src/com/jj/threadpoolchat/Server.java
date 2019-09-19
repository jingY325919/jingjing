package com.jj.threadpoolchat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.jj.threadpool.ThreadPoolManager;

/**
 * 加入线程池的聊天
 * @author ASUS
 *
 */
public class Server {

	public static void main(String[] args) throws IOException {
		ThreadPoolManager manager = new ThreadPoolManager(10);
		
		ServerSocket ss = new ServerSocket(20000);
		System.out.println("服务器启动了，开始监听端口为："+ss.getLocalPort());
		while(true) {
			Socket s = ss.accept();//等待客户端连接   线程阻塞
			manager.process(new TalkTask(s));
		}
	}
}

class TalkTask implements Runnable{
	private Socket socket;
	public TalkTask(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("客户端："+socket.getInetAddress()+"连接上服务器。。。。");
			Scanner clientReader = new Scanner(socket.getInputStream());
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			do {
				//接收客户端发送的数据
				String response = clientReader.nextLine();
				System.out.println("客户端："+socket.getInetAddress()+"向服务器说："+response);
				if("bye".equals(response)) {
					System.out.println("客户端"+socket.getInetAddress()+"主动与我服务器断开连接。。。");
					break;
				}
				System.out.println("请输入您想对客户端"+socket.getInetAddress()+"说的话：");
				//发送数据到客户端
				String line = sc.nextLine();
				pw.println();
				pw.flush();
				if("bye".equals(line)) {
					System.out.println("我主动与客户端"+socket.getInetAddress()+"断开连接。。。");
					break;
				}
			}while(true);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}

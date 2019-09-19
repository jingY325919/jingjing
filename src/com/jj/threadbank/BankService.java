package com.jj.threadbank;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BankService implements Runnable{
	
	private Socket socket;
	private Bank bank;
	
	private Scanner sc;
	private PrintWriter pw;
	
	public  BankService(Socket socket,Bank bank) {
		this.bank = bank;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			//处理协议，并给出响应信息
			sc = new Scanner(socket.getInputStream());
			pw = new PrintWriter(socket.getOutputStream());
			//解析协议
			parseProtocal();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				this.socket.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}

	//解析协议
	private void parseProtocal() {
		while(true) {
			if(!sc.hasNext()) {
				System.out.println("客户端"+this.socket.getRemoteSocketAddress()+"已经掉线。。。。。");
				return;
			}
			//获取到客户端发送过来的协议信息  DEPOSIT 1 100\n
			//第一种方式：sc.nextLine();  字符串分割 split(" ")
			//第二种方式：sc.next()  读取第一个分割数位置前的数据
			String command = sc.next();//读取第一个分割数位置前的数据
			System.out.println(command);
			if("QUIT".equals(command)) {
				System.out.println("客户端"+this.socket.getRemoteSocketAddress()+"已经掉线。。。。");
				return;
			}
			//获取账号信息
			String accountId = sc.next();
			if("LOGIN".equals(command)) {//LOGIN OK LOGIN ERROR
				String info = sc.next();
				System.out.println(info);
				pw.println(info+" "+this.bank.getBalance(accountId));
				pw.flush();
			}else if("DEPOSIT".equals(command)) {//存款
				double money = sc.nextDouble();
				this.bank.deposit(accountId, money);
			}else if("WITHDRAW".equals(command)) {
				double money = sc.nextDouble();
				this.bank.withdraw(accountId, money);
			}else if( !"BALANCE".equals(command)) {
				pw.println("命令错误！");
				pw.flush();
			}
			
			//显示余额
			pw.println(accountId+" "+this.bank.getBalance(""+accountId));
			pw.flush();
		}
		
	}

}

package com.jj.threadcase01;

import java.io.IOException;

/**
 * 网络扫描
 * 需求：
 * 扫描网络中所有在线的机器
 * ping
 * 1.扫描是一个耗时操作，使用多线程
 * 2.线程扫描到一个在线的机器后，要回调主线程，并通知他的地址
 * 3.Runtime Process
 * @author ASUS
 *
 */
public class NetScan extends Thread{

	private String ipPreFix;//IP地址前三位
	private int start;//IP地址的最后一位起始值
	private int end;//最后一位的结束值
	private String hostName;//网址
	private NotifyNetScan notifyNetScan;//回调接口
	
	//用于IP地址扫描
	public NetScan(String ipPreFix,int start,int end,NotifyNetScan notifyNetScan) {//构造函数
		this.end = end;
		this.ipPreFix = ipPreFix;
		this.notifyNetScan = notifyNetScan;
		this.start = start;
	}
	
	//用于网址
	public NetScan(String hostName,NotifyNetScan notifyNetScan) {//构造函数
		this.notifyNetScan = notifyNetScan;
		this.hostName = hostName;
		this.start = -1;
	}

	@Override
	public void run() {
		if(this.start !=-1) {//IP
			//循环
			for(int i = start;i<=end;i++) {
				String ip = ipPreFix+"."+i;//39.96.52.206
				Ping ping = new Ping();
				try {
					String result = ping.getPingResult(ip);
					IpInfo info = ping.isOnLine(ip, result);
					if(null != this.notifyNetScan && info.isOnLine) {
						this.notifyNetScan.notify(info);
					}
				} catch (IOException e) {
					System.out.println("网址中地址为："+ip+"的机器连接故障");
					e.printStackTrace();
				}
			}
		}else {
			//网址
			Ping ping = new Ping();
			try {
				String result = ping.getPingResult(hostName);
				IpInfo ipInfo = ping.isOnLine(hostName, result);
				System.out.println(result);
			} catch (IOException e) {
				System.out.println("网址中地址为："+hostName+"的机器连接故障");
				e.printStackTrace();
			}
		}
	}	
}
package com.jj.threadcase01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 执行命令
 * @author ASUS
 *
 */
public class Ping {
	
	public String getPingResult(String ip) throws IOException{
		//执行dos系统
		Process p = Runtime.getRuntime().exec("ping "+ip);//ping后面一定要加一个空格，不然Ping不通
		//获取输入流
		InputStream in = p.getInputStream();
		//解决中文乱码
		InputStreamReader ir = new InputStreamReader(in,Charset.forName("GBK"));
		char [] cs = new char[1024];
		int length = -1;
		StringBuffer sb = new StringBuffer();
		while ( (length = ir.read(cs)) != -1) {
			sb.append(new String(cs,0,length));
			
		}
		ir.close();
		in.close();
		return sb.toString();
	}

	
	/**
	 * 解析结果，返回IP地址对象
	 */
	public IpInfo isOnLine(String ip,String result) {
		IpInfo info = new IpInfo();
		//不在线
		if(result.indexOf("无法访问目标主机") != -1 || result.indexOf("请求找不到主机") != -1 ||
				result.indexOf("请求超时") != -1 || result.indexOf("Result timed out") != -1) {
			info.ip = ip;
			info.isOnLine = false;
			info.linkTime = Integer.MAX_VALUE;
			
		}else {
			info.ip = ip;
			info.hostName = ip;//设置主机名与ip一致
			info.isOnLine = true;
			//TODO
			
			
		}
		return info;
	}
}

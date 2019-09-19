package com.jj.threadcase01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 
 * @author ASUS
 *
 */
public class PingTest {

	public static void main(String[] args) throws IOException {
		Process p = Runtime.getRuntime().exec("ping 39.96.52.206");
		InputStream in = p.getInputStream();
		//解决中文乱码
		InputStreamReader ir = new InputStreamReader(in,Charset.forName("GBK"));
		char [] cs = new char[1024];
		//byte [] bt = new byte[1024];
		int length = -1;
		StringBuffer sb = new StringBuffer();
		while((length = ir.read(cs)) != -1) {
			String str = new String(cs,0,length);
			sb.append(str);
		}
		ir.close();
		in.close();
		System.out.println(sb.toString());
	}
}

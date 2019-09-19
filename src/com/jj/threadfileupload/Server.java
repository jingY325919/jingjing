package com.jj.threadfileupload;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 文件上传服务器
 * @author ASUS
 *
 */
public class Server {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(18888);
		System.out.println("服务器启动了，开始监听端口为："+ss.getLocalPort());
		while(true) {
			Socket s = ss.accept();
			
			//获取客户端的图片数据
			InputStream in = s.getInputStream();
			OutputStream out = new FileOutputStream(new File("D:\\jj.jpg"));
			byte [] bt = new byte[1024*10];
			int length = -1;
			while((length=in.read(bt))!= -1) {
				//写入文件中
				out.write(bt,0,length);
			}
			//发送信息给客户端
			DataOutputStream data = new DataOutputStream(s.getOutputStream());
			data.writeUTF("文件上传成功！");
			data.flush();
			data.close();
			out.close();
			in.close();
		}
	}
}

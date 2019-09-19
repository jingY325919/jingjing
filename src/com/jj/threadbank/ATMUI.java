package com.jj.threadbank;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ATMUI implements Runnable {

	protected Shell shlAtm;
	private Text text;
	private Label label_accountId;//账号
	private Label label_1;//余额
	private boolean isOk = false;//是否连接服务器
	private Socket socket;
	private Scanner sc;
	private PrintWriter pw;
	private String accountId;//账号
	private double money;//余额
	private Button button;//进入按钮
	private Button button_find;
	private Button button_save;
	private Button button_get;
	private Button button_exit;
	String [] infos;
	private Label label_money;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ATMUI window = new ATMUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlAtm.open();
		shlAtm.layout();
		while (!shlAtm.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAtm = new Shell();
		shlAtm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shlAtm.setSize(842, 617);
		shlAtm.setText("ATM");
		
		text = new Text(shlAtm, SWT.BORDER);
		text.setBounds(62, 50, 253, 30);
		
		button = new Button(shlAtm, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//从文本框获取账号
				accountId = text.getText().trim();
				if(null == accountId || "".equals(accountId)) {
					String protocal = "LOGIN "+accountId+" ERROR";//查询余额的协议
					pw.println(protocal);
					pw.flush();
				}else {
					text.setText("");
					String protocal = "LOGIN "+accountId+" OK";//查询余额的协议
					pw.println(protocal);
					pw.flush();
					label_accountId.setText(accountId);
				}
			}
		});
		button.setBounds(407, 48, 98, 30);
		button.setText("进入");
		
		Label label = new Label(shlAtm, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label.setBounds(62, 121, 98, 30);
		label.setText("当前账号：");
		
		label_accountId = new Label(shlAtm, SWT.NONE);
		label_accountId.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_accountId.setBounds(201, 121, 285, 30);
		
		 button_find = new Button(shlAtm, SWT.NONE);
		 //查询
		 button_find.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		pw.println("BALANCE "+accountId);
		 		pw.flush();
		 	}
		 });
		button_find.setBounds(62, 222, 98, 30);
		button_find.setText("查询");
		
		 button_save = new Button(shlAtm, SWT.NONE);
		 //存款
		 button_save.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		String amount = text.getText().trim();
		 		if(null!=amount && !"".equals(amount)) {
		 			pw.println("DEPOSIT "+accountId+" "+amount);
		 			pw.flush();
		 		}
		 		
		 	}
		 });
		button_save.setBounds(62, 337, 98, 30);
		button_save.setText("存款");
		
		 button_get = new Button(shlAtm, SWT.NONE);
		 //取款
		 button_get.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		String amount = text.getText().trim();
		 		System.out.println(amount+"--------");
		 		if(null!=amount && !"".equals(amount)) {
		 			pw.println("WITHDRAW "+accountId+" "+amount);
		 			pw.flush();
		 		}
		 	}
		 });
		button_get.setBounds(407, 222, 98, 30);
		button_get.setText("取款");
		
		 button_exit = new Button(shlAtm, SWT.NONE);
		 //退出
		 button_exit.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
//		 		pw.println("QUIT");
//	 			pw.flush();
		 		System.exit(0);
		 	}
		 });
		button_exit.setBounds(407, 337, 98, 30);
		button_exit.setText("退出");
		
		 label_1 = new Label(shlAtm, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 18, SWT.NORMAL));
		label_1.setBounds(136, 414, 103, 47);
		label_1.setText("余额：");
		
		
		label_money = new Label(shlAtm, SWT.NONE);
		label_money.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 18, SWT.NORMAL));
		label_money.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_money.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_money.setBounds(260, 414, 171, 47);
		
		connectServer();
		button_find.setEnabled(false);
		button_get.setEnabled(false);
		button_save.setEnabled(false);
		button_exit.setEnabled(false);

	}

	private void connectServer() {
		try {
			socket = new Socket("localhost",9999);
			sc = new Scanner(socket.getInputStream());
			pw = new PrintWriter(socket.getOutputStream());
			Thread tt = new Thread(this);
			tt.start();
			System.out.println("连接成功");
			isOk = true;
		} catch (IOException e) {
			isOk = false;
			accountId  = "";
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		while(true) {
			//获取响应信息
			String info  = sc.nextLine();
			System.out.println(info);
			infos = info.split(" ");
			if("OK".equals(infos[0])) {
				//禁用登陆按钮，开启其他按钮
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						button.setEnabled(false);
						button_find.setEnabled(true);
						button_get.setEnabled(true);
						button_save.setEnabled(true);
						button_exit.setEnabled(true);
						
					}
				});
			}else if("ERROR".equals(infos[0])) {
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						MessageBox mb = new MessageBox(shlAtm);
						mb.setText("错误提示");
						mb.setMessage("账号不存在");
						mb.open();
					}
				});
			}else if ("QUIT".equals(info)) {
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						button.setEnabled(true);
						button_find.setEnabled(false);
						button_get.setEnabled(false);
						button_save.setEnabled(false);
						button_exit.setEnabled(false);
						
					}
				});
			}else if(infos.length==2) {
				//System.out.println(infos[1]);
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
					//	System.out.println("00000"+infos[1]);
						label_money.setText(infos[1]);
						
					}
				});
			}
		}
	}
}

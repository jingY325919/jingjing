package com.jj.threadbank;

import java.util.ArrayList;
import java.util.List;

/**
 * 银行类
 * @author ASUS
 *
 */
public class Bank {

	private List<BankAccount> accounts;//银行账户集合
	
	/**
	 * 初始化银行账户
	 * @param initialSize
	 */
	public Bank(int initialSize) {
		accounts = new ArrayList<BankAccount>();
		for(int i = 0;i<initialSize;i++) {
			accounts.add( new BankAccount("62270029"+i, 100));
		}
	}
	
	/**
	 * 银行的存款业务
	 * @param accountId：账号
	 * @param amount：存入的金额
	 */
	public void deposit(String accountId,double amount) {
		//遍历所有的账户信息，获取账号与之比较，相同则修改当前账户的余额信息
		for(BankAccount ba:accounts) {
			if(accountId.equals(ba.getAccountId())) {
				ba.deposit(amount);//存钱
				break;
			}
		}
	}
	
	/**
	 * 取款
	 * @param accountId：账号
	 * @param amount：取款的金额
	 */
	public void withdraw(String accountId,double amount) {
		for(BankAccount ba:accounts) {
			if(accountId.equals(ba.getAccountId())) {
				ba.withdraw(amount);
				break;
			}
		}
	}
	
	/**
	 * 查询账号
	 * @param accountId
	 * @return
	 */
	public boolean getAccountId(String accountId) {
		for(BankAccount ba:accounts) {
			if(accountId.equals(ba.getAccountId())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 查询余额
	 * @param accountId
	 * @return
	 */
	public double getBalance(String accountId) {
		for(BankAccount ba:accounts) {
			if(accountId.equals(ba.getAccountId())) {
				return ba.getBalance();
			}
		}
		return -1;
	}
}

package com.wangcw.framework.base.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
  *@description   MD5加密工具类
  *@author        王财文
  *@version       1.0 2018-3-27
  */


public class MD5 {
	
	/**
	 * 使用指定SALT加密原始字符串
	 * @param saw 原始字符串
	 * @param salt 加密盐
	 * @return 加密后的字符串
	 */
	public static String getMd5(String saw,String salt) {
		return new Md5Hash(saw, salt).toHex();
	}

	/**
	 * 使用指定SALT加密原始字符串
	 * @param saw 原始字符串
	 * @param salt 加密盐
	 * @return 加密后的字符串
	 */
	public static String getMd5(char[] saw,String salt) {
		return new Md5Hash(saw, salt).toHex();
	}

	public static void main(String[] args) {
		System.out.println(MD5.getMd5("123456", "admin".toLowerCase()));
		System.out.println(MD5.getMd5("111111", "jay".toLowerCase()));
		System.out.println(MD5.getMd5("111111", "hr".toLowerCase()));
		System.out.println(MD5.getMd5("111111", "manager".toLowerCase()));
		System.out.println(MD5.getMd5("demo", "demo".toLowerCase()));
	}
	
}

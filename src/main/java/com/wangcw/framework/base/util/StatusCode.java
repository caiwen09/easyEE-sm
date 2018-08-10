package com.wangcw.framework.base.util;
/**
  *@description   服务器端返回状态码列表，作为JSON输出信息时statusCode属性的可选值
  *@author        王财文
  *@version       1.0 2018-3-27
  */


public class StatusCode {
	/*
	 * Ajax 请求响应信息 <br/>
	 *  {  <br/>
	 *  	statusCode: 响应状态码;  <br/>
	 *  	msg：响应消息;   <br/>
	 *  	callback:closeFn, <br/>
	 *  	locationUrl: <br/>
	 *   } <br/>
	 */
	public static final int OK=200; // 操作正常
	public static final int ERROR=300; // 操作失败
	public static final int TIMEOUT=301; // 用户超时
	public static final int NO_PERMISSSION=401; // 权限不足
}

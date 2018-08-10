package com.wangcw.framework.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


/**
  *@description   日志信息实体类
  *@author        王财文
  *@version       1.0 2018-3-27
  */


public class SysLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer logId;
	private String action;
	private String parameters;
	private String res;
	private String account;
	private String ip;
	private Date logTime;


	public SysLog() {
	}

	public SysLog(String action, String parameters, String res, String account,
			String ip, Date logTime) {
		this.action = action;
		this.parameters = parameters;
		this.res = res;
		this.account = account;
		this.ip = ip;
		this.logTime = logTime;
	}


	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getParameters() {
		return this.parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getRes() {
		return this.res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getIp() {
		return this.ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

}
package com.wangcw.framework.system.shiro;

import cn.easyproject.easyshiro.EasyUsernamePasswordEndcodeToken;
import com.wangcw.framework.base.util.MD5;

/**
  *@description    * 自定义用户名密码加密 Token
  *                - 继承EasyUsernamePasswordEndcodeToken，实现密码加密
  *@author        王财文
  *@version       1.0 2018-3-28
  */


public class UsernamePasswordEncodeToken extends EasyUsernamePasswordEndcodeToken{
	
		private static final long serialVersionUID = 1L;

	/*--------------------------------------------
   | UsernamePasswordToken 令牌内置属性  |
   ============================================*/
	
	/*--------------------------------------------
   | 数据库用户列信息，相关属性 由EasyJdbcRealm在登录认证时，从数据库查询并初始化  |
   ============================================*/
	// TODO 数据库列信息
	private Integer userId;
	private String name;
	private String realName;
	/**
	 * 用户状态：0启用；1禁用；2删除
	 */
	private Integer status;

	
	@Override
	public String encodePassword(){
		String pwd= MD5.getMd5(super.getPassword(),getName().toLowerCase());
		return pwd;
	}
	

	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getRealName() {
		return realName;
	}



	public void setRealName(String realName) {
		this.realName = realName;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



}

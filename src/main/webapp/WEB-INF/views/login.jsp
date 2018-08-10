<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.authc.AuthenticationException"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags"      prefix="shiro" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>">

<title>User Login</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<!-- 全局变量 -->
<script type="text/javascript">
	var EasyEE={
		basePath:'<%=basePath%>'
	}
</script>

<!-- EasyUI CSS -->
<link rel="stylesheet" type="text/css" href="static/easyui/themes/metro-blue/easyui.css" id="themeLink">
<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css">
<!-- EasyUI JS -->
<script type="text/javascript" src="static/easyui/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- EasyUIEx -->
<link rel="stylesheet" type="text/css" href="static/easyuiex/css/easyuiex.css">
<script type="text/javascript" src="static/easyuiex/easy.easyuiex.min.js"></script>
<script type="text/javascript" src="static/easyuiex/easy.easyuiex-validate.js"></script>
<!-- EasyUIEx的默认消息语言为中文，使用其他语言需要导入相应语言文件 -->
<script type="text/javascript" src="static/easyuiex/lang/easy.easyuiex-lang-zh_CN.js"></script>
<%-- jquery Cookie plugin --%>
<script type="text/javascript" src="static/easyee/jquery.cookie.js"></script>
<!-- 自定义页面相关JS -->
<script type="text/javascript" src="js/login.js"></script>

<style type="text/css">
	* {
		font-size: 15px;
		font-family: 'Open Sans', Arial, Helvetica, sans-serif;
	}

	h1,h1 span {
		font-size: 48px;
		font-family: 'Roboto', '微软雅黑';
		font-weight: 700;
	}

	.footer {
		margin: 10px auto;
	}
</style>


<!-- 登录消息提示JS -->
<c:if test="${!empty MSG}">
	<script type="text/javascript">
		$(function() {
			uiEx.alert("${MSG }", "info");
		})
	</script>
	<c:remove var="MSG" scope="session"/>
</c:if>
</head>

<body>
	<div style="text-align: center;overflow:auto;width:100%;height:100%;margin: 10px auto;">
		<h1><span style="color:#8FC31F">Wang</span><span style="color:#376E91">C</span><span style="color:#376E91">W</span>-<span style="">Framework SM</span></h1>

		<div style="margin: 10px auto;"></div>

		<c:if test="${!empty IPLock}">
			<span style="color:#ff0000; font-weight:bold">您的 IP 地址由于连续登录错误过多，已被锁定 2 小时，请稍后再试。</span>
		</c:if>

		<div style="margin: 0 auto;width: 500px;">
			<div class="easyui-panel" title="用户登录" style="width:500px;">
				<div style="padding:10px 60px 20px 60px;">
					<form id="loginForm" class="easyui-form" method="post" data-options="novalidate:true" action="toLogin">
						<table cellpadding="5" style="">
							<tr>
								<td width="90">主题：</td>
								<td><input id="themeCombobox" class="easyui-combobox" data-options="" /></td>
							</tr>
							<tr>
								<td width="90">用户名:</td>
								<td>
									<input class="easyui-textbox" type="text" name="name" id="username"style="height:30px;width: 190px;"
										   data-options="validType:[],required:true" value="admin"/>
								</td>
							</tr>
							<tr>
								<td>密码:</td>
								<td><input class="easyui-textbox" type="password" name="password" style="height:30px;width: 190px;"
										   data-options="required:true" value="123456"/>
								</td>
							</tr>
							<%--暂时注释验证码，恢复的同时也要开启shiro的验证码验证--%>
							<%--<tr>
								<td>验证码:</td>
								<td>
									<input class="easyui-validatebox textbox" id="captcha" name="captcha" style="height:30px;width: 80px;"
										   data-options="required:true, validType:'minLength[4]' , tipPosition:'right',position:'bottom', deltaX:105" maxlength="4">
									<div style="display: none; float: right; border: 1px solid #ccc;" id="vcTr">
										<img title="点击切换" alt="加载中..." align="middle"
											 style="cursor: pointer;" width="100" height="28" id="vcImg" src="jsp/VerifyCode.jsp">
									</div>
								</td>
							</tr>--%>
						</table>
					</form>
					<div style="text-align:center;padding:5px">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="loginBtn" iconCls="icon-man" style="padding: 3px 10px">Login</a>
						<a href="javascript:void(0)" iconCls="icon-clear" class="easyui-linkbutton" onclick="uiEx.clearForm('#loginForm')"style="padding: 3px 10px">Reset</a>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<p>
				Copyright © 2018 - 2099     Caiwen Wang, All Rights Reserved
			</p>
		</div>
	</div>
</body>
</html>

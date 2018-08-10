<%@page import="org.apache.shiro.subject.support.DefaultSubjectContext"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="<%=basePath%>">
    
    <title>用户主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">


<%-- 全局变量 --%>
<script type="text/javascript">
  var EasyEE={};
  EasyEE.basePath='<%=basePath%>';
  EasyEE.menuTreeJson=${menuTreeJson};
</script>

<%-- EasyUI CSS --%>
<link rel="stylesheet" type="text/css" href="static/easyui/themes/metro-blue/easyui.css" id="themeLink">
<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="static/easyui/portal.css">

<%-- EasyUI JS & Extension JS--%>
<script type="text/javascript" src="static/easyui/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/easyui/datagrid-dnd.js"></script>
<script type="text/javascript" src="static/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="static/easyui/jquery.portal.js"></script>
<script type="text/javascript" src="static/easyui/treegrid-dnd.js"></script>

<%-- EasyUIEx --%>
<link rel="stylesheet" type="text/css" href="static/easyuiex/css/easyuiex.css">
<script type="text/javascript" src="static/easyuiex/easy.easyuiex.min.js"></script>
<script type="text/javascript" src="static/easyuiex/easy.easyuiex-validate.js"></script>

<%-- 使用 EasyUIEx的 easy.jquery.edatagrid.js 代替 jquery.edatagrid.js --%>
<script type="text/javascript" src="static/easyuiex/easy.jquery.edatagrid.js"></script>
<%-- EasyUIEx的默认消息语言为中文，使用其他语言需要导入相应语言文件 --%>
<script type="text/javascript" src="static/easyuiex/lang/easy.easyuiex-lang-zh_CN.js"></script>

<%-- Echarts --%>
<script type="text/javascript" src="static/echarts/themes/default.js"></script>
<script type="text/javascript" src="static/echarts/themes/blue.js"></script>
<script type="text/javascript" src="static/echarts/themes/dark.js"></script>
<script type="text/javascript" src="static/echarts/themes/gray.js"></script>
<script type="text/javascript" src="static/echarts/themes/green.js"></script>
<script type="text/javascript" src="static/echarts/themes/helianthus.js"></script>
<script type="text/javascript" src="static/echarts/themes/infographic.js"></script>
<script type="text/javascript" src="static/echarts/themes/macarons.js"></script>
<script type="text/javascript" src="static/echarts/themes/red.js"></script>
<script type="text/javascript" src="static/echarts/themes/shine.js"></script>
<script type="text/javascript" src="static/echarts/echarts-all.js"></script>

<%-- EasyEE 全局文件 --%>
<link rel="stylesheet" type="text/css" href="static/style/easyee.main.css">
<script type="text/javascript" src="static/easyee/lang/easyee-zh_CN.js"></script>
<script type="text/javascript" src="static/easyee/easyee-sm.main.js"></script>

<%-- jquery Cookie plugin --%>
<script type="text/javascript" src="static/easyee/jquery.cookie.js"></script>

<%-- 自定义页面相关JS --%>
<script type="text/javascript" src="js/main/main.js"></script>

<%request.setAttribute("now", new java.util.Date());%>
</head>
  
<%-- 将布局放在body --%>
<body class="easyui-layout" >

		<%-- Head --%>
		<div id="easysshtop" data-options="region:'north',split:true" style="height:120px;padding: 0 20px;" title="Welcome to Wang Caiwen Development Platform">
			<h1 style="display: inline;line-height: 80px;font-size: 32px;font-family: 'Roboto Condensed', 微软雅黑; font-weight: 700; ">
				<span style="color:#8FC31F">Wang</span>
				<span style="color:#376E91">CW</span>
				<span style="">Framework SM</span>
			</h1>
			<div style="float: right; padding-top: 20px;line-height: 30px;text-align: right; ">

				<span style="font-weight:bold;color:#376E91">欢迎${USER.realName }</span>
				<span id="showtime" style="font-weight:bold"><fmt:formatDate value="${now }" pattern="yyyy年MM月dd日 HH:mm:ss"/></span>
				<br/>
				<input id="themeCombobox" class="easyui-combobox" style="width: 120px;" data-options="hasDownArrow:false,icons:[{'iconCls':'icon-palette'}]"/>
				<a id="btnChangePwd" class="easyui-linkbutton" data-options="iconCls:'icon-lock_edit'">Change Password</a>
				<a id="btnExit"  href="logout" class="easyui-linkbutton"  data-options="iconCls:'icon-monitor_go'">Exit System</a>
			</div>
		</div>


		<%-- Menu --%>
		<div data-options="region:'west',title:'功能菜单',split:true" style="width:180px;">
			<div style="margin:10px 0;"></div>
			<ul id="menu" class="easyui-tree" data-options="animate:true,lines:false"></ul>
		</div>

		<%-- Content --%>
		<div data-options="region:'center',split:true"  >
			<div class="easyui-tabs" id="tabs" data-options="fit:true">
				<div title="框架手册"  iconCls="icon-book" data-options="fit:true, content:'<iframe src=\'doc/easyee-sm-readme-single_zh_CN.html?v=1\' frameborder=\'0\' style=\'height:500px;width:100%;\'  ></iframe>'"></div>
			</div>
		</div>

		<%-- Copyright --%>
		<div data-options="region:'south',split:false" style="height:30px;line-height: 30px;text-align: center;">
			Copyright © 2018 - 2099     Caiwen Wang, All Rights Reserved
			</div>
		</div>

		<%-- ##################Tab选项卡的右键菜单，不能删除################## --%>
		<div id="tabsMenu" class="easyui-menu" style="width:120px;">
			   <div name="close"   data-options="iconCls:'icon-close'">关闭标签</div>
			   <div name="other"   data-options="">关闭其他标签</div>
			   <div name="all"  data-options="">关闭所有标签</div>
			   <div class="menu-sep"></div>
			   <div name="closeRight">关闭右侧标签</div>
			   <div name="closeLeft">关闭左侧标签</div>
			   <div class="menu-sep"></div>
			  <div name="refresh"  data-options="iconCls:'icon-reload'">刷新标签</div>
		</div>

		<%-- 密码修改Dialog --%>
		<div id="dialogChangePwd" class="easyui-dialog" title="密码修改" style="width:400px;height:200px;" data-options="iconCls:'icon-edit',resizable:true,modal:true,closed:true">
			<form id="formChangePwd" action="SysUser/changePwd" method="post" class="easyui-form" data-options="novalidate:true">
				<table height="140" align="center">
					<tr>
						<td>密码 :</td>
						<td>
							<input id="upwd" name="password" class="easyui-textbox" type="password" data-options="required:true,validType:'minLength[6]'">
						</td>
					</tr>
					<tr>
						<td>新密码 :</td>
						<td><input id="newpwd" name="newPwd" class="easyui-textbox" type="password" data-options="required:true,validType:'minLength[6]'">

						</td>
					</tr>
					<tr>
						<td>重复密码 :</td>
						<td>
							<input id="renewpwd" name="confirmPwd" class="easyui-textbox" type="password" data-options="required:true,validType:['minLength[6]','equals[\'#newpwd\',\'与新密码不一致.\']']">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<a href="javascript:void(0)" id="submitChangPwd" class="easyui-linkbutton"data-options="iconCls:'icon-edit'">更新密码</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="uiEx.clearForm('#formChangePwd')" data-options="iconCls:'icon-clear'">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>

</body>
</html>

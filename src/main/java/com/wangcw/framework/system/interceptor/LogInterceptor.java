package com.wangcw.framework.system.interceptor;

import com.wangcw.framework.system.entity.SysLog;
import com.wangcw.framework.system.entity.SysUser;
import com.wangcw.framework.system.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

/**
  *@description   系统操作，日志记录拦截器
  *@author        王财文
  *@version       1.0 2018-3-27
  */


public class LogInterceptor implements HandlerInterceptor {

	@Resource
	SysLogService sysLogService;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
		if(request.getRequestURI().contains("captcha") || !SecurityUtils.getSubject().isAuthenticated()){
			return;
		}
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress()+":"+addr.getHostName();
		StringBuilder parameters = new StringBuilder();
		if (request.getParameterMap() != null) {
			Map<String, String[]> map = request.getParameterMap();
			for (Entry<String, String[]> entry : map.entrySet()) {
				if (entry.getKey().contains("password") || entry.getKey().contains("pwd")) {
					parameters.append(entry.getKey() + ": can't show; ");
					continue;
				}
				parameters.append(entry.getKey() + ": " + Arrays.toString(entry.getValue()) + "; ");
			}
			if (parameters.toString().endsWith("; ")) {
				parameters.substring(0, parameters.length() - 2);
			}
		}
		// 利用反射， 防止出现 ClassCastException
		Object sysUser= request.getSession().getAttribute("USER");
		String account = "未登录";
		if (sysUser != null) {
			if(sysUser instanceof SysUser){
				String name=((SysUser) sysUser).getName();
				String realName=((SysUser) sysUser).getRealName();
				account = name + "[" + realName + "]";
			}else{
				String name=sysUser.getClass().getMethod("getName").invoke(sysUser).toString();
				String realName=sysUser.getClass().getMethod("getRealName").invoke(sysUser).toString();
				account = name + "[" + realName + "]";
			}
		}
		/*
		 * 添加日志
		 */
		String invokeBeanName = "";
		if(handler instanceof HandlerMethod){
			HandlerMethod hm = ((HandlerMethod) handler);
			invokeBeanName = hm.getBean().getClass().getName() + "#" + hm.getMethod().getName();
		}else{
			invokeBeanName = handler.getClass().getName();
		}
		
		String res = "";
		if (ex != null) {
			res = ex.getMessage() + " [" + ex.getClass().getName() + "]";
		} else {
			res = "status Code:["+response.getStatus() + "]";
		}
		SysLog sysLog = new SysLog(request.getRequestURI() + " [" + invokeBeanName + "]", parameters.toString(), res, account, ip, new Date());
		sysLogService.add(sysLog);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		return true;
	}

}

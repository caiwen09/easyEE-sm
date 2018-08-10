package com.wangcw.framework.system.shiro;

import cn.easyproject.easyshiro.EasyAuthenticationInterceptor;
import com.alibaba.fastjson.JSONObject;
import com.wangcw.framework.system.entity.SysMenuPermission;
import com.wangcw.framework.system.entity.SysUser;
import com.wangcw.framework.system.service.SysMenuPermissionService;
import com.wangcw.framework.system.service.SysOperationPermissionService;
import com.wangcw.framework.system.util.EasyUITreeEntity;
import com.wangcw.framework.system.util.EasyUIUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 *@description    认证拦截器
 *  			   EasyJdbcRealm执行认证之后执行,认证成功或失败拦截器
 *@author        王财文
 *@version       1.0 2018-3-2
 */

public class AuthenticationInterceptor implements EasyAuthenticationInterceptor {

	@Resource
	private SysOperationPermissionService sysOperationPermissionService;
	@Resource
	private SysMenuPermissionService sysMenuPermissionService;

	public static Logger LOG = LoggerFactory.getLogger("system");
	/**
	 *        将用户信息和相关的菜单信息存入session中
	 * @param request
	 * @param response
	 * @param token
	 * @throws Exception
	 */
	@Override
	public void afterSuccess(ServletRequest request, ServletResponse response, AuthenticationToken token)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		// 不要强制转换，防止 devtools 的 RestartClassLoader 导致的 cast exception
		UsernamePasswordEncodeToken downToken = new UsernamePasswordEncodeToken();
		downToken.setUserId(Integer.valueOf(token.getClass().getMethod("getUserId").invoke(token).toString()));
		downToken.setName(token.getClass().getMethod("getName").invoke(token).toString());
		downToken.setPassword((char[])token.getClass().getMethod("getPassword").invoke(token));
		downToken.setRealName(token.getClass().getMethod("getRealName").invoke(token).toString());
		downToken.setStatus(Integer.valueOf(token.getClass().getMethod("getStatus").invoke(token).toString()));
		// 用户锁定
		if (downToken.getStatus() == SysUser.STATUS_LOCK) {
			subject.logout();
			throw new LockedAccountException("账户已锁定！");
		}
		// 存入用户信息到Session
		// SysUser sysUser=new SysUser(downToken.getName(), new
		// String(downToken.getPassword()));
		SysUser sysUser = new SysUser(downToken.getName(), "");
		sysUser.setPassword(new String(downToken.getPassword()));
		sysUser.setRealName(downToken.getRealName());
		sysUser.setStatus(downToken.getStatus());
		sysUser.setUserId(downToken.getUserId());
		
		subject.getSession().setAttribute("USER", sysUser);

		// 初始化菜单列表
		initMenu(subject.getSession(), downToken);

		// 保存所有权限对应的权限名称，权限备注（获取所有一级菜单、二级菜单和二级菜单内所有操作）
		subject.getSession().setAttribute("operationsName", sysOperationPermissionService.getAllOpreationNames());
		LOG.info("用户:{}认证成功", downToken.getName());
	}

	@Override
	public void afterFailure(ServletRequest request, ServletResponse response, AuthenticationToken token, Exception e)throws Exception {
		LOG.info("认证失败token：{}", JSONObject.toJSON(token));
		if(null != e){
			LOG.info("认证失败出现异常:", e);
		}
	}

	/**
	 * 设置当前用户的菜单
	 * 
	 * @param session
	 * @param token
	 */
	public void initMenu(Session session, UsernamePasswordEncodeToken token) {
		List<SysMenuPermission> menus = sysMenuPermissionService.listByUserId(token.getUserId());
		//将菜单权限集合转为EasyUI菜单Tree
		List<EasyUITreeEntity> list = EasyUIUtil.getEasyUITreeFromUserMenuPermission(menus);
		String menuTreeJson = JSONObject.toJSONString(list);
		session.setAttribute("menuTreeJson", menuTreeJson); // 菜单权限集合 info
	}

	public void setSysOperationPermissionService(SysOperationPermissionService sysOperationPermissionService) {
		this.sysOperationPermissionService = sysOperationPermissionService;
	}

}

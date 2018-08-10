package com.wangcw.framework.system.shiro;

import cn.easyproject.easyshiro.EasyJdbcRealmInterceptor;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *@description   授权拦截器，
 * 			  EasyJdbcRealm执行认证之后执行认证拦截器AuthenticationInterceptor，
 * 			  之后执行EasyJdbcRealm授权后，再执行该授权拦截器
 * 	          可以认证和授权信息获得后， 对SimpleAuthenticationInfo认证和SimpleAuthorizationInfo授权信息进行额外处理
 *@author        王财文
 *@version       1.0 2018-3-2
 */

public class RealmInterceptor implements EasyJdbcRealmInterceptor {

	@Override
	public void afterDoGetAuthenticationInfo(SimpleAuthenticationInfo info) {
	}

	@Override
	public void afterDoGetAuthorizationInfo(SimpleAuthorizationInfo info) {
		Set<String> permissions = new LinkedHashSet<String>();
		Set<String> sets = info.getStringPermissions();

		for (String permissionString : sets) {
			if(permissionString!=null&&(!permissionString.trim().equals(""))){
				for (String o : permissionString.split("#|,")) {
					if (o.trim().length() > 0) {
						permissions.add(o.trim());
					}
				}
			}
		}
		info.setStringPermissions(permissions);
	}

}

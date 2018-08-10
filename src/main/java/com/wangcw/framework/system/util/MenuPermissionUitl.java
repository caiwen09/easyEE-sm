package com.wangcw.framework.system.util;

import com.wangcw.framework.system.entity.SysMenuPermission;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.List;
/**
  *@description   菜单操作工具类
  *@author        王财文
  *@version       1.0 2018-3-28
  */

public class MenuPermissionUitl {
	/**
	 * 存储菜单权限集合等数据的OGNL上下文 操作完成后清除
	 */
	public static OgnlContext context;
	
	/**
	 * 将菜单权限树初始化，返回根节点
	 * 
	 *         List<SysMenuPermission> 菜单权限
	 * @return EasyUI菜单tree集合
	 */
	@SuppressWarnings("unchecked")
	public static List<SysMenuPermission> sysPermissionMenuInit(List<SysMenuPermission> allMenus){
		context = new OgnlContext();
		context.put("menus", allMenus);
		
		List<SysMenuPermission> rootMenus=null;
		try {
			rootMenus = (List<SysMenuPermission>) Ognl.getValue(
					"#menus.{?#this.sysMenuPermission==null}", context,
					context.getRoot());
			
			for (SysMenuPermission root : rootMenus) {
				recursionInitMenuPermission(root); // 递归初始化
			}
			
		} catch (OgnlException e) {
			e.printStackTrace();
		} finally{
			context.clear();
			context = null;
		}
		
		
		return rootMenus;
	}
	
	/**
	 * 递归初始化菜单权限
	 * 
	 *            EasyUITree Root对象
	 */
	@SuppressWarnings("unchecked")
	private static void recursionInitMenuPermission(SysMenuPermission menuPermission) {
		Integer menuPermissionId = menuPermission.getMenuPermissionId();

		try {
			List<SysMenuPermission> childMenus = (List<SysMenuPermission>) Ognl.getValue(
					"#menus.{?#this.sysMenuPermission!=null&&#this.sysMenuPermission.menuPermissionId=="
							+ menuPermissionId + "}", context, context.getRoot());
			// 目录
			if (childMenus.size() > 0) {
				for (SysMenuPermission child : childMenus) {
					menuPermission.getSysMenuPermissions().add(child);
					recursionInitMenuPermission(child); // 递归初始化
				}
			
			}
		} catch (OgnlException e) {
			e.printStackTrace();
		}
	}
}

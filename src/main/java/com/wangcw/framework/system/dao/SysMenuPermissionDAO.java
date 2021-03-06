package com.wangcw.framework.system.dao;

import com.wangcw.framework.system.entity.SysMenuPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface SysMenuPermissionDAO{
	public void add(SysMenuPermission sysMenuPermission);
	
	// delete sys_role_menu
	public void deleteMenuRoles(SysMenuPermission sysMenuPermission);
	// delete sys_role_operation
	public void deleteMenuOperationRoles(SysMenuPermission sysMenuPermission);
	// delete sys_operation
	public void deleteMenuOperation(SysMenuPermission sysMenuPermission);
	// delete sys_menu
	public void delete(SysMenuPermission sysMenuPermission);
	
	
	public void update(SysMenuPermission sysMenuPermission);
	public SysMenuPermission get(int id);
	public List<SysMenuPermission> list();
	public List<Map<String, String>> listAll();
	public int getMaxSortNum(@Param("parentId") Integer parentId);
	public int getSortNum(Integer menuPermissionId);
	public int hashChildMenu(Integer menuPermissionId);
	

	public List<String> getIdsByRoleId(Integer roleId);
	
	public SysMenuPermission getUpNeighbor(SysMenuPermission sysMenuPermission);
	public SysMenuPermission getDownNeighbor(SysMenuPermission sysMenuPermission);
	public void move(SysMenuPermission sysMenuPermission);
	

	public List<SysMenuPermission> listByUserId(int userId);
}

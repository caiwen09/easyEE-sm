package com.wangcw.framework.system.service;

import com.wangcw.framework.system.entity.SysOperationPermission;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface SysOperationPermissionService{
	public void add(SysOperationPermission sysOperationPermission);

	public void delete(String[] ids);

	public void deleteByMenuPermissionId(int menuPermissionId);

	public void delete(SysOperationPermission sysOperationPermission);

	public void update(SysOperationPermission sysOperationPermission);

	@Transactional(readOnly=true)
	public SysOperationPermission get(int id);

	@Transactional(readOnly=true)
	public List<Map> list(Integer menuId);

	/**
	 * 查询角色的所有操作权限ID
	 * @param roleId
	 */
	@Transactional(readOnly=true)
	public List<String> getIdsByRoleId(Integer roleId);

	/**
	 * 获得权限动作和名称备注的映射，包括Menu和Operation权限
	 */
	@Transactional(readOnly=true)
	public Map<String,String> getAllOpreationNames();
}

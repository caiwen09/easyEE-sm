package com.wangcw.framework.system.service;

import com.wangcw.framework.base.pagination.PageBean;
import com.wangcw.framework.system.criteria.SysRoleCriteria;
import com.wangcw.framework.system.entity.SysRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface SysRoleService{
	public void add(SysRole SysRole);

	public void delete(int id);

	public void update(SysRole SysRole);

	@Transactional(readOnly=true)
	public SysRole get(int id);

	@Transactional(readOnly=true)
	public List list();

	@Transactional(readOnly=true)
	public void findByPage(PageBean pb, SysRoleCriteria sysrole);

	@Transactional(readOnly=true)
	public int findMaxPage(int rowsPerPage);

	@Transactional(readOnly=true)
	public boolean existsName(String name);

	@Transactional(readOnly=true)
	public boolean existsName(String name, Integer roleId);

	/**
	 * 获得角色所有的权限Id集合（包括菜单权限和操作权限）
	 * @param roleId
	 * @return
	 */
	@Transactional(readOnly=true)
	public List getAllPermissionsIds(int roleId);
	
	
}

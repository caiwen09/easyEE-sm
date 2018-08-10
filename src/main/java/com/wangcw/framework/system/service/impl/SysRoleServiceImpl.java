package com.wangcw.framework.system.service.impl;

import com.wangcw.framework.base.pagination.PageBean;
import com.wangcw.framework.base.service.BaseService;
import com.wangcw.framework.system.criteria.SysRoleCriteria;
import com.wangcw.framework.system.dao.SysRoleDAO;
import com.wangcw.framework.system.entity.SysRole;
import com.wangcw.framework.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sysRoleService")
@SuppressWarnings("rawtypes")
public class SysRoleServiceImpl extends BaseService implements SysRoleService {

	@Resource
	SysRoleDAO sysRoleDAO;
	
	@Override
	public void add(SysRole sysRole) {
		sysRoleDAO.add(sysRole);
		if(sysRole.getSysMenuPermissions().size()>0){
			sysRoleDAO.addRoleMenus(sysRole);
		}
		if(sysRole.getSysOperationPermissions().size()>0){
			sysRoleDAO.addRoleOperations(sysRole);
		}
		
	}

	@Override
	public void delete(int roleId) {
		sysRoleDAO.deleteRoleMenus(roleId);
		sysRoleDAO.deleteRoleOperations(roleId);
		sysRoleDAO.deleteRoleUsers(roleId);
		sysRoleDAO.delete(roleId);
	}

	@Override
	public void update(SysRole sysRole) {
		sysRoleDAO.deleteRoleMenus(sysRole.getRoleId());
		sysRoleDAO.deleteRoleOperations(sysRole.getRoleId());
		sysRoleDAO.update(sysRole);
		if(sysRole.getSysMenuPermissions().size()>0){
			sysRoleDAO.addRoleMenus(sysRole);
		}
		if(sysRole.getSysOperationPermissions().size()>0){
			sysRoleDAO.addRoleOperations(sysRole);
		}
	}

	@Override
	public SysRole get(int id) {
		return sysRoleDAO.get( id);
	}

	@Override
	public List list() {
		return sysRoleDAO.list();
	}

	@Override
	public void findByPage(PageBean pb, SysRoleCriteria sysRoleCriteria) {
		pb.setFrom("Sys_Role s");
		pb.setSelect("s.*");
		pb.setEasyCriteria(sysRoleCriteria);
		
		// 按条件分页查询
		sysRoleDAO.pagination(pb);
	}

	@Override
	public int findMaxPage(int rowsPerPage) {
		return (sysRoleDAO.findMaxRow()-1)/rowsPerPage+1;
	}

	@Override
	public List getAllPermissionsIds(int roleId) {
		//查询角色的菜单权限Id和操作权限Id
		//返回所有权限id
		return sysRoleDAO.getAllPermissionsIds(roleId);
	}
	@Override
	public boolean existsName(String name) {
		return sysRoleDAO.existsName(name)>0;
	}
	@Override
	public boolean existsName(String name, Integer roleId) {
		//修改用户时，检测用户名是否存在
		return sysRoleDAO.existsNameWhenEdit(name, roleId)>0;
	}

}

package com.wangcw.framework.system.service;

import com.wangcw.framework.base.pagination.PageBean;
import com.wangcw.framework.system.criteria.SysUserCriteria;
import com.wangcw.framework.system.entity.SysUser;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface SysUserService{

	public SysUser login(SysUser sysUser);

	public void add(SysUser sysuser);

	public void delete(String[] ids);

	public void delete(Integer id);

	public void update(SysUser sysuser);

	@Transactional(readOnly=true)
	public SysUser get(int id);

	@Transactional(readOnly=true)
	public void findByPage(PageBean pb, SysUserCriteria sysuser);

	public void changePwd(int id, String pwd);

	@Transactional(readOnly=true)
	public boolean existsName(String name);

	@Transactional(readOnly=true)
	public boolean existsName(String name, Integer userId);

	@Transactional(readOnly=true)
	public int findMaxPage(int rowsPerPage);

	@Transactional(readOnly=true)
	public String getPwd(int id);
}

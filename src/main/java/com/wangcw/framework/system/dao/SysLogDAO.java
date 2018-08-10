package com.wangcw.framework.system.dao;

import com.wangcw.framework.base.pagination.PageBean;
import com.wangcw.framework.system.entity.SysLog;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface SysLogDAO {
	public void save(SysLog sysLog);
	public List pagination(PageBean pb);
}

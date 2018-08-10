package com.wangcw.framework.system.service;

import com.wangcw.framework.base.pagination.PageBean;
import com.wangcw.framework.system.criteria.SysLogCriteria;
import com.wangcw.framework.system.entity.SysLog;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SysLogService {

	public void add(SysLog sysLog);

	@Transactional(readOnly=true)
	public void findByPage(PageBean pb, SysLogCriteria sysLogCriteria);
}

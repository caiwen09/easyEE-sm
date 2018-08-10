package com.wangcw.framework.system.service.impl;

import com.wangcw.framework.base.pagination.PageBean;
import com.wangcw.framework.base.service.BaseService;
import com.wangcw.framework.system.criteria.SysLogCriteria;
import com.wangcw.framework.system.dao.SysLogDAO;
import com.wangcw.framework.system.entity.SysLog;
import com.wangcw.framework.system.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sysLogService")
@SuppressWarnings("rawtypes")
public class SysLogServiceImpl extends BaseService implements SysLogService {
	@Resource
	SysLogDAO sysLogDAO;
	
	@Override
	public void add(SysLog sysLog) {
		sysLogDAO.save(sysLog);
	}
	@Override
	public void findByPage(PageBean pageBean, SysLogCriteria sysLogCriteria) {
		pageBean.setSelect("*");
		pageBean.setFrom("sys_log s");
		pageBean.setEasyCriteria(sysLogCriteria);
		// 按条件分页查询
		sysLogDAO.pagination(pageBean);
	}

}

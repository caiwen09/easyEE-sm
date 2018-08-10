package com.wangcw.framework.system.controller;

import com.wangcw.framework.base.controller.BaseController;
import com.wangcw.framework.base.pagination.PageBean;
import com.wangcw.framework.system.criteria.SysLogCriteria;
import com.wangcw.framework.system.service.SysLogService;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;
/**
  *@description   系统日志
  *@author        王财文
  *@version       1.0 2018-3-27
  */


@RestController
@RequestMapping("SysLog")
public class SysLogController extends BaseController {

	@Resource
	private SysLogService sysLogService;

	
	/**
	 * 转向显示页面
	 * @return
	 */
	@RequestMapping("page")
	public ModelAndView page(ModelAndView mv){
		mv.setViewName("main/sys/sysLog");
		return mv;
	}
	
	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public Map<Object, Object> list(SysLogCriteria sysLogCriteria) throws Exception {
		String sort = ServletRequestUtils.getStringParameter(request, "sort", "");
		String order = ServletRequestUtils.getStringParameter(request, "order", "");
		if (!isNotNullAndEmpty(sort) || sort.equals("logTime")) {
			sort = "log_Time";
		}
		if (!isNotNullAndEmpty(order)) {
			order = "desc";
		}
		PageBean pb = super.getPageBean(); // 获得分页对象
		pb.setSort(sort);
		pb.setSortOrder(order);
		sysLogService.findByPage(pb, sysLogCriteria);
		return super.setJsonPaginationMap(pb);
	}

}

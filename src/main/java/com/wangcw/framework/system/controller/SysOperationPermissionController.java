package com.wangcw.framework.system.controller;

import com.wangcw.framework.base.controller.BaseController;
import com.wangcw.framework.base.util.StatusCode;
import com.wangcw.framework.system.entity.SysOperationPermission;
import com.wangcw.framework.system.service.SysOperationPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  *@description   菜单操作控制层
  *@author        王财文
  *@version       1.0 2018-3-27
  */


@RestController
@RequestMapping("SysOperationPermission")
@SuppressWarnings({ "rawtypes" })
public class SysOperationPermissionController extends BaseController {
	public static Logger LOG = LoggerFactory.getLogger("system");

	@Resource
	private SysOperationPermissionService sysOperationPermissionService;
	


	/**
	 * 转向显示页面
	 * @return
	 */
	@RequestMapping("page")
	public ModelAndView page(ModelAndView mv){
		mv.setViewName("main/sys/sysOperationPermission");
		return mv;
	}
	
	@RequestMapping("list")
	public Object list(String menuId) {
		Object jsonRoot = new Object[] {};
		if (isNotNullAndEmpty(menuId)) {
			List list = sysOperationPermissionService.list(Integer.valueOf(menuId));
			jsonRoot=setJsonMap("rows", list, "sysMenuPermission.menuPermissionId", menuId);
		}
		return jsonRoot;
	}

	@RequestMapping("update")
	public Map<Object, Object> update(SysOperationPermission sysOperationPermission) {
		try {
			sysOperationPermissionService.update(sysOperationPermission);
			super.setMsgKey("msg.updateSuccess");
			super.reloadPermissions(); // 刷新权限
		} catch (Exception e) {
			LOG.error(getText("sys.OperationPermissionController.updateException"), e);
			e.printStackTrace();
			super.setMsgKey("msg.updateFail");
			super.setStatusCode(StatusCode.ERROR); //默认为OK
		}
		return setJsonMsgMap();
		
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("save")
	public Map<Object, Object> save(SysOperationPermission sysOperationPermission) {
		try {
			sysOperationPermissionService.add(sysOperationPermission);
			super.setMsgKey("msg.saveSuccess");
			super.reloadPermissions(); // 刷新权限
		} catch (Exception e) {
			LOG.error(getText("sys.OperationPermissionController.saveException"), e);
			e.printStackTrace();
			super.setMsgKey("msg.saveFail");
			super.setStatusCode(StatusCode.ERROR); //默认为OK
		}
//		System.out.println(sysOperationPermission.getOperationPermissionId());
		// 返回添加的数据主键信息
		Map rowData=new HashMap();
		rowData.put("operationPermissionId", sysOperationPermission.getOperationPermissionId());
		return setJsonMsgMap("rowData",rowData);
		
	}

	@RequestMapping("delete")
	public Map<Object, Object> delete(SysOperationPermission sysOperationPermission) {
		try {
			sysOperationPermissionService
					.delete(sysOperationPermissionService.get(sysOperationPermission.getOperationPermissionId()));
			super.setMsgKey("msg.deleteSuccess");
			super.reloadPermissions(); // 刷新权限
		} catch (Exception e) {
			LOG.error(getText("sys.OperationPermissionController.deleteException"), e);
			e.printStackTrace();
			super.setMsgKey("msg.deleteSuccess");
			super.setStatusCode(StatusCode.ERROR); //默认为OK
		}
		return setJsonMsgMap();
		
	}

}

package com.wangcw.framework.system.controller;

import cn.easyproject.easycommons.objectutils.EasyObjectExtract;
import com.wangcw.framework.base.controller.BaseController;
import com.wangcw.framework.base.pagination.PageBean;
import com.wangcw.framework.base.util.StatusCode;
import com.wangcw.framework.system.criteria.SysRoleCriteria;
import com.wangcw.framework.system.entity.SysMenuPermission;
import com.wangcw.framework.system.entity.SysOperationPermission;
import com.wangcw.framework.system.entity.SysRole;
import com.wangcw.framework.system.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  *@description   角色操作控制层
  *@author        王财文
  *@version       1.0 2018-3-27
  */


@RestController
@RequestMapping("SysRole")
public class SysRoleController extends BaseController {
	public static Logger LOG = LoggerFactory.getLogger("system");

	@Resource
	private SysRoleService sysRoleService;

	/**
	 * 转向显示页面
	 * @return
	 */
	@RequestMapping("page")
	public ModelAndView page(ModelAndView mv){
		mv.setViewName("main/sys/sysRole");
		return mv;
	}

	/**
	 * 获得所有角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public Map<Object, Object> list(SysRoleCriteria sysRoleCriteria) throws Exception {
		PageBean pb = super.getPageBean(); // 获得分页对

		sysRoleService.findByPage(pb, sysRoleCriteria);
		// 从分页集合中抽取需要输出的分页数据，不需要的不用输出，而且可以防止no session异常
		Map<String, String> fieldMap = new HashMap<String, String>();
		List<Map> list = EasyObjectExtract.extract(pb.getData(), fieldMap, "roleId", "name", "status", "remark");
		// 使用抽取的集合作为分页数据
		pb.setData(list);

		return super.setJsonPaginationMap(pb);
		
	}

	/**
	 * 获得所有角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("all")
	public Map<Object, Object> all() throws Exception {
		return super.setJsonMap("list", sysRoleService.list());
		
	}

	/**
	 * 获得所有权限的Id（菜单权限+操作权限），为了防止id重复，使用前缀标识
	 * 
	 * @return
	 */
	@RequestMapping("getAllPermissionsId")
	public Map<Object, Object> getAllPermissionsId(SysRole sysRole) {
		List<Map> all = sysRoleService.getAllPermissionsIds(sysRole.getRoleId());
		List allPermissions = new ArrayList();
		for (Map res : all) {
			allPermissions.add(res.get("TYPE") + "_" + res.get("ID"));
		}
//		System.out.println("###############################：：：" + allPermissions);
		return super.setJsonMap("list", allPermissions);
		
	}

	/**
	 * 添加角色
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public Map<Object, Object> save(int rows,String menus, String operations, SysRole sysRole) {
		super.setMsgKey("msg.saveFail");
		super.setStatusCode(StatusCode.ERROR); //默认为OK
		try {

			if (sysRoleService.existsName(sysRole.getName())) {
				super.setMsgKey("sys.RoleController.roleExists");
			} else {
				// 设置角色对应的菜单权限
				if (isNotNullAndEmpty(menus)) {
					String[] menuIds = menus.split("#");
					for (String menuId : menuIds) {
						SysMenuPermission menuPermission = new SysMenuPermission();
						menuPermission.setMenuPermissionId(Integer.valueOf(menuId.replace("menu_", "")));
						sysRole.getSysMenuPermissions().add(menuPermission);
					}
				}
				// 设置角色对应的操作权限
				if (isNotNullAndEmpty(operations)) {
					String[] operationIds = operations.split("#");
					for (String operationId : operationIds) {
						SysOperationPermission operationPermission = new SysOperationPermission();
						operationPermission
								.setOperationPermissionId(Integer.valueOf(operationId.replace("operation_", "")));
						sysRole.getSysOperationPermissions().add(operationPermission);
					}
				}
				sysRoleService.add(sysRole);
				super.setMsgKey("msg.saveSuccess");
				super.setStatusCode(StatusCode.OK); 
				super.reloadPermissions(); // 刷新权限
			}
		} catch (Exception e) {
			LOG.error(getText("sys.RoleController.saveException"), e);
			e.printStackTrace();
		}
		
		// 如果需要刷新，跳转到最后一页
		int maxPage = sysRoleService.findMaxPage(rows);
		return super.setJsonMsgMap("page", maxPage);
	}

	/**
	 * 修改角色
	 * 
	 * @return
	 */
	@RequestMapping("update")
	public Map<Object, Object> update(int rows,String menus, String operations, SysRole sysRole) {
		super.setMsgKey("msg.updateFail");
		super.setStatusCode(StatusCode.ERROR); //默认为OK
		try {

			if (sysRoleService.existsName(sysRole.getName(), sysRole.getRoleId())) {
				super.setMsgKey("sys.RoleController.roleExists");
			} else {
				SysRole sysRole2 = sysRoleService.get(sysRole.getRoleId());
				// 不修改用户角色关系
				sysRole.setSysUsers(sysRole2.getSysUsers());

				// 设置角色对应的菜单权限
				if (isNotNullAndEmpty(menus)) {
					String[] menuIds = menus.split("#");
					for (String menuId : menuIds) {
						SysMenuPermission menuPermission = new SysMenuPermission();
						menuPermission.setMenuPermissionId(Integer.valueOf(menuId.replace("menu_", "")));
						sysRole.getSysMenuPermissions().add(menuPermission);
					}
				}
				// 设置角色对应的操作权限
				if (isNotNullAndEmpty(operations)) {
					String[] operationIds = operations.split("#");
					for (String operationId : operationIds) {
						SysOperationPermission operationPermission = new SysOperationPermission();
						operationPermission
								.setOperationPermissionId(Integer.valueOf(operationId.replace("operation_", "")));
						sysRole.getSysOperationPermissions().add(operationPermission);
					}
				}
				sysRoleService.update(sysRole);
				super.setMsgKey("msg.updateSuccess");
				super.setStatusCode(StatusCode.OK); 

				// 跳转到最后一页
				super.reloadPermissions(); // 刷新权限
			}
		} catch (Exception e) {
			LOG.error(getText("sys.RoleController.updateException"), e);
			e.printStackTrace();
		}
		
		// 如果需要刷新，跳转到最后一页
		int maxPage = sysRoleService.findMaxPage(rows);
		return super.setJsonMsgMap("page", maxPage);
		
	}

	/**
	 * 删除角色
	 * 
	 * @return
	 */
	@RequestMapping("delete")
	public Map<Object, Object> delete(SysRole sysRole) {
		try {
			sysRoleService.delete(sysRole.getRoleId());
			super.reloadPermissions(); // 刷新权限
		} catch (Exception e) {
			e.printStackTrace();
			super.setStatusCode(StatusCode.ERROR); //默认为OK
		}
		return super.setJsonMsgMap();
	}
}

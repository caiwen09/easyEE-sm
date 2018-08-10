/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50132
Source Host           : 127.0.0.1:3306
Source Database       : easyee

Target Server Type    : MYSQL
Target Server Version : 50132
File Encoding         : 65001

Date: 2018-04-04 14:23:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for module_dept
-- ----------------------------
DROP TABLE IF EXISTS `module_dept`;
CREATE TABLE `module_dept` (
  `deptno` int(11) NOT NULL AUTO_INCREMENT,
  `dname` varchar(30) NOT NULL,
  `loc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_dept
-- ----------------------------
INSERT INTO `module_dept` VALUES ('1', '开发部', '深圳');
INSERT INTO `module_dept` VALUES ('2', '测试部', '北京');
INSERT INTO `module_dept` VALUES ('3', '研究院', '上海');

-- ----------------------------
-- Table structure for module_emp
-- ----------------------------
DROP TABLE IF EXISTS `module_emp`;
CREATE TABLE `module_emp` (
  `empno` int(11) NOT NULL AUTO_INCREMENT,
  `ename` varchar(30) DEFAULT NULL,
  `job` varchar(30) DEFAULT NULL,
  `deptno` int(11) DEFAULT NULL,
  PRIMARY KEY (`empno`),
  KEY `fk_emp_deptno` (`deptno`) USING BTREE,
  CONSTRAINT `module_emp_ibfk_1` FOREIGN KEY (`deptno`) REFERENCES `module_dept` (`deptno`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_emp
-- ----------------------------
INSERT INTO `module_emp` VALUES ('1', '张三', '开发工程师', '1');
INSERT INTO `module_emp` VALUES ('2', '李四', '开发工程师', '1');
INSERT INTO `module_emp` VALUES ('3', '王五', '管理员', '3');
INSERT INTO `module_emp` VALUES ('4', '孙六', '测试主管', '1');
INSERT INTO `module_emp` VALUES ('5', '钱七', '测试工程师', '2');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACTION` text,
  `PARAMETERS` text,
  `RES` text,
  `ACCOUNT` varchar(200) DEFAULT NULL,
  `IP` varchar(200) DEFAULT NULL,
  `LOG_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1846 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1800', '/toMain [com.wangcw.framework.system.controller.ForwardController#toMain]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:18:35');
INSERT INTO `sys_log` VALUES ('1801', '/toLogin [com.wangcw.framework.system.controller.ForwardController#toLogin]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:18:35');
INSERT INTO `sys_log` VALUES ('1802', '/toMain [com.wangcw.framework.system.controller.ForwardController#toMain]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:18:46');
INSERT INTO `sys_log` VALUES ('1803', '/toLogin [com.wangcw.framework.system.controller.ForwardController#toLogin]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:18:47');
INSERT INTO `sys_log` VALUES ('1804', '/favicon.ico [org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:18:47');
INSERT INTO `sys_log` VALUES ('1805', '/toMain [com.wangcw.framework.system.controller.ForwardController#toMain]', '', 'status Code:[200]', 'demo[戴谋]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:19:19');
INSERT INTO `sys_log` VALUES ('1806', '/toLogin [com.wangcw.framework.system.controller.ForwardController#toLogin]', '', 'status Code:[200]', 'demo[戴谋]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:19:20');
INSERT INTO `sys_log` VALUES ('1807', '/toMain [com.wangcw.framework.system.controller.ForwardController#toMain]', '', 'status Code:[200]', 'user[蒲关]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:19:47');
INSERT INTO `sys_log` VALUES ('1808', '/toLogin [com.wangcw.framework.system.controller.ForwardController#toLogin]', '', 'status Code:[200]', 'user[蒲关]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:19:47');
INSERT INTO `sys_log` VALUES ('1809', '/toMain [com.wangcw.framework.system.controller.ForwardController#toMain]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:20:39');
INSERT INTO `sys_log` VALUES ('1810', '/toLogin [com.wangcw.framework.system.controller.ForwardController#toLogin]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:20:40');
INSERT INTO `sys_log` VALUES ('1811', '/toMain [com.wangcw.framework.system.controller.ForwardController#toMain]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:29:17');
INSERT INTO `sys_log` VALUES ('1812', '/static/easyee/json/easyui.theme.combobox.json [org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:29:17');
INSERT INTO `sys_log` VALUES ('1813', '/toLogin [com.wangcw.framework.system.controller.ForwardController#toLogin]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:29:18');
INSERT INTO `sys_log` VALUES ('1814', '/SysUser/page [com.wangcw.framework.system.controller.SysUserController#page]', '_: [1522740557646]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:29:22');
INSERT INTO `sys_log` VALUES ('1815', '/SysUser/list [com.wangcw.framework.system.controller.SysUserController#list]', 'page: [1]; rows: [10]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:29:23');
INSERT INTO `sys_log` VALUES ('1816', '/SysRole/all [com.wangcw.framework.system.controller.SysRoleController#all]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:29:52');
INSERT INTO `sys_log` VALUES ('1817', '/SysRole/page [com.wangcw.framework.system.controller.SysRoleController#page]', '_: [1522740557647]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:29:59');
INSERT INTO `sys_log` VALUES ('1818', '/SysRole/list [com.wangcw.framework.system.controller.SysRoleController#list]', 'page: [1]; rows: [10]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:30:03');
INSERT INTO `sys_log` VALUES ('1819', '/SysMenuPermission/listAllForSysRole [com.wangcw.framework.system.controller.SysMenuPermissionController#listAllForSysRole]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:30:08');
INSERT INTO `sys_log` VALUES ('1820', '/SysUser/page [com.wangcw.framework.system.controller.SysUserController#page]', '_: [1522740557648]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:30:16');
INSERT INTO `sys_log` VALUES ('1821', '/SysUser/list [com.wangcw.framework.system.controller.SysUserController#list]', 'page: [1]; rows: [10]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:30:18');
INSERT INTO `sys_log` VALUES ('1822', '/SysRole/all [com.wangcw.framework.system.controller.SysRoleController#all]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:30:22');
INSERT INTO `sys_log` VALUES ('1823', '/SysUser/save [com.wangcw.framework.system.controller.SysUserController#save]', 'userId: []; name: [admin111]; realName: [admin]; password: can\'t show; confirmPwd: [123456]; status: [0]; rows: [10]; xRequestedWith: [XMLHttpRequest]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:30:41');
INSERT INTO `sys_log` VALUES ('1824', '/SysUser/list [com.wangcw.framework.system.controller.SysUserController#list]', 'page: [1]; rows: [10]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:30:42');
INSERT INTO `sys_log` VALUES ('1825', '/SysLog/page [com.wangcw.framework.system.controller.SysLogController#page]', '_: [1522740557649]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:30:50');
INSERT INTO `sys_log` VALUES ('1826', '/SysLog/list [com.wangcw.framework.system.controller.SysLogController#list]', 'page: [1]; rows: [10]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:30:53');
INSERT INTO `sys_log` VALUES ('1827', '/SysLog/list [com.wangcw.framework.system.controller.SysLogController#list]', 'page: [1]; rows: [10]; sort: [parameters]; order: [asc]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:31:05');
INSERT INTO `sys_log` VALUES ('1828', '/SysLog/list [com.wangcw.framework.system.controller.SysLogController#list]', 'page: [1]; rows: [10]; sort: [logTime]; order: [asc]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:31:24');
INSERT INTO `sys_log` VALUES ('1829', '/SysLog/list [com.wangcw.framework.system.controller.SysLogController#list]', 'page: [1]; rows: [10]; sort: [logTime]; order: [desc]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:31:36');
INSERT INTO `sys_log` VALUES ('1830', '/SysLog/list [com.wangcw.framework.system.controller.SysLogController#list]', 'page: [2]; rows: [10]; sort: [logTime]; order: [desc]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:31:55');
INSERT INTO `sys_log` VALUES ('1831', '/SysLog/list [com.wangcw.framework.system.controller.SysLogController#list]', 'page: [3]; rows: [10]; sort: [logTime]; order: [desc]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:31:56');
INSERT INTO `sys_log` VALUES ('1832', '/SysLog/list [com.wangcw.framework.system.controller.SysLogController#list]', 'page: [4]; rows: [10]; sort: [logTime]; order: [desc]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:31:57');
INSERT INTO `sys_log` VALUES ('1833', '/toMain [com.wangcw.framework.system.controller.ForwardController#toMain]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:32:00');
INSERT INTO `sys_log` VALUES ('1834', '/static/easyee/json/easyui.theme.combobox.json [org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:32:01');
INSERT INTO `sys_log` VALUES ('1835', '/toLogin [com.wangcw.framework.system.controller.ForwardController#toLogin]', '', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:32:01');
INSERT INTO `sys_log` VALUES ('1836', '/SysLog/page [com.wangcw.framework.system.controller.SysLogController#page]', '_: [1522740721013]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:32:02');
INSERT INTO `sys_log` VALUES ('1837', '/SysLog/list [com.wangcw.framework.system.controller.SysLogController#list]', 'page: [1]; rows: [10]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:32:03');
INSERT INTO `sys_log` VALUES ('1838', '/SysLog/list [com.wangcw.framework.system.controller.SysLogController#list]', 'page: [1]; rows: [10]; sort: [logTime]; order: [asc]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:32:04');
INSERT INTO `sys_log` VALUES ('1839', '/SysLog/list [com.wangcw.framework.system.controller.SysLogController#list]', 'page: [1]; rows: [10]; sort: [logTime]; order: [desc]; ', 'status Code:[200]', 'admin[WangCaiwen]', '11.13.233.239:LAPTOP-OBMETQJ6', '2018-04-03 15:32:04');
INSERT INTO `sys_log` VALUES ('1840', '/toMain [com.wangcw.framework.system.controller.ForwardController#toMain]', '', 'status Code:[200]', 'admin[WangCaiwen]', '192.168.1.108:LAPTOP-OBMETQJ6', '2018-04-03 22:04:47');
INSERT INTO `sys_log` VALUES ('1841', '/toLogin [com.wangcw.framework.system.controller.ForwardController#toLogin]', '', 'status Code:[200]', 'admin[WangCaiwen]', '192.168.1.108:LAPTOP-OBMETQJ6', '2018-04-03 22:04:48');
INSERT INTO `sys_log` VALUES ('1842', '/toReports [com.wangcw.framework.system.controller.ForwardController#toReports]', '_: [1522764287695]; ', 'status Code:[200]', 'admin[WangCaiwen]', '192.168.1.108:LAPTOP-OBMETQJ6', '2018-04-03 22:26:27');
INSERT INTO `sys_log` VALUES ('1843', '/toReports [com.wangcw.framework.system.controller.ForwardController#toReports]', '_: [1522764287696]; ', 'status Code:[200]', 'admin[WangCaiwen]', '192.168.1.108:LAPTOP-OBMETQJ6', '2018-04-03 22:26:37');
INSERT INTO `sys_log` VALUES ('1844', '/toMain [com.wangcw.framework.system.controller.ForwardController#toMain]', '', 'status Code:[200]', 'admin[WangCaiwen]', '192.168.1.108:LAPTOP-OBMETQJ6', '2018-04-03 22:42:58');
INSERT INTO `sys_log` VALUES ('1845', '/toLogin [com.wangcw.framework.system.controller.ForwardController#toLogin]', '', 'status Code:[200]', 'admin[WangCaiwen]', '192.168.1.108:LAPTOP-OBMETQJ6', '2018-04-03 22:43:00');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_PERMISSION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `ACTION` varchar(500) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `SORT_NUM` int(11) DEFAULT NULL,
  `ICON` varchar(50) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`MENU_PERMISSION_ID`),
  KEY `FK_FK_SYS_MENU_RIGHTS_ID` (`PARENT_ID`) USING BTREE,
  CONSTRAINT `sys_menu_ibfk_1` FOREIGN KEY (`PARENT_ID`) REFERENCES `sys_menu` (`MENU_PERMISSION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', 'WangCW_Framework', '', null, '0', 'icon-home2', '');
INSERT INTO `sys_menu` VALUES ('2', '系统管理', null, '1', '0', 'icon-application_view_tile', '系统维护管理，系统管理员拥有');
INSERT INTO `sys_menu` VALUES ('3', '用户管理', 'SysUser/page', '2', '0', 'icon-user', '');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', 'SysRole/page', '2', '1', 'icon-grade', null);
INSERT INTO `sys_menu` VALUES ('5', '菜单权限管理', 'SysMenuPermission/page', '2', '2', 'icon-menu', '');
INSERT INTO `sys_menu` VALUES ('6', '操作权限管理', 'SysOperationPermission/page', '2', '3', 'icon-rights', null);
INSERT INTO `sys_menu` VALUES ('10', '报表管理', null, '1', '3', 'icon-chart_bar', '经理查看');
INSERT INTO `sys_menu` VALUES ('11', '统计报表', 'toReports', '10', '0', 'icon-chart_curve', '');
INSERT INTO `sys_menu` VALUES ('12', '系统日志', 'SysLog/page', '2', '5', 'icon-book', '系统日志查看');

-- ----------------------------
-- Table structure for sys_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation`;
CREATE TABLE `sys_operation` (
  `OPERATION_PERMISSION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MENU_PERMISSION_ID` int(11) DEFAULT NULL,
  `NAME` varchar(50) NOT NULL,
  `ACTION` varchar(500) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`OPERATION_PERMISSION_ID`),
  KEY `FK_FK_SYS_OPERATION_MENU_ID` (`MENU_PERMISSION_ID`) USING BTREE,
  CONSTRAINT `sys_operation_ibfk_1` FOREIGN KEY (`MENU_PERMISSION_ID`) REFERENCES `sys_menu` (`MENU_PERMISSION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_operation
-- ----------------------------
INSERT INTO `sys_operation` VALUES ('1', '5', '查询菜单列表', 'SysMenuPermission/list', '');
INSERT INTO `sys_operation` VALUES ('2', '5', '修改菜单', 'SysMenuPermission/update', '');
INSERT INTO `sys_operation` VALUES ('3', '5', '删除菜单', 'SysMenuPermission/delete', '');
INSERT INTO `sys_operation` VALUES ('4', '5', '移动菜单次序', 'SysMenuPermission/move', '');
INSERT INTO `sys_operation` VALUES ('5', '5', '添加菜单', 'SysMenuPermission/save', '');
INSERT INTO `sys_operation` VALUES ('6', '6', '菜单列表查询', 'SysMenuPermission/list', '');
INSERT INTO `sys_operation` VALUES ('7', '6', '查询菜单对应的操作权限列表', 'SysOperationPermission/list', '');
INSERT INTO `sys_operation` VALUES ('8', '6', '新增操作权限', 'SysOperationPermission/save', '');
INSERT INTO `sys_operation` VALUES ('9', '6', '修改操作权限', 'SysOperationPermission/update', '');
INSERT INTO `sys_operation` VALUES ('10', '6', '删除操作权限', 'SysOperationPermission/delete', '');
INSERT INTO `sys_operation` VALUES ('11', '4', '查询所有角色', 'SysRole/list', '');
INSERT INTO `sys_operation` VALUES ('12', '4', '添加角色', 'SysRole/save,SysMenuPermission/listAllForSysRole', '');
INSERT INTO `sys_operation` VALUES ('13', '4', '修改角色', 'SysRole/update#SysRole/getAllPermissionsId,SysMenuPermission/listAllForSysRole', '修改角色需要获得用户的相关角色权限');
INSERT INTO `sys_operation` VALUES ('14', '4', '删除角色', 'SysRole/delete', '');
INSERT INTO `sys_operation` VALUES ('15', '3', '查询用户列表', 'SysUser/list', '');
INSERT INTO `sys_operation` VALUES ('16', '3', '添加用户', 'SysUser/save,SysRole/all', '');
INSERT INTO `sys_operation` VALUES ('17', '3', '修改用户', 'SysUser/update,SysRole/all', '');
INSERT INTO `sys_operation` VALUES ('18', '3', '删除用户', 'SysUser/delete', '');
INSERT INTO `sys_operation` VALUES ('19', '3', '显示添加用户按钮', 'SysUserAddBtn', '显示权限');
INSERT INTO `sys_operation` VALUES ('20', '3', '显示删除用户按钮', 'SysUserDelBtn', '显示权限');
INSERT INTO `sys_operation` VALUES ('21', '3', '显示修改用户按钮', 'SysUserUpdateBtn', '显示权限');
INSERT INTO `sys_operation` VALUES ('22', '3', '显示真实姓名列信息', 'showRealNameColumn', '显示权限');
INSERT INTO `sys_operation` VALUES ('32', '12', '查询日志', 'SysLog/list', '');
INSERT INTO `sys_operation` VALUES ('33', '1', '后台管理中心权限', 'toMain', '登录到后台必须授予');
INSERT INTO `sys_operation` VALUES ('34', '3', '查询角色列表', 'SysRole/all', '');
INSERT INTO `sys_operation` VALUES ('35', '4', '查询菜单权限和操作权限', 'SysRole/getAllPermissionsId,sysMenuPermission/listAll,sysMenuPermission/listAllForSysRole', '');
INSERT INTO `sys_operation` VALUES ('36', '1', '修改个人密码', 'SysUser/changePwd', '可以执行Change Password功能');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `STATUS` int(11) DEFAULT '0',
  `REMARK` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '0', '拥有所有管理权限');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `ROLE_ID` int(11) NOT NULL,
  `MENU_PERMISSION_ID` int(11) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`MENU_PERMISSION_ID`),
  KEY `FK_FK_SYS_ROLE_RIGHTS_RIGHTS_ID` (`MENU_PERMISSION_ID`) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`),
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`MENU_PERMISSION_ID`) REFERENCES `sys_menu` (`MENU_PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '10');
INSERT INTO `sys_role_menu` VALUES ('1', '11');
INSERT INTO `sys_role_menu` VALUES ('1', '12');

-- ----------------------------
-- Table structure for sys_role_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_operation`;
CREATE TABLE `sys_role_operation` (
  `ROLE_ID` int(11) NOT NULL,
  `OPERATION_PERMISSION_ID` int(11) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`OPERATION_PERMISSION_ID`),
  KEY `FK_FK_SYS_ROLE_OPERATION_RIGHTS_OPERATION_RIGHTS_ID` (`OPERATION_PERMISSION_ID`) USING BTREE,
  CONSTRAINT `sys_role_operation_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`),
  CONSTRAINT `sys_role_operation_ibfk_2` FOREIGN KEY (`OPERATION_PERMISSION_ID`) REFERENCES `sys_operation` (`OPERATION_PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_operation
-- ----------------------------
INSERT INTO `sys_role_operation` VALUES ('1', '1');
INSERT INTO `sys_role_operation` VALUES ('1', '2');
INSERT INTO `sys_role_operation` VALUES ('1', '3');
INSERT INTO `sys_role_operation` VALUES ('1', '4');
INSERT INTO `sys_role_operation` VALUES ('1', '5');
INSERT INTO `sys_role_operation` VALUES ('1', '6');
INSERT INTO `sys_role_operation` VALUES ('1', '7');
INSERT INTO `sys_role_operation` VALUES ('1', '8');
INSERT INTO `sys_role_operation` VALUES ('1', '9');
INSERT INTO `sys_role_operation` VALUES ('1', '10');
INSERT INTO `sys_role_operation` VALUES ('1', '11');
INSERT INTO `sys_role_operation` VALUES ('1', '12');
INSERT INTO `sys_role_operation` VALUES ('1', '13');
INSERT INTO `sys_role_operation` VALUES ('1', '14');
INSERT INTO `sys_role_operation` VALUES ('1', '15');
INSERT INTO `sys_role_operation` VALUES ('1', '16');
INSERT INTO `sys_role_operation` VALUES ('1', '17');
INSERT INTO `sys_role_operation` VALUES ('1', '18');
INSERT INTO `sys_role_operation` VALUES ('1', '19');
INSERT INTO `sys_role_operation` VALUES ('1', '20');
INSERT INTO `sys_role_operation` VALUES ('1', '21');
INSERT INTO `sys_role_operation` VALUES ('1', '22');
INSERT INTO `sys_role_operation` VALUES ('1', '32');
INSERT INTO `sys_role_operation` VALUES ('1', '33');
INSERT INTO `sys_role_operation` VALUES ('1', '34');
INSERT INTO `sys_role_operation` VALUES ('1', '35');
INSERT INTO `sys_role_operation` VALUES ('1', '36');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `STATUS` int(11) DEFAULT '0',
  `REAL_NAME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `UQ_SYS_USER_NAME` (`NAME`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'a66abb5684c45962d887564f08346e8d', '0', 'WangCaiwen');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `FK_FK_SYS_USER_ROLE_ROLE_ID` (`ROLE_ID`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');

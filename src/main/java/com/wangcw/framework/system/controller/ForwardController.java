package com.wangcw.framework.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
  *@description   页面跳转
  *@author        王财文
  *@version       1.0 2018-3-27
  */


@Controller
public class ForwardController {

	@RequestMapping("toLogin")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("toMain")
	public String toMain() {
		return "main/main";
	}

	@RequestMapping("toReports")
	public String toReports() {
		return "main/reports/doChart";
	}

	

}

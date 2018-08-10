package com.wangcw.framework.system.controller;

import com.wangcw.framework.base.controller.BaseController;
import com.wangcw.framework.base.util.StatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
  *@description   验证码检测
  *@author        王财文
  *@version       1.0 2018-3-27
  */


@RestController
public class CaptchaController extends BaseController {

	/**
	 * 验证码检测
	 * 
	 * @return
	 */
	@RequestMapping("checkCaptcha")
	public Map<Object, Object> checkCaptcha(String captcha) {
		HttpSession session = request.getSession();
		if (!(session.getAttribute("rand") != null && session.getAttribute("rand").toString().equalsIgnoreCase(captcha))) {
			super.setStatusCode(StatusCode.ERROR); //默认为OK
			super.setMsgKey("sys.LoginController.captchatError");
		}
		return super.setJsonMsgMap();
	}

}

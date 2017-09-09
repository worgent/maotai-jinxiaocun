package com.sohu.action;

import com.sohu.service.login.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页action
 */

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private final static Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String login(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		for (int i = 0; i < 10; i++) {
			log.info("login ...");
		}
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		return "login";
	}


}
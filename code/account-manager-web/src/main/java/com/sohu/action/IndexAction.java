package com.sohu.action;

import com.sohu.mrd.domain.beans.TLogin;
import com.sohu.mrd.domain.util.WebMvcHelper;
import com.sohu.mrd.domain.util.common.StringEscapeUtils;
import com.sohu.mrd.domain.util.common.StringUtils;
import com.sohu.mrd.domain.util.springmvc.context.FormsAuthenticationTicket;
import com.sohu.service.jinxiaocun.EmployeeService;
import com.sohu.service.login.LoginService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页action
 */

@Controller
@RequestMapping(value = "/")
public class IndexAction {

	private final static Logger log = Logger.getLogger(IndexAction.class);

	private LoginService loginService;
	private EmployeeService employeeService;

	// @Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView index(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		for (int i = 0; i < 10; i++) {
			log.info("index ....");
		}
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// return
		// return "index";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

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

	@RequestMapping(value = "/doLogin", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String doLogin(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String emName = request.getParameter("emName");
		String emPassword = request.getParameter("emPassword");
		String returnUrl = request.getParameter("returnUrl");
		int message = 0;
		if (validateLoginParams(emName, emPassword) == false) {
			message = 1;
			modelMap.put("login_error", "用户名或密码不能为空，请重新登陆");
			modelMap.put("message", message);
			return "login_error";
		}
		// int count = 1;// todo
		int count = loginService.queryTLoginTotalRows(emName, emPassword);
		log.info("username is " + emName + " pwd is " + emPassword
				+ " returnUrl is " + returnUrl);

		boolean resultOk = count > 0 ? true : false;
		// boolean resultOk=true;//
		if (resultOk == false) {
			message = 1;
			modelMap.put("login_error", "用户名或密码错误，请重新登陆");
			modelMap.put("message", message);
			return "login";
		}

		TLogin tLogin = loginService.queryTLoginByUname(emName);
		WebMvcHelper.setContext(WebMvcHelper.SESSION_EMPLOYEE,
				employeeService.get(tLogin.getEmployeeId()));
		FormsAuthenticationTicket.setTicketBefore(emName, emPassword);
		loginService.writeCookieAndLoginContext(response);
		response.sendRedirect(URLDecoder.decode(returnUrl, "UTF-8"));

		return null;
	}

	@RequestMapping(value = "/doRegister", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String doRegister(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String emName = request.getParameter("emName");
		String emPassword = request.getParameter("emPassword");
		String remPassword = request.getParameter("remPassword");
		int model = 2;
		if (validateRegisterParams(emName, emPassword, remPassword) == false) {
			modelMap.put("register_error", "用户名或密码不能为空，请重新登陆");
			modelMap.put("model", model);
			return "register";
		}
		try {
			loginService.insertTLogin(emName, emPassword);
			return "register";
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "doRegister";
	}

	/**
	 * 退出登录
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String logout(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		loginService.deleteCookies(request, response);
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		return "logout";
	}

	private boolean validateLoginParams(String emName, String emPassword) {

		if (StringUtils.isBlank(emName) || StringUtils.isBlank(emPassword)) {
			return false;
		}
		emName = StringEscapeUtils.escapeHtml(emName).trim();
		emPassword = StringEscapeUtils.escapeHtml(emPassword).trim();
		return true;
	}

	private boolean validateRegisterParams(String emName, String emPassword,
			String remPassword) {
		if (StringUtils.isBlank(emName) || StringUtils.isBlank(emPassword)
				|| StringUtils.isBlank(remPassword)
				|| !emPassword.equals(remPassword)) {
			return false;
		}
		emName = StringEscapeUtils.escapeHtml(emName).trim();
		emPassword = StringEscapeUtils.escapeHtml(emPassword).trim();
		remPassword = StringEscapeUtils.escapeHtml(remPassword).trim();
		return true;
	}

}
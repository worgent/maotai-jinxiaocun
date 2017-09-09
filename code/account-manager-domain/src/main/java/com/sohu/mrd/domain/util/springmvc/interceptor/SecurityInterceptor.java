package com.sohu.mrd.domain.util.springmvc.interceptor;

import com.sohu.mrd.domain.util.springmvc.context.LoginContext;
import com.sohu.mrd.domain.util.url.JdUrl;
import com.sohu.mrd.domain.util.url.JdUrlUtils;
import com.sohu.mrd.domain.util.web.JdSecurity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;

/**
 * 资源保护拦截器。 User: yonggangli
 */
public class SecurityInterceptor extends SoHuInterceptor {
	private final static Log log = LogFactory.getLog(SecurityInterceptor.class);
	// 下面这些主要是通过spring自动注入
	protected JdSecurity jdSecurity;
	protected JdUrlUtils jdUrlUtils;
	protected String homeModule = "homeModule";
	protected String loginUrl = "loginUrl";

	/**
	 * 取出登录的信息
	 *
	 * @return
	 */
	protected LoginContext getLoginContext() {
		return LoginContext.getLoginContext();
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String path = request.getServletPath();
		if (jdSecurity != null) {
			if (jdSecurity.isProtected(path)) {// 如果资源受保护
				// check 有没有登录
				LoginContext context = getLoginContext();
				if (context == null || !context.isLogin()) {// 没登录
					if (jdUrlUtils != null) {
						setLoginUrl(request);
						modelAndView.setViewName("redirect:/login.html");
					} else {
						log.error("jdUrlUtils is null");
						modelAndView.setViewName("redirect:/login.html");
					}
				}
			}
		} else {
			log.error("jdSecurity is null");
			modelAndView.setViewName("redirect:/login.html");
		}

	}

	protected String getCurrentUrl(HttpServletRequest request) {
		JdUrl venderUrl = jdUrlUtils.getJdUrl(homeModule);
		venderUrl.getTarget(request.getRequestURI());
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = (String) parameterNames.nextElement();
			venderUrl.addQueryData(key, request.getParameterValues(key));
		}
		String resultUrl = venderUrl.toString();
		return resultUrl;
	}

	protected void setLoginUrl(HttpServletRequest request) {
		JdUrl loginUrl = jdUrlUtils.getJdUrl(this.loginUrl);
		String currentUrl = getCurrentUrl(request);
		loginUrl.addQueryData("ReturnUrl", currentUrl);

		// ValueStack valueStack = actionContext.getValueStack();

		ServletContext servletContext = request.getSession()
				.getServletContext();
		servletContext
				.setAttribute("returnUrl", loginUrl.encodeUrl(currentUrl));
		servletContext.setAttribute("loginUrl", loginUrl.toString());
	}

	public void setJdSecurity(JdSecurity jdSecurity) {
		this.jdSecurity = jdSecurity;
	}

	public void setJdUrlUtils(JdUrlUtils jdUrlUtils) {
		this.jdUrlUtils = jdUrlUtils;
	}

	public void setHomeModule(String homeModule) {
		this.homeModule = homeModule;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}

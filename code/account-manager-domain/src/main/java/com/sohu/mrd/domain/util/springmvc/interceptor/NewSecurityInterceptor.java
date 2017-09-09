package com.sohu.mrd.domain.util.springmvc.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.sohu.mrd.domain.util.springmvc.context.FormsAuthenticationTicket;
import com.sohu.mrd.domain.util.springmvc.context.LoginContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * vender端登录 User: chenghaixing Date: 2010-6-1 Time: 9:25:37
 */
public class NewSecurityInterceptor extends SecurityInterceptor {
	private final static Log log = LogFactory
			.getLog(NewSecurityInterceptor.class);

	protected String loginUrl = "loginUrl";

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		ServletContext servletContext = request.getSession()
				.getServletContext();

		String path = request.getServletPath();
		if (jdSecurity != null) {
			if (jdSecurity.isProtected(path)) {// 如果资源受保护
				FormsAuthenticationTicket ticket = (FormsAuthenticationTicket) servletContext
						.getAttribute(getDotnetTicketKey());
				if (ticket == null) { // 先check passport的cookie有没有
					log.debug("actioncontext hasn't a ticked!");
					setLoginUrl(request);
					modelAndView.setViewName("redirect:/login");
				}
			}
		} else {
			log.debug("jdSecurity is null!");
		}
	}

	/**
	 * 未登录passport跳转的url
	 * 
	 * @return
	 */

	protected String getNoLoginUrl() {
		return "login";
	}

	/**
	 * 把dotnet ticket放入actioncontext的key
	 *
	 * @return key
	 */
	protected String getDotnetTicketKey() {
		return LoginContext.HTTP_DOTNET_LOGIN_TICKET_CONTEXT;
	}

}

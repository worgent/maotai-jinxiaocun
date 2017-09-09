package com.sohu.mrd.domain.util.springmvc.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.sohu.mrd.domain.util.springmvc.context.LoginContext;
import com.sohu.mrd.domain.util.springmvc.cookie.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录有关的拦截器。 <br/>
 * 居然是单实例 <br/>
 * 该拦截器覆盖了SessionContextInterceptor的功能，二者取其一 <br/>
 * User: chenghaixing <br/>
 * Date: 2010-5-14 <br/>
 * Time: 9:50:10 <br/>
 * 
 * @deprecated
 */
public class LoginContextInterceptor extends SoHuInterceptor {

	private final static Log log = LogFactory
			.getLog(LoginContextInterceptor.class);
	protected CookieUtils cookieUtils;
	protected String loginCookieKey = "_lc_";
	/**
	 * 判断session有效时间，单位：秒 1800 为 30 * 60 。30分钟
	 */
	protected int sessionTimeout = 1800;

	/**
	 * 从cookie中读入登录信息。并写入到logincontext中。 只有登录成功后，才写入
	 *
	 * @param invocation
	 * @return
	 * @throws Exception
	 */

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		try {
			updateLogin(request, response);
		} catch (Exception e) {
			log.warn("updatelogin error!", e);
		}
	}

	protected void updateLogin(HttpServletRequest request,
			HttpServletResponse response) {

		// 判断sessiion是否超时
		boolean loginCookieValid = false;
		String session = cookieUtils.getCookieValue(request,
				LAST_ACCESS_TIME_COOKIE_NAME);// cookie有的效期
		if (StringUtils.isNotEmpty(session)) {
			try {
				long lastTime = Long.parseLong(session);
				long currTime = System.currentTimeMillis();
				if (currTime - lastTime < sessionTimeout * 1000) {// 如果没有超时
					loginCookieValid = true;
				}
			} catch (Exception e) {
				log.error("login intercept error", e);
			}
		} else {
			log.debug("session cookie hasn't exist");
		}
		if (loginCookieValid) {
			// 判断是否配置了cookie的cookie名称
			if (loginCookieKey != null) {
				try {
					String value = cookieUtils.getCookieValue(request,
							loginCookieKey);
					LoginContext context = getLoginContext(value);
					LoginContext.setLoginContext(context);
				} catch (Exception e) {
					log.error("login intercept error", e);
				}
			}
		} else {
			log.debug("session cookie is valid!");
			// 超时后，要清空
			cookieUtils.invalidate(request, response);
		}

		// 写最后一次访问的cookie
		cookieUtils.setCookie(response, LAST_ACCESS_TIME_COOKIE_NAME,
				Long.toString(System.currentTimeMillis()));
	}

	/**
	 * 从组合cookie中得到login context对象，可以被覆盖！
	 * 
	 * @param cookieValue
	 * @return
	 */
	protected LoginContext getLoginContext(String cookieValue) {
		return LoginContext.parse(cookieValue);
	}

	public void setCookieUtils(CookieUtils cookieUtils) {
		this.cookieUtils = cookieUtils;
	}

	/**
	 * 从struts2的注入
	 *
	 * @param loginCookieKey
	 */
	public void setLoginCookieKey(String loginCookieKey) {
		this.loginCookieKey = loginCookieKey;
	}

	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

}

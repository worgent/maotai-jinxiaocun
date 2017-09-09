package com.sohu.mrd.domain.util.struts.interceptor;

import com.sohu.mrd.domain.util.struts.context.FormsAuthenticationTicket;
import com.sohu.mrd.domain.util.struts.context.LoginContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 提供安全的session cookie策略 <br/>
 * 注意：如果直接升级到现有cookie策略，会导致以前session失效 <br/>
 * * 以前的安全问题主要是由于cookie没有写入过期信息。而是依靠另外过期的cookie来实现。存在漏洞 <br/>
 * Date: 10-12-15      <br/>
 * Time: 下午8:24      <br/>
 */
public class NewLoginContextInterceptor extends LoginContextInterceptor {
    private final static Log log = LogFactory.getLog(NewLoginContextInterceptor.class);
    /**
     * 写入cookie的时机
     */
    protected int rate = 2;

    protected void updateLogin(HttpServletRequest request, HttpServletResponse response) {
        //判断是否配置了cookie的cookie名称
        if (loginCookieKey != null) {
            try {
                String value = cookieUtils.getCookieValue(request, loginCookieKey);
                if (StringUtils.isNotBlank(value)) {//能取到值
                    LoginContext context = getLoginContext(value);
                    if (context != null) {//又能解出来
                        FormsAuthenticationTicket.setTicketBefore(context.getPin(), null);
                        long current = System.currentTimeMillis();
                        long created = context.getCreated();
                        long expires = context.getExpires();
                        long timeout = expires == 0 ? sessionTimeout * 1000 : expires - created;//如果没有设置过期时间，则使用默认的
                        if (current - created < timeout) { //如果没有过期
                            LoginContext.setLoginContext(context);
                            if ((current - created) * rate > timeout) {//如果剩下的时间只有2/3，就需要重新派发cookie
                                log.debug("session cookie[" + loginCookieKey + "] rewrite!");
                                //写最后一次访问的cookie
                                context.setCreated(current);
                                if (expires != 0) {
                                    context.setTimeout(timeout);
                                }
                                cookieUtils.setCookie(response, loginCookieKey, context.toCookieValue());
                            }
//                        } else if (expires == 0) {//如果没有设置过期时间。应该不能做特殊处理。否则就会兼容以前的。
//                            cookieUtils.setCookie(response, loginCookieKey, context.toCookieValue());
                        } else {
                            log.debug("session cookie[" + loginCookieKey + "] is valid!");
                            //超时后，要清空
                            cookieUtils.invalidate(request, response);
                        }
                    } else {
                        log.debug("session cookie[" + loginCookieKey + "] is error!");
                    }
                } else {
                    log.debug("session cookie[" + loginCookieKey + "] is empty!");
                }
            } catch (Exception e) {
                log.error("login intercept error", e);
            }
        } else {
            log.debug("session cookie key is empty!");
        }

    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}

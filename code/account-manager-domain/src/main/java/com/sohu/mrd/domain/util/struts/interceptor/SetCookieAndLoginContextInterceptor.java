package com.sohu.mrd.domain.util.struts.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.sohu.mrd.domain.util.struts.context.FormsAuthenticationTicket;
import com.sohu.mrd.domain.util.struts.context.LoginContext;
import com.sohu.mrd.domain.util.struts.cookie.CookieUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 保持logincontext中的session和从 dotnet ticket中读取出来的一致
 * <p/>
 * Date: 10-12-16
 * Time: 上午11:20
 */
public class SetCookieAndLoginContextInterceptor extends SoHuInterceptor {
    private final static Log log = LogFactory.getLog(SetCookieAndLoginContextInterceptor.class);

    /**
     * 读取cookie
     */
    protected CookieUtils cookieUtils;
    /**
     * 自己的login cookie
     */
    protected String loginCookieKey;

    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext actionContext = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);

        FormsAuthenticationTicket ticket = (FormsAuthenticationTicket) ActionContext.getContext().get(getInitTicketKey());
        if (ticket == null) {//如果没有这个ticket，说明登录没成功
            removeLoginContext();
        } else {
            LoginContext loginContext = LoginContext.getLoginContext();
            if (loginContext == null) {
                loginContext = getLoginContextFromTicket(ticket);
                cookieUtils.setCookie(response, loginCookieKey, loginContext.toCookieValue());
                setLoginContext(loginContext);
                newLoginContext(ticket);
            } else if(!ticket.getUsername().equals(loginContext.getPin())){
                loginContextChanged(ticket, loginContext);
                loginContext.setPin(ticket.getUsername());
                cookieUtils.setCookie(response, loginCookieKey, loginContext.toCookieValue());
                setLoginContext(loginContext);
            }
        }

        return invocation.invoke();
    }

    /**
     * 表示用户已经发生改变。logincontext的和tickit中的对应不上了
     * @param ticket
     * @param loginContext
     */
    protected void loginContextChanged(FormsAuthenticationTicket ticket, LoginContext loginContext) {

    }

    /**
     * 预留。表示没有logincontext，从ticket中创建
     * @param ticket
     */
    protected void newLoginContext(FormsAuthenticationTicket ticket) {

    }

    /**
     * 将新构造的logincontext放入actioncontext
     * 调用这个方法可能因为有ticket但是没有logincontext。有可能是新用户从passport登录过来
     * @param loginContext
     */
    protected void setLoginContext(LoginContext loginContext) {
        LoginContext.setLoginContext(loginContext);
    }

    /**
     * 从dotnet 的 ticket中重构出logincontext
     *
     * @param ticket
     * @return
     */
    protected LoginContext getLoginContextFromTicket(FormsAuthenticationTicket ticket) {
        LoginContext loginContext = new LoginContext();
        loginContext.setPin(ticket.getUsername());
        return loginContext;
    }

    /**
     * 称去登录内容
     */
    protected void removeLoginContext() {
        LoginContext.remove();
    }


    /**
     * 把登录的ticket放入actioncontext的key
     *
     * @return key
     */
    protected String getInitTicketKey() {
        return LoginContext.HTTP_DOTNET_LOGIN_TICKET_CONTEXT;
    }

    public void setCookieUtils(CookieUtils cookieUtils) {
        this.cookieUtils = cookieUtils;
    }

    public void setLoginCookieKey(String loginCookieKey) {
        this.loginCookieKey = loginCookieKey;
    }
}

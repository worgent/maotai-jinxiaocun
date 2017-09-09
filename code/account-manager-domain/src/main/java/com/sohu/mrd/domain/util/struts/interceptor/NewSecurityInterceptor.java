package com.sohu.mrd.domain.util.struts.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.sohu.mrd.domain.util.struts.context.FormsAuthenticationTicket;
import com.sohu.mrd.domain.util.struts.context.LoginContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * vender端登录
 * User: chenghaixing
 * Date: 2010-6-1
 * Time: 9:25:37
 */
public class NewSecurityInterceptor extends SecurityInterceptor {
    private final static Log log = LogFactory.getLog(NewSecurityInterceptor.class);

    protected String loginUrl = "loginUrl";


    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext actionContext = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);

        String path = request.getServletPath();
        if (jdSecurity != null) {
            if (jdSecurity.isProtected(path)) {//如果资源受保护
                FormsAuthenticationTicket ticket = (FormsAuthenticationTicket) actionContext.get(getDotnetTicketKey());
                if (ticket == null) { //先check passport的cookie有没有
                    log.debug("actioncontext hasn't a ticked!");
                    setLoginUrl();
                    return getNoLoginUrl();
                }
            }
        } else {
            log.debug("jdSecurity is null!");
        }

        return invocation.invoke();
    }

    /**
     * 未登录passport跳转的url
     * @return
     */

    protected String getNoLoginUrl() {
        return Action.LOGIN;
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

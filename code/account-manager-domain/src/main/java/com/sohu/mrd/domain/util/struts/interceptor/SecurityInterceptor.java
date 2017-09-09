package com.sohu.mrd.domain.util.struts.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;
import com.sohu.mrd.domain.util.struts.context.LoginContext;
import com.sohu.mrd.domain.util.url.JdUrl;
import com.sohu.mrd.domain.util.url.JdUrlUtils;
import com.sohu.mrd.domain.util.web.JdSecurity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 资源保护拦截器。
 * User: chenghaixing
 * Date: 2010-5-14
 * Time: 16:46:51
 */
public class SecurityInterceptor extends SoHuInterceptor {
    private final static Log log = LogFactory.getLog(SecurityInterceptor.class);
    //下面这些主要是通过spring自动注入
    protected JdSecurity jdSecurity;
    protected JdUrlUtils jdUrlUtils;
    protected String homeModule = "homeModule";
    protected String loginUrl = "loginUrl";

    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext actionContext = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        String path = request.getServletPath();
        if (jdSecurity != null) {
            if (jdSecurity.isProtected(path)) {//如果资源受保护
                //check 有没有登录
                LoginContext context = getLoginContext();
                if (context == null || !context.isLogin()) {//没登录
                    if (jdUrlUtils != null) {
                        setLoginUrl();
                        return Action.LOGIN;
                    } else {
                        log.error("jdUrlUtils is null");
                        return Action.ERROR;
                    }
                }
            }
        } else {
            log.error("jdSecurity is null");
            return Action.ERROR;
        }
        return invocation.invoke();
    }

    /**
     * 取出登录的信息
     *
     * @return
     */
    protected LoginContext getLoginContext() {
        return LoginContext.getLoginContext();
    }

    protected String getCurrentUrl(HttpServletRequest request) {
        JdUrl venderUrl = jdUrlUtils.getJdUrl(homeModule);
        venderUrl.getTarget(request.getRequestURI());
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            venderUrl.addQueryData(key, request.getParameterValues(key));
        }
        String resultUrl=venderUrl.toString();
        return resultUrl;
    }

    protected void setLoginUrl() {
        ActionContext actionContext = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        JdUrl loginUrl = jdUrlUtils.getJdUrl(this.loginUrl);
        String currentUrl = getCurrentUrl(request);
        loginUrl.addQueryData("ReturnUrl", currentUrl);
        ValueStack valueStack = actionContext.getValueStack();
        valueStack.set("returnUrl", loginUrl.encodeUrl(currentUrl));
        valueStack.set("loginUrl", loginUrl.toString());
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

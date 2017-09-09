package com.sohu.service.login.impl;

import com.sohu.dao.TLoginMapper;
import com.sohu.mrd.domain.beans.TLogin;
import com.sohu.mrd.domain.beans.TLoginExample;
import com.sohu.mrd.domain.util.springmvc.context.FormsAuthenticationTicket;
import com.sohu.mrd.domain.util.springmvc.context.LoginContext;
import com.sohu.mrd.domain.util.springmvc.cookie.CookieUtils;
import com.sohu.service.login.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TLoginMapper tLoginMapper;

    /**
     * 读取cookie
     */
    private CookieUtils cookieUtils;
    /**
     * 自己的login cookie
     */
    private String loginCookieKey;

    private List shiLiCookie;

    public CookieUtils getCookieUtils() {
        return cookieUtils;
    }

    public void setCookieUtils(CookieUtils cookieUtils) {
        this.cookieUtils = cookieUtils;
    }

    public String getLoginCookieKey() {
        return loginCookieKey;
    }

    public void setLoginCookieKey(String loginCookieKey) {
        this.loginCookieKey = loginCookieKey;
    }

    public void writeCookieAndLoginContext(HttpServletResponse response) {
        // FormsAuthenticationTicket ticket = (FormsAuthenticationTicket)
        // ActionContext
        // .getContext().get(getInitTicketKey());

        WebApplicationContext webApplicationContext = ContextLoader
                .getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext
                .getServletContext();
        FormsAuthenticationTicket ticket = (FormsAuthenticationTicket) servletContext
                .getAttribute(getInitTicketKey());

        buildShiLiCookies(ticket);

        LoginContext loginContext = LoginContext.getLoginContext();
        if (loginContext == null) {
            loginContext = getLoginContextFromTicket(ticket);
            cookieUtils.setCookie(response, loginCookieKey,
                    loginContext.toCookieValue());
            setLoginContext(loginContext);
            newLoginContext(ticket);
        } else if (!ticket.getUsername().equals(loginContext.getPin())) {
            loginContextChanged(ticket, loginContext);
            loginContext.setPin(ticket.getUsername());
            cookieUtils.setCookie(response, loginCookieKey,
                    loginContext.toCookieValue());
            setLoginContext(loginContext);
        }
    }

    public void deleteCookies(HttpServletRequest request,
                              HttpServletResponse response) {
        cookieUtils.invalidate(request, response);
    }

    private void buildShiLiCookies(FormsAuthenticationTicket ticket) {
        cookieUtils.setJdCookie(shiLiCookie);
    }

    /**
     * 把登录的ticket放入actioncontext的key
     *
     * @return key
     */
    protected String getInitTicketKey() {
        return LoginContext.HTTP_DOTNET_LOGIN_TICKET_CONTEXT;
    }

    /**
     * 从dotnet 的 ticket中重构出logincontext
     *
     * @param ticket
     * @return
     */
    protected LoginContext getLoginContextFromTicket(
            FormsAuthenticationTicket ticket) {
        LoginContext loginContext = new LoginContext();
        loginContext.setPin(ticket.getUsername());
        return loginContext;
    }

    /**
     * 预留。表示没有logincontext，从ticket中创建
     *
     * @param ticket
     */
    protected void newLoginContext(FormsAuthenticationTicket ticket) {

    }

    /**
     * 将新构造的logincontext放入actioncontext
     * 调用这个方法可能因为有ticket但是没有logincontext。有可能是新用户从passport登录过来
     *
     * @param loginContext
     */
    protected void setLoginContext(LoginContext loginContext) {
        LoginContext.setLoginContext(loginContext);
    }

    /**
     * 表示用户已经发生改变。logincontext的和tickit中的对应不上了
     *
     * @param ticket
     * @param loginContext
     */
    protected void loginContextChanged(FormsAuthenticationTicket ticket,
                                       LoginContext loginContext) {

    }

    public List getShiLiCookie() {
        return shiLiCookie;
    }

    public void setShiLiCookie(List shiLiCookie) {
        this.shiLiCookie = shiLiCookie;
    }

    public int queryTLoginTotalRows(String uname, String upwd) {
        TLoginExample tLoginExample = new TLoginExample();
        tLoginExample.createCriteria().andUsernameEqualTo(uname).andPasswordEqualTo(upwd);

        return tLoginMapper.countByExample(tLoginExample);
    }

    public void insertTLogin(String uname, String upwd) {
        TLogin tLogin = new TLogin();
        tLogin.setUsername(uname);
        tLogin.setPassword(upwd);
        tLoginMapper.insert(tLogin);
    }

	@Override
	public TLogin queryTLoginByUname(String uname) {
		TLoginExample tLoginExample = new TLoginExample();
    	tLoginExample.createCriteria().andUsernameEqualTo(uname);
    	return tLoginMapper.selectByExample(tLoginExample).get(0);
	}
    
}

package com.sohu.mrd.domain.util;

import com.opensymphony.xwork2.ActionContext;
import com.sohu.mrd.domain.util.struts.context.LoginContext;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe:获取登录人信息
 * User: dingqiong@360buy.com
 * Date: 2010-12-07
 * Time: 10:26:32
 */
public class WebHelper {
    /**
     * 获取当前登录人的ID
     *
     * @return long
     */
    public static final long getUserId() {
        if(LoginContext.getLoginContext()!=null){
            return LoginContext.getLoginContext().getUserId();
        }else{
            return 0;
        }
    }
    /**
     * 获取当前登录用户的代理商ID
     *
     * @return long
     */
    public static final int getAgentId() {
        if(LoginContext.getLoginContext()!=null){
            return LoginContext.getLoginContext().getAgentId();
        }else{
            return 0;
        }
    }

    /**
     * 获取当前登录登录人的登入账号
     *
     * @return long
     */
    public static final String getPin() {
        if (LoginContext.getLoginContext()!=null) {
            return LoginContext.getLoginContext().getPin();
        }else {
            return null;
        }
    }

    /**
     * 获取当前登录登录人的显示名称
     */
    public static final String getNick() {
        if (LoginContext.getLoginContext()!=null) {
            return LoginContext.getLoginContext().getNick();
        }else {
            return null;
        }
    }

    /**
     * 获取当前登录登录人登录信息
     *
     * @return
     */
    public static final Map<String, Object> getLoginInfo() {
        Map<String, Object> loginInfo = new HashMap<String, Object>();
        loginInfo.put("userId", getUserId());
        loginInfo.put("pin", getPin());
        loginInfo.put("nick", getNick());
        return loginInfo;
    }

    /**
     * get the user ip
     * @return
     */
    public static final String getIpAddress(){
        HttpServletRequest request = (HttpServletRequest)  ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        String ip=request.getRemoteAddr();
        if (request.getHeader("x-forwarded-for") != null
                && !"unknown".equalsIgnoreCase(request.getHeader("x-forwarded-for"))) {
            ip=ip+","+request.getHeader("x-forwarded-for");
        }
        return ip;
    }
}

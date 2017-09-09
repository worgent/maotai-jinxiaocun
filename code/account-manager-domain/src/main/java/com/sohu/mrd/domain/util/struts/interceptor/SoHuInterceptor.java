package com.sohu.mrd.domain.util.struts.interceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * User: chenghaixing
 * Date: 2010-5-14
 * Time: 16:10:05
 */
public abstract class SoHuInterceptor implements Interceptor {

    protected static final String LAST_ACCESS_TIME_COOKIE_NAME = "_latn_";

    public void destroy() {

    }

    public void init() {

    }


}

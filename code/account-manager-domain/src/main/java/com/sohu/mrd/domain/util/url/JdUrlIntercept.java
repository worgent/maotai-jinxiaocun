package com.sohu.mrd.domain.util.url;

/**
 * url渲染拦截器 <br/>
 * User: yangsiyong@360buy.com <br/>
 * Date: 2010-7-7 <br/>
 * Time: 16:40:39 <br/>
 */
public interface JdUrlIntercept {

    /**
     * 拦截渲染内容
     * @param jdUrl 需要渲染的内容
     */
    void doIntercept(JdUrl jdUrl);
}

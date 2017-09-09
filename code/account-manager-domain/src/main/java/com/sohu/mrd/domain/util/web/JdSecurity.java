package com.sohu.mrd.domain.util.web;

/**
 * 安全认证框架
 */
public interface JdSecurity {

    /**
     * 判断当前url是不是被保护
     *
     * @param path
     * @return true 是，false 不是
     */
    boolean isProtected(String path);
}
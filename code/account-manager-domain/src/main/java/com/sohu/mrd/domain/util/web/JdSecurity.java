package com.sohu.mrd.domain.util.web;

/**
 * ��ȫ��֤���
 */
public interface JdSecurity {

    /**
     * �жϵ�ǰurl�ǲ��Ǳ�����
     *
     * @param path
     * @return true �ǣ�false ����
     */
    boolean isProtected(String path);
}
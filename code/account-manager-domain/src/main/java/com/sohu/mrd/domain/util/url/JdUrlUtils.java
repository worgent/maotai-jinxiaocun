package com.sohu.mrd.domain.util.url;

import java.util.Map;

/**
 * User: yangsiyong@360buy.com
 * Date: 2010-5-14
 * Time: 18:53:34
 */
public class JdUrlUtils {

     private Map<String, JdUrl > velocityUrl;

    public void setVelocityUrl(Map<String, JdUrl> velocityUrl) {
        this.velocityUrl = velocityUrl;
    }

    public JdUrl getJdUrl(String key) {
        JdUrl org = velocityUrl.get(key);
        JdUrl jdUrl = org.clone();
        jdUrl.setJdUrl(org);
        return jdUrl;
    }
}

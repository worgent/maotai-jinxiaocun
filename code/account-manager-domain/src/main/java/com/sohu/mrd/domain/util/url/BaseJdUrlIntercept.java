package com.sohu.mrd.domain.util.url;

import com.sohu.mrd.domain.util.common.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * url重写拦截器
 * User: yangsiyong@360buy.com
 * Date: 2010-7-7
 * Time: 18:03:09
 */
public  class BaseJdUrlIntercept implements JdUrlIntercept {
    private final static Log log = LogFactory.getLog(BaseJdUrlIntercept.class);
    /**
     * 重写的参数
     */
    protected Map<String, String[]> urlMaps = new HashMap<String, String[]>();
    /**
     * url rewirte到
     */
    protected String urlSeparate = "-";
    /**
     * 后缀
     */
    protected String urlSuffix = ".html";

    public void doIntercept(JdUrl jdUrl) {
        String path = jdUrl.getPath();
        if (StringUtils.isNotBlank(path)) {
            if (urlMaps.containsKey(path)) {
                Object o = urlMaps.get(path);
                int start = path.lastIndexOf('.');
                int start1 = path.lastIndexOf('/');
                StringBuilder builder;

                if (start > start1) {//去掉扩展名。
                    builder = new StringBuilder(path.substring(0, start));
                } else {
                    builder = new StringBuilder(path);
                }
                if (o != null) {
                    String[] parameters = (String[]) o;
                    Map<String, Object> queryMap = jdUrl.getQuery();
                    for (String parameter : parameters) {
                        builder.append(urlSeparate);
                        if (StringUtils.isNotEmpty(parameter)) {
                            Object o1 = queryMap.get(parameter);
                            if (o1 != null) {
                                builder.append(jdUrl.encodeUrl(o1.toString()));
                            }
                        }
                        queryMap.remove(parameter);
                    }
                }
                builder.append(urlSuffix);
                jdUrl.setPath(builder.toString());
            }
        }
    }

    public void setUrlMaps(Map<String, String[]> urlMaps) {
        this.urlMaps = urlMaps;
    }

    public void setUrlSeparate(String urlSeparate) {
        this.urlSeparate = urlSeparate;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }
}

package com.sohu.mrd.domain.util.protocol.impl;


import com.sohu.mrd.domain.util.protocol.Request;

/**
 * @author sailor
 *
 */
public class RequestImpl implements Request {
    private String url;
    private String type;
    private String path;

    public RequestImpl() {

    }

    public RequestImpl(String url, String type) {
        this.setUrl(url);
        this.setType(type);
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

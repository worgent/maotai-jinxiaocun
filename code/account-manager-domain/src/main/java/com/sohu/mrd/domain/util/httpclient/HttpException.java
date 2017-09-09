package com.sohu.mrd.domain.util.httpclient;

/**
 * User: Created by chenbaoan@360buy.com
 * Date: 2011-4-27
 * Time: 10:22:58.
 */
public class HttpException extends Exception {

    public HttpException(String message) {
        super(message);
    }

    public HttpException(Throwable t) {
        super(t);
    }

    public HttpException(String message, Throwable t) {
        super(message, t);
    }
}

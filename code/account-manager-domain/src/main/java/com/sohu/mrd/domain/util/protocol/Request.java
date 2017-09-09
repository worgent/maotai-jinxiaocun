package com.sohu.mrd.domain.util.protocol;

/**
 * User: Created by chenbaoan@360buy.com
 * Date: 2011-4-27
 * Time: 10:22:58.
 */
public interface Request {

    /**
     * set url
     *
     * @param url
     */
    void setUrl(String url);

    /**
     * get url
     *
     * @return
     */
    String getUrl();

    /**
     * set http's type:GET, POST, HEAD, etc..
     *
     * @param type
     */
    void setType(String type); //

    /**
     * get http's type
     *
     * @return
     */
    String getType();

}

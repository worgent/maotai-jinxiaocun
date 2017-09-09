package com.sohu.mrd.domain.util.protocol;

import java.io.InputStream;

/**
 * User: Created by chenbaoan@360buy.com
 * Date: 2011-4-27
 * Time: 10:22:58.
 */
public interface Response {

    /**
     * @return
     */
    InputStream getResponseBodyAsStream();

    /**
     * @return
     */
    String getResponseBodyAsString();

    /**
     * @param charset
     * @return
     */
    String getResponseBodyAsString(String charset);

    /**
     * @return
     */
    int getStatusCode();

    /**
     * @return
     */
    String getRequestUrl();

    /**
     *
     */
    void close();
}

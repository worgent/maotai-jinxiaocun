package com.sohu.mrd.domain.util.httpclient;


import com.sohu.mrd.domain.util.protocol.Request;
import com.sohu.mrd.domain.util.protocol.Response;

import java.io.IOException;
import java.util.Map;

/**
 * User: Created by chenbaoan@360buy.com
 * Date: 2011-4-27
 * Time: 10:22:58.
 */
public abstract class HttpClientManager {
    /**
     * @param request
     * @return
     * @throws java.io.IOException
     */
    public abstract Response doRequest(Request request, Map<String, String> nameValuePairs, String code) throws HttpException, IOException;
    /**
     * 创建请求
     *
     * @return
     */
    public abstract Request createRequest();
    /**
     * 相关设置
     *
     * @param request
     */
    public abstract void setup(Request request);
}

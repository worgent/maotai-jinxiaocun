package com.sohu.mrd.domain.util.web.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: yangsiyong@360buy.com
 * Date: 2010-5-18
 * Time: 20:37:05
 */
public class CharsetFilter implements Filter {
    private final static Log log = LogFactory.getLog(CharsetFilter.class);
    public static final String DEFAULT_CHARSET_VALUE = "gbk";
    private Pattern inputCharsetPattern;
    private String defaultCharset;


    public void init(FilterConfig filterConfig) throws ServletException {
        inputCharsetPattern = Pattern.compile("_charset_=([\\w-]+)", Pattern.CASE_INSENSITIVE);
        if (defaultCharset == null) {
            defaultCharset = DEFAULT_CHARSET_VALUE;
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            // 试图从queryString中取得inputCharset
            String queryString = request.getQueryString();
            if (queryString != null) {
                Matcher matcher = inputCharsetPattern.matcher(queryString);

                if (matcher.find()) {
                    MatchResult matchResult = matcher.toMatchResult();
                    String charset = matchResult.group(1);
                    request.setCharacterEncoding(charset);
                    request.getParameterNames();
                    log.debug("Set INPUT charset to " + charset);
                }
            } else {
                request.setCharacterEncoding(defaultCharset);
            }
        } catch (UnsupportedEncodingException e) {
            try {
                request.setCharacterEncoding(defaultCharset);

            } catch (UnsupportedEncodingException ee) {
                log.error("Failed to set INPUT charset to " + defaultCharset, e);
            }
        }
        chain.doFilter(request, response);

    }

    public void destroy() {


    }


}

package com.sohu;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: Administrator
 * Date: 2010-4-27
 * Time: 15:24:04
 */
public class TimeHandler implements MethodInterceptor {
    /**
     * log
     */
    private final static Log log = LogFactory.getLog(TimeHandler.class);
    /**
     * 对于执行时间超过指定毫秒，日志级别为error <br/>
     * 单位：毫秒 <br/>
     * 默认值：200 <br/>
     */
    private int error = 200;
    /**
     * 对于执行时间超过指定毫秒，日志级别为warn <br/>
     * 单位：毫秒 <br/>
     * 默认值：100 <br/>
     */
    private int warn = 100;
    /**
     * 对于执行时间超过指定毫秒，日志级别为info <br/>
     * 单位：毫秒 <br/>
     * 默认值：50 <br/>
     */
    private int info = 50;

    /**
     * Method invoke ...
     *
     * @param methodInvocation of type MethodInvocation
     * @return Object
     * @throws Throwable when
     */
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long procTime = System.currentTimeMillis();
        try {
            return methodInvocation.proceed();
        }
        finally {
            procTime = System.currentTimeMillis() - procTime;
            if (procTime > error) {
                if (log.isErrorEnabled())
                    log.error(getMsg(methodInvocation, procTime));
            }
            else if (procTime > warn) {
                if (log.isWarnEnabled())
                    log.warn(getMsg(methodInvocation, procTime));
            }
            else if (procTime > info) {
                if (log.isInfoEnabled())
                    log.info(getMsg(methodInvocation, procTime));
            }
            else {
                if (log.isDebugEnabled())
                    log.debug(getMsg(methodInvocation, procTime));
            }
        }
    }
    private String getMsg(MethodInvocation methodInvocation, long procTime) {
        return new StringBuilder().append("Process  method ").append(methodInvocation.getMethod().getDeclaringClass().getName()).append("#")
                .append(methodInvocation.getMethod().getName()).append(" successful! Total time: ").append(procTime).append(" milliseconds!")
                .toString();
    }
    /**
     * Method setError sets the error of this TimeHandler object.
     * <p/>
     * 对于执行时间超过指定毫秒，日志级别为error <br/>
     *
     * @param error the error of this TimeHandler object.
     */
    public void setError(int error) {
        this.error = error;
    }
    /**
     * Method setWarn sets the warn of this TimeHandler object.
     * <p/>
     * 对于执行时间超过指定毫秒，日志级别为warn <br/>
     *
     * @param warn the warn of this TimeHandler object.
     */
    public void setWarn(int warn) {
        this.warn = warn;
    }
    /**
     * Method setInfo sets the info of this TimeHandler object.
     * <p/>
     * 对于执行时间超过指定毫秒，日志级别为info <br/>
     *
     * @param info the info of this TimeHandler object.
     */
    public void setInfo(int info) {
        this.info = info;
    }
}

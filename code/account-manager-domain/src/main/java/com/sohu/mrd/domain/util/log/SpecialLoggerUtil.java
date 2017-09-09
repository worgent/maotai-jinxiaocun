package com.sohu.mrd.domain.util.log;


import com.sohu.mrd.domain.util.CodesUtil;
import com.sohu.mrd.domain.util.WebHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 类StatisticsLogger.java的实现描述：热闻日志输出
 *
 * @author chenghaixing
 */
public class SpecialLoggerUtil {

	/**
	 * 用于特殊的日志
	 */
	private static final Log logger = LogFactory.getLog(SpecialLoggerUtil.class);
    public static final Log timetask = LogFactory.getLog("CHANNEL_TIMETASK");

    public static void logOperater(String machineIp,String operatorMethod ,Log log){
        StringBuilder logStr=new StringBuilder("username:"+ WebHelper.getPin());
        logStr.append("refer IP:"+ CodesUtil.getIpAddress());
        logStr.append(" operator is method:"+operatorMethod);
        logStr.append(" operator is machineIp:"+machineIp);

        log.info(logStr.toString());
    }

}

package com.sohu.mrd.domain.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Describe:重要日志输出工具类
 * User: doushihui
 * Date: 2013-8-7
 * Time: 下午6:19:23
 */
public class LogHelper {
    /**
     * 充值日志
     */
    public static final String TRANSACTION_RECHARGE = "TRANSACTION_RECHARGE";
    /**
     * 下单日志
     */
    public static final String TRANSACTION_ORDER = "TRANSACTION_ORDER";
    /**
     * 迁移历史
     */
    public static final String MOVE_ORDER = "MOVE_ORDER";
    /**
     * 商家调度
     */
    public static final String SCHEDULE = "SCHEDULE";
    /**
     * 商家调度
     */
    public static final String NOTIFY_STATUS = "NOTIFY_STATUS";
    /**
     * 支付
     */
    public static final String ORDER_PAY = "ORDER_PAY";
    /**
     * 充值回调
     */
    public static final String ORDER_FILL = "ORDER_FILL";
    /**
     * 手动处理操作日志
     */
    public static final String RECHARGE_SMS = "RECHARGE_SMS";


    public static final Log recharge = LogFactory.getLog(TRANSACTION_RECHARGE);
    public static final Log order = LogFactory.getLog(TRANSACTION_RECHARGE);
    public static final Log moveorder = LogFactory.getLog(MOVE_ORDER);
    public static final Log orderpay = LogFactory.getLog(ORDER_PAY);
    public static final Log schedule = LogFactory.getLog(SCHEDULE);
    public static final Log notify = LogFactory.getLog(NOTIFY_STATUS);
    public static final Log orderfill = LogFactory.getLog(ORDER_FILL);
    public static final Log rechargesms = LogFactory.getLog(RECHARGE_SMS);

    /**
     * 打印错误信息
     * @param e
     * @return
     */
    public static String getExceptionTrace(Throwable e) {
        if (e != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        }
        else
            return null;
    }
}

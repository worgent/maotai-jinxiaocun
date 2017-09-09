package com.sohu.mrd.domain.util;


import com.sohu.mrd.domain.util.common.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 充值工具类
 * Describe:
 * User: doushihui
 * Date: 2013-7-31
 * Time: 下午3:00:26
 */
public class CzUtil {
    private String directory;//导入路径
    private String serverType;//是正式还是测试环境
    private String timeTaskServerIP;//运行时间程序的服务器IP

    /**
     * 取请求IP
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * ip地址是否在白名单
     * @return
     */
    public static boolean ipLicit(String ip,String licitIp){
        boolean flag = false;
        if(StringUtils.isEmpty(ip) || StringUtils.isEmpty(licitIp)){
            return flag;
        }
        String[] ips = ip.split(",");
        if(ips.length<1){
            return flag;
        }
        for(String ipstr :ips){
            if(licitIp.indexOf(ipstr.trim())<0){
                return flag;
            }
        }
        return true;
    }

    /**
     * 客户端IP
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For") + "," + request.getRemoteAddr();
        if (ip != null && ip.length() > 50) {
            ip = ip.substring(0, 50);
        }
        return ip;
    }
    public String getServerType() {
        return serverType;
    }
    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getDirectory() {
        return directory;
    }
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setTimeTaskServerIP(String timeTaskServerIP) {
        this.timeTaskServerIP = timeTaskServerIP;
    }
    public String getTimeTaskServerIP() {
        return timeTaskServerIP;
    }
    public static void main(String[] args) {
        boolean flag = ipLicit("60.247.90.254", "60.247.90.254");
        System.out.println(flag);
    }
}

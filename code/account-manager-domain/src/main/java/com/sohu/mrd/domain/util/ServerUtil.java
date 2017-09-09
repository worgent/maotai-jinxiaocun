package com.sohu.mrd.domain.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ServerUtil {
    private final static Logger log = LogManager.getLogger(ServerUtil.class);
    private static String[] serverIps = null;
    static {
        serverIps = initServerIps();//启动时初始化,提高性能
    }

    /**
     * 初始化ip
     * @return
     */
    private static String[] initServerIps() {
        List<String> ips = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
                if (inetAddresses != null && inetAddresses.hasMoreElements()) {
                    InetAddress ip = (InetAddress) inetAddresses.nextElement();
                    if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()) {
                        ips.add(ip.getHostAddress());
                    }
                }
            }
        }
        catch (SocketException e) {
            log.error("getServerIP error!", e);
        }
        if (!ips.isEmpty()) {
            String[] ips1 = new String[ips.size()];
            ips.toArray(ips1);
            return ips1;
        }
        else {
            log.error("ip获取失败,可能是vm的启动参数中没有加以下内容：-Djava.net.preferIPv4Stack=true");
            return null;
        }
    }
    /**
     * 取本地机器IP
     * 由于本机可能会有多个IP，建议取机器名称。
     * @return
     * @throws Exception
     */
    public static String getServerIP() {
        if (serverIps != null && serverIps.length > 0) {
            return serverIps[0];
        }
        return "";
    }
    /**
     * 获取服务器名
     */
    public static String getServerName() {
        String ret = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ret = addr.getHostName().toString();
        }
        catch (Exception e) {
            log.error("getServerName error!", e);
        }
        return ret;
    }
    /**
     * 获取应用程序集名
     */
    public static String getAppliationName() {
        String ret = "";
        try {
            String catalina = System.getProperty("catalina.base");
            catalina = catalina.replaceAll("\\\\", "//");
            if (catalina.length() > 50) {
                catalina = operateString(catalina);
            }
            ret = catalina;
        }
        catch (Exception e) {
            log.error("getAppliationName error!", e);
        }
        return ret;
    }
    /**
     * 服务器标识字符串处理
     * 超过五十个字符截取
     */
    private static String operateString(String str) {
        String ret = "";
        ret = str.substring(str.length() - 50, str.length());
        return ret;
    }
}

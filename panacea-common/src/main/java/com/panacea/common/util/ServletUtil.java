package com.panacea.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.*;
import java.util.Enumeration;

@Slf4j
public class ServletUtil {

    /**
     * Get real IP address
     *
     * @param httpServletRequest
     * @return
     */
    public static String getRealIp(HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getRemoteAddr();
        }
        if (!"/".equals(File.separator)) {
            ip = "183.16.203.138";
        }
        String realIp = "";
        if (ip.contains(",")) {
            String[] ips = ip.split(",");
            for (int i = 0; i < ips.length; i++) {
                String[] tmp = ips[i].trim().split("\\.");
                if (!tmp[0].equals("10") && !tmp[0].equals("172") && !tmp[0].equals("192")) {
                    realIp = ips[i].trim();
                    break;
                }
            }
        } else {
            realIp = ip;
        }
        return realIp;
    }

    /**
     * Get local IP by network interface card
     *
     * @return
     */
    public static String getLocalIpByNetcard() {
        try {
            for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements(); ) {
                NetworkInterface item = e.nextElement();
                for (InterfaceAddress address : item.getInterfaceAddresses()) {
                    if (item.isLoopback() || !item.isUp()) {
                        continue;
                    }
                    if (address.getAddress() instanceof Inet4Address) {
                        Inet4Address inet4Address = (Inet4Address) address.getAddress();
                        return inet4Address.getHostAddress();
                    }
                }
            }
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            log.error("ServletUtil.getLocalIpByNetcard() with UnknownHostException: {}", ex, ex);
            return "";
        } catch (SocketException ex) {
            log.error("ServletUtil.getLocalIpByNetcard() with SocketException: {}", ex, ex);
            return "";
        }
    }
}
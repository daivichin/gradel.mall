package com.gradel.common.shared.web;

import com.gradel.common.shared.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WebUtil {

    public static String getURL(HttpServletRequest request) {
        String contextPath = request.getContextPath().equals("/") ? "" : request.getContextPath();
        String url = "http://" + request.getServerName();
        if (null2Int(Integer.valueOf(request.getServerPort())) != 80) {
            url = url + ":" + null2Int(Integer.valueOf(request.getServerPort())) + contextPath;
        } else {
            url = url + contextPath;
        }
        return url;
    }

    public static int null2Int(Object s) {
        int v = 0;
        if (s != null) {
            try {
                v = Integer.parseInt(s.toString());
            } catch (Exception localException) {
            }
        }
        return v;
    }

    /**
     * requeset所有参数
     * @param request
     * @return
     */
    public static Map<String, String> getParamMap(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        if (params == null) {
            return null;
        }
        Map<String, String> queryMap = new HashMap<String, String>();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getValue() == null || entry.getValue().length <= 0) {
                continue;
            }
            queryMap.put(entry.getKey(), entry.getValue()[0]);
        }
        return queryMap;
    }

    /**
     * 查询条件拼接  不以q_开头的
     * @param request
     * @return
     */
    public static Map<String, Object> handlerQueryMapNoQ(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        if (params == null) {
            return null;
        }
        Map<String, Object> queryMap = new HashMap<String, Object>();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getValue() == null || entry.getValue().length <= 0) {
                continue;
            }
            //不考虑复选框多值情况，通常查询条件使用复选框为单值情况。
            if (entry.getValue().length == 1) {
                queryMap.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        return queryMap;
    }

    /**
     * 获取请求的IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        // 如果客户端经过多级反向代理，则X-Forwarded-For的值并不止一个，而是一串IP值，
        // 取X-Forwarded-For中第一个非unknown的有效IP字符串即为用户真实IP
        String ipResult = getIp(ip);
        if (ipResult != null) {
            return ipResult;
        }

        ip = request.getHeader("Proxy-Client-IP");
        ipResult = getIp(ip);
        if (ipResult != null) {
            return ipResult;
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        ipResult = getIp(ip);
        if (ipResult != null) {
            return ipResult;
        }
        ip = request.getRemoteAddr();
        ipResult = getIp(ip);
        if (ipResult != null) {
            return ipResult;
        }
        return "";
    }

    private static String getIp(String ips) {
        if (StringUtil.isEmpty(ips, true)) {
            return null;
        }
        String[] tokens = ips.split(",");
        for (String s : tokens) {
            if (StringUtil.isIp(s.trim())) {
                return s.trim();
            }
        }
        return null;
    }
}

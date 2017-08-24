package com.io.ssm.module.common.utils;

import com.io.ssm.module.common.config.Global;
import com.io.ssm.module.common.utils.cryptography.CryptographyUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * @author lenovo
 * @version 2017-08-06 10:59:07
 * @date 2017年08月06日
 */
public class Servlets {
    public static final long ONE_YEAR_SECONDS = 31536000L;
    private static final String[] staticFiles = StringUtils.split(Global.getConfig("web.staticFile"), ",");

    private static final String urlSuffix = Global.getUrlSuffix();

    public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
        response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000L);

        response.setHeader("Cache-Control", new StringBuilder().append("private, max-age=").append(expiresSeconds).toString());
    }

    public static void setNoCacheHeader(HttpServletResponse response) {
        response.setDateHeader("Expires", 1L);
        response.addHeader("Pragma", "no-cache");

        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
    }

    public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
        response.setDateHeader("Last-Modified", lastModifiedDate);
    }

    public static void setEtag(HttpServletResponse response, String etag) {
        response.setHeader("ETag", etag);
    }

    public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response, long lastModified) {
        long ifModifiedSince = request.getDateHeader("If-Modified-Since");
        if ((ifModifiedSince != -1L) && (lastModified < ifModifiedSince + 1000L)) {
            response.setStatus(304);
            return false;
        }
        return true;
    }

    public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
        String headerValue = request.getHeader("If-None-Match");
        if (headerValue != null) {
            boolean conditionSatisfied = false;
            if (!"*".equals(headerValue)) {
                StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

                while ((!conditionSatisfied) && (commaTokenizer.hasMoreTokens())) {
                    String currentToken = commaTokenizer.nextToken();
                    if (currentToken.trim().equals(etag))
                        conditionSatisfied = true;
                }
            } else {
                conditionSatisfied = true;
            }

            if (conditionSatisfied) {
                response.setStatus(304);
                response.setHeader("ETag", etag);
                return false;
            }
        }
        return true;
    }

    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition", new StringBuilder().append("attachment; filename=\"").append(encodedfileName).append("\"").toString());
        } catch (UnsupportedEncodingException e) {
            e.getMessage();
        }
    }

    public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
        Validate.notNull(request, "Request must not be null", new Object[0]);
        Enumeration paramNames = request.getParameterNames();
        Map params = new TreeMap();
        String pre = prefix;
        if (pre == null) {
            pre = "";
        }
        while ((paramNames != null) && (paramNames.hasMoreElements())) {
            String paramName = (String) paramNames.nextElement();
            if (("".equals(pre)) || (paramName.startsWith(pre))) {
                String unprefixed = paramName.substring(pre.length());
                String[] values = request.getParameterValues(paramName);
                if ((values == null) || (values.length == 0)) {
                    values = new String[0];
                } else if (values.length > 1)
                    params.put(unprefixed, values);
                else {
                    params.put(unprefixed, values[0]);
                }
            }
        }
        return params;
    }

    public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix) {
        StringBuilder queryStringBuilder = new StringBuilder();

        String pre = prefix;
        if (pre == null) {
            pre = "";
        }
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            queryStringBuilder.append(pre).append((String) entry.getKey()).append("=").append(entry.getValue());
            if (it.hasNext()) {
                queryStringBuilder.append("&");
            }
        }
        return queryStringBuilder.toString();
    }

    public static String encodeHttpBasic(String userName, String password) {
        String encode = new StringBuilder().append(userName).append(":").append(password).toString();
        return new StringBuilder().append("Basic ").append(CryptographyUtil.encBase64(encode)).toString();
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        Principal principal = UserUtils.getPrincipal();

        return ((accept != null) && (accept.indexOf("application/json") != -1)) || ((xRequestedWith != null) && (xRequestedWith.indexOf("XMLHttpRequest") != -1)) || ((principal != null) && (principal.isMobileLogin()));
    }

    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean isStaticFile(String uri) {
        if (staticFiles == null) {
            try {
                throw new Exception("检测到“app.properties”中没有配置“web.staticFile”属性。配置示例：\n#静态文件后缀\nweb.staticFile=.css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (StringUtils.endsWithAny(uri, staticFiles)) if (!StringUtils.endsWithAny(uri, new CharSequence[]{urlSuffix}))
            if (!StringUtils.endsWithAny(uri, new CharSequence[]{".jsp"}))
                if (!StringUtils.endsWithAny(uri, new CharSequence[]{".java"})) {
                    return true;
                }
        return false;
    }
}
package com.io.ssm.module.common.config;

import com.io.ssm.module.common.utils.properties.PropertiesLoader;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.springframework.core.io.DefaultResourceLoader;

/**
 * @author lenovo
 * @version 2017-08-06 10:37:12
 * @date 2017年08月06日
 */
public class Global {
    public static final String SHOW = "Y";
    public static final String HIDE = "N";
    public static final String YES = "Y";
    public static final String NO = "N";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String USERFILES_BASE_URL = "/userfiles/";
    private static Global global = new Global();
    private static Map<String, String> map = Maps.newHashMap();
    private static PropertiesLoader loader = new PropertiesLoader(new String[]{"raycom.properties", "raycom-jms.properties"});

    public static Global getInstance() {
        return global;
    }

    public static String getConfig(String key) {
        String value = (String) map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : "");
        }
        return value;
    }

    public static String getAdminPath() {
        return getConfig("adminPath");
    }

    public static String getFrontPath() {
        return getConfig("frontPath");
    }

    public static String getUrlSuffix() {
        return getConfig("urlSuffix");
    }

    public static Boolean isSynActivitiIndetity() {
        String dm = getConfig("activiti.isSynActivitiIndetity");
        return Boolean.valueOf(("true".equals(dm)) || ("Y".equals(dm)));
    }

    public static Object getConst(String field) {
        try {
            return Global.class.getField(field).get(null);
        } catch (Exception e) {
        }
        return null;
    }

    public static String getProjectPath() {
        String projectPath = getConfig("projectPath");
        if (StringUtils.isNotBlank(projectPath))
            return projectPath;
        try {
            File file = new DefaultResourceLoader().getResource("").getFile();
            if (file != null) {
                while (true) {
                    File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
                    if ((f == null) || (f.exists())) {
                        break;
                    }
                    if (file.getParentFile() == null) break;
                    file = file.getParentFile();
                }

                projectPath = file.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projectPath;
    }
}
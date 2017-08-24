package com.io.ssm.module.common.bean;

import java.io.Serializable;

/**
 * @author lenovo
 * @version 2017-08-06 13:10:17
 * @date 2017年08月06日
 */
public class Principal implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String loginName;
    private String name;

    public Principal(String id, String loginName, String name) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionid() {
        try {
            return (String) UserUtils.getSession().getId();
        } catch (Exception e) {
        }
        return "";
    }

    public String toString() {
        return this.id;
    }
}
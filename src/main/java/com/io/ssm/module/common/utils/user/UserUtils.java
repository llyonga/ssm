package com.io.ssm.module.common.utils.user;

import com.io.ssm.module.common.bean.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

/**
 * @author lenovo
 * @version 2017-08-06 13:19:16
 * @date 2017年08月06日
 */
public class UserUtils {

    public static Principal getPrincipal() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null)
                return principal;
        } catch (UnavailableSecurityManagerException e) {
        } catch (InvalidSessionException e) {
        }
        return null;
    }
}

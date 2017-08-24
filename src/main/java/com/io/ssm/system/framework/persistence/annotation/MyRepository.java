package com.io.ssm.system.framework.persistence.annotation;
/**
 * @date 2017年6月25日
 * @author lvyong
 * @version 2017-06-25 上午9:53:43
 */

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Component
public @interface MyRepository {
	String value() default "";
}

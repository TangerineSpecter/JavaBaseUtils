package com.tangerinespecter.javabaseutils.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tangerinespecter.javabaseutils.common.util.Constant;

/**
 * 类注解
 *
 * @author TangerineSpecter
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassInfo {

    String Name() default Constant.Chinese.TOOL;

    String Author() default Constant.Chinese.ANONYMOUS;

    String Time() default Constant.NULL_KEY_STR;
}

package com.tangerineSpecter.javaBaseUtils.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import common.util.Constant;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassInfo {

	public String Name() default Constant.Chinese.TOOL;

	public String Author() default Constant.Chinese.ANONYMOUS;

	public String Time() default Constant.NULL_KEY_STR;
}

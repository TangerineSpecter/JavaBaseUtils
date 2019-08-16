package com.tangerinespecter.javabaseutils.common.annotation;

import com.tangerinespecter.javabaseutils.common.util.Constant;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {

    /**
     * 方法名
     */
    String Name() default Constant.Chinese.NOTHING;

    /**
     * 参数信息
     */
    String[] paramInfo() default Constant.Chinese.NOTHING;

    /**
     * 返回信息
     */
    String returnInfo() default Constant.Chinese.NOTHING;
}

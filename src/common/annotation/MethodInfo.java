package common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import common.util.Constant;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {

	/** 方法名 */
	public String Name() default Constant.Chinese.NOTHING;

	/** 参数信息 */
	public String[] paramInfo() default Constant.Chinese.NOTHING;

	/** 返回信息 */
	public String returnInfo() default Constant.Chinese.NOTHING;
}

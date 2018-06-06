package common.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {

	/** 方法名 */
	public String Name() default "无";

	/** 参数信息 */
	public String paramInfo() default "无";

	/** 返回信息 */
	public String returnInfo() default "无";
}

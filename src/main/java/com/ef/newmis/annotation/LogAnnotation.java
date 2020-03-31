package com.ef.newmis.annotation;

import java.lang.annotation.*;

/**
 * 操作日志自定义注解
 * https://www.jianshu.com/p/444c6cef9fd2  (关于日志的详细描述，以后再增加)
 * @author dirxu
 *
 */
@Target({ElementType.METHOD}) // 作用在方法上
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Documented // 说明该注解将被包含在javadoc中
public @interface LogAnnotation {

    /**
     * 记录操作描述
     *
     */
    String description() default "";

    /**
     * 增删改的数据的类型
     */
    Class<?> clazz();
}

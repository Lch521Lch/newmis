package com.ef.newmis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jinbin
 * @date 2018-07-08 20:40
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}


//@Target:注解的作用目标
//@Target(ElementType.TYPE)——接口、类、枚举、注解
//@Target(ElementType.FIELD)——字段、枚举的常量
//@Target(ElementType.METHOD)——方法
//@Target(ElementType.PARAMETER)——方法参数
//@Target(ElementType.CONSTRUCTOR) ——构造函数
//@Target(ElementType.LOCAL_VARIABLE)——局部变量
//@Target(ElementType.ANNOTATION_TYPE)——注解
//@Target(ElementType.PACKAGE)——包
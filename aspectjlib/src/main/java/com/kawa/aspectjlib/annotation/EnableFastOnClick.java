package com.kawa.aspectjlib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/****
 * <pre>
 *  Created:         Kawa on 2018/5/22 11:29.
 *  E-mail:          958129971@qq.com
 *  Desc:            激活快速点击注释标签
 * </pre> 
 ****/
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface EnableFastOnClick {
}
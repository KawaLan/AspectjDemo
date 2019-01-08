package com.kawa.aspectjlib.annotation.obfuscator;

import java.lang.annotation.Inherited;

/****
 * <pre>
 *  Project_Name:    Bookkeeping
 *  Created:         Kawa on 2019/1/8 11:20.
 *  E-mail:          958129971@qq.com
 *  Desc:            本类、所有变量不被混淆(包括私有变量和方法)
 * </pre>    -keep @com.kawa.aspectjlib.annotation.obfuscator.ObfuscateKeepAll class * { *; }
 ****/
@Inherited
public @interface ObfuscateKeepAll {
}
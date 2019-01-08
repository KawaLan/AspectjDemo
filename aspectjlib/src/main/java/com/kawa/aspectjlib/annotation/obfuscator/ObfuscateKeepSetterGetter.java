package com.kawa.aspectjlib.annotation.obfuscator;

import java.lang.annotation.Inherited;

/****
 * <pre>
 *  Project_Name:    Bookkeeping
 *  Created:         Kawa on 2019/1/8 11:24.
 *  E-mail:          958129971@qq.com
 *  Desc:       本类的set/get方法 不被混淆
 *     -keepclassmembers @com.kawa.aspectjlib.annotation.obfuscator.ObfuscateKeepSetterGetter class * {
 *       void set*(***);
 *       boolean is*();
 *       *** get*();
 *  }
 *
 * </pre> 
 ****/
@Inherited
public @interface ObfuscateKeepSetterGetter {

}
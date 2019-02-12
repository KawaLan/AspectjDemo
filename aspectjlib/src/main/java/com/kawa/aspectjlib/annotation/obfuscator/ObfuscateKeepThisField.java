package com.kawa.aspectjlib.annotation.obfuscator;

import java.lang.annotation.Inherited;

/****
 * <pre>
 *  Project_Name:    Bookkeeping
 *  Created:         Kawa on 2019/1/8 11:25.
 *  E-mail:          958129971@qq.com
 *  Desc:
 *  -keepclassmembers @com.kawa.aspectjlib.annotation.obfuscator.ObfuscateKeepSetterGetter class * {
 *     void set*(***);
 *     boolean is*();
 *     *** get*();
 * }
 * </pre> 
 ****/
@Inherited
public @interface ObfuscateKeepThisField {

}

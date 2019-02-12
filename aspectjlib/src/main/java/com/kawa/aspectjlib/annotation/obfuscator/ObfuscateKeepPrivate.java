package com.kawa.aspectjlib.annotation.obfuscator;

import java.lang.annotation.Inherited;

/****
 * <pre>
 *  Project_Name:    Bookkeeping
 *  Created:         Kawa on 2019/1/8 11:22.
 *  E-mail:          958129971@qq.com
 *  Desc:            本类的private方法/变量 不被混淆
 *  -keep @com.kawa.aspectjlib.annotation.obfuscator.ObfuscateKeepPrivate class * {
 *  public <fields>;
 *  public <methods>;
 * }
 * </pre> 
 ****/
@Inherited
public @interface ObfuscateKeepPrivate {
}

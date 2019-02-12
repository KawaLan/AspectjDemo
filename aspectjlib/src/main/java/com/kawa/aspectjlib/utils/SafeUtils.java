package com.kawa.aspectjlib.utils;

import android.util.Log;

import java.lang.reflect.Field;

/****
 * <pre>
 *  Project_Name:    AspectjDemo
 *  Created:         Kawa on 2018/10/29 10:34.
 *  E-mail:          958129971@qq.com
 *  Desc:            安全检测
 * </pre> 
 ****/
public class SafeUtils {

    /**
     * 检测Xposed框架，设置其不启动hooks
     */
    public static void checkXposed() {
        try {
            Class<?> aClass = ClassLoader.getSystemClassLoader()
                    .loadClass("de.robv.android.xposed.XposedBridge");
            Field field1 = aClass.getDeclaredField("disableHooks");
            Field field2 = aClass.getDeclaredField("runtime");
            field1.setAccessible(true);
            field2.setAccessible(true);
            field1.setBoolean(null, true);
            field1.setInt(null, 2);
        } catch (Exception e) {
            Log.d("_", e.toString());
        }
    }

}

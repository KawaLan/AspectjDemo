package com.kawa.aspectjlib.utils;

import android.os.Handler;

/****
 * <pre>
 *  Created:         Kawa on 2018/5/22 11:29.
 *  E-mail:          958129971@qq.com
 *  Desc:            点击事件工具类
 * </pre> 
 ****/
public class ClickUtils {

    public static boolean isDoubleClick = false;

    public static boolean isEnableStartActivity() {
        if (isDoubleClick) {
            return false;
        }
        isDoubleClick = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isDoubleClick = false;
            }
        }, 1000);
        return true;
    }
}

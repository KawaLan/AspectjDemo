package com.kawa.aspectjlib.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/****
 * <pre>
 *  Created:         Kawa on 2018/5/22 11:29.
 *  E-mail:          958129971@qq.com
 *  Desc:            权限工具类
 * </pre> 
 ****/
public class PermissionUtils {

    /**
     * 检测是否获取了权限
     *
     * @param contextObject
     * @return
     */
    public static boolean checkPermission(Object contextObject, String permission) {
        if (contextObject == null) {
            return false;
        }
        if (ContextCompat.checkSelfPermission((Activity) contextObject, permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    /**
     * 检测是否获取了权限
     *
     * @param contextObject
     * @return
     */
    public static boolean checkPermissions(Object contextObject, String permission[]) {
        if (contextObject == null) {
            return false;
        }
        for (String pes : permission) {
            if (ContextCompat.checkSelfPermission((Activity) contextObject, pes) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 申请权限
     *
     * @param contextObject
     * @param permission
     * @param requestCode
     */
    public static void requestPermissions(final Object contextObject, String[] permission, int requestCode) {
        ActivityCompat.requestPermissions((Activity) contextObject, permission, requestCode);
    }

    /**
     * 检查是否都赋予权限
     *
     * @param grantResults grantResults
     * @return 所有都同意返回true 否则返回false
     */
    public static boolean verifyPermissions(int... grantResults) {
        if (grantResults.length == 0) return false;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查所给权限List是否需要给提示
     *
     * @param activity    Activity
     * @param permissions 权限list
     * @return 如果某个权限需要提示则返回true
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }
}

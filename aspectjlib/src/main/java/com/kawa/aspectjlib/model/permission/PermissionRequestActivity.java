package com.kawa.aspectjlib.model.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kawa.aspectjlib.R;
import com.kawa.aspectjlib.interf.PermissionListener;
import com.kawa.aspectjlib.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

/****
 * <pre>
 *  Created:         Kawa on 2018/5/22 11:29.
 *  E-mail:          958129971@qq.com
 *  Desc:
 * </pre>
 ****/
public class PermissionRequestActivity extends Activity {

    private static String PERMISSION_KEY = "permission_key";
    private static String REQUEST_CODE = "request_code";
    private static PermissionListener listener;

    private String[] permissions;
    private int requestCode;

    /**
     * 跳转到Activity申请权限
     *
     * @param context            Context
     * @param permissions        Permission List
     * @param permissionListener Interface
     */
    public static void PermissionRequest(Context context, String[] permissions, int requestCode, PermissionListener permissionListener) {
        listener = permissionListener;
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PERMISSION_KEY, permissions);
        bundle.putInt(REQUEST_CODE, requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_request);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            permissions = getIntent().getStringArrayExtra(PERMISSION_KEY);
            requestCode = getIntent().getIntExtra(REQUEST_CODE, 0);
            if (permissions.length <= 0) {
                finish();
            } else {
                requestPermission(permissions);
            }
        } else {
            finish();
        }
    }

    /**
     * 请求权限
     *
     * @param permissions
     */
    private void requestPermission(String[] permissions) {
        //有权限
        if (PermissionUtils.checkPermissions(this, permissions)) {
            if (listener != null) {
                listener.PermissionGranted();
            }
            finish();
            overridePendingTransition(0, 0);
        }
        //没有权限
        else {
            PermissionUtils.requestPermissions(this, permissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //有权限
        if (PermissionUtils.verifyPermissions(grantResults)) {
            if (listener != null) {
                listener.PermissionGranted();
            }
        } else {
            if (!PermissionUtils.shouldShowRequestPermissionRationale(this, permissions)) {
                //权限被拒绝并且选中不再提示
                if (permissions.length != grantResults.length) return;
                List<String> denyList = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == -1) {
                        denyList.add(permissions[i]);
                    }
                }
                if (listener != null) {
                    listener.PermissionDenied(requestCode, denyList);
                }
            } else {
                //权限被取消
                if (listener != null) {
                    listener.PermissionCanceled(requestCode);
                }
            }
        }
        finish();
        overridePendingTransition(0, 0);
    }
}

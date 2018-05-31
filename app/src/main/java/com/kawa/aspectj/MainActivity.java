package com.kawa.aspectj;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.kawa.aspectjlib.annotation.Permission;
import com.kawa.aspectjlib.annotation.PermissionCancel;
import com.kawa.aspectjlib.annotation.PermissionDenied;

/****
 * <pre>
 *  Created:         Kawa on 2018/5/22 11:29.
 *  E-mail:          958129971@qq.com
 *  Desc:            权限注释标签
 * </pre>
 ****/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 呼叫
     *
     * @param view
     */
    @Permission(value = {Manifest.permission.CALL_PHONE}, requestCode = 10001)
    public void call(View view) {
        startActivity(getCallIntent("18011721275"));
    }

    @PermissionDenied(value = "你的权限被拒绝了")
    private void PermissionDenied(int requestCode) {
    }

    @PermissionCancel(value = "您的权限被取消了，请先去设置")
    private void PermissionCancel(int requestCode) {
    }


    /**
     * 获取拨打电话意图
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.CALL_PHONE"/>}</p>
     *
     * @param phoneNumber 电话号码
     */
    public Intent getCallIntent(String phoneNumber) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10001) {
            Log.e("kawa", ">>>>>>" + "设置权限回来<<<<<<");
        }
    }
}

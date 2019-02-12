package com.kawa.aspectjlib.constant;

import android.text.TextUtils;

import com.kawa.aspectjlib.annotation.obfuscator.ObfuscateKeepThisField;

/****
 * <pre>
 *  Project_Name:    YLM
 *  Created:         Kawa on 2018/8/30 14:35.
 *  E-mail:          958129971@qq.com
 *  Desc:            
 * </pre> 
 ****/
@ObfuscateKeepThisField
public enum PermissionStatus {
    REQUEST_INSTALL_PACKAGES("android.permission.REQUEST_INSTALL_PACKAGES", "应用安装权限"),
    SYSTEM_ALERT_WINDOW("android.permission.SYSTEM_ALERT_WINDOW", "悬浮窗权限"),
    READ_CALENDAR("android.permission.READ_CALENDAR", "读取日程提醒"),
    WRITE_CALENDAR("android.permission.WRITE_CALENDAR", " 写入日程提醒"),
    CAMERA("android.permission.CAMERA", "拍照权限"),
    READ_CONTACTS("android.permission.READ_CONTACTS", "读取联系人"),
    WRITE_CONTACTS("android.permission.WRITE_CONTACTS", " 写入联系人"),
    GET_ACCOUNTS("android.permission.GET_ACCOUNTS", "访问账户列表"),
    ACCESS_FINE_LOCATION("android.permission.ACCESS_FINE_LOCATION", " 获取精确位置"),
    ACCESS_COARSE_LOCATION("android.permission.ACCESS_COARSE_LOCATION", "获取粗略位置"),
    RECORD_AUDIO("android.permission.RECORD_AUDIO", "录音权限"),
    READ_PHONE_STATE("android.permission.READ_PHONE_STATE", "读取电话状态"),
    CALL_PHONE("android.permission.CALL_PHONE", "拨打电话"),
    READ_CALL_LOG("android.permission.READ_CALL_LOG", "读取通话记录"),
    WRITE_CALL_LOG("android.permission.WRITE_CALL_LOG", "写入通话记录"),
    ADD_VOICEMAIL("com.android.voicemail.permission.ADD_VOICEMAIL", "添加语音邮件"),
    USE_SIP("android.permission.USE_SIP", "使用SIP视频"),
    PROCESS_OUTGOING_CALLS("android.permission.PROCESS_OUTGOING_CALLS", "处理拨出电话"),
    BODY_SENSORS("android.permission.BODY_SENSORS", "传感器"),
    SEND_SMS("android.permission.SEND_SMS", "发送短信"),
    RECEIVE_SMS("android.permission.RECEIVE_SMS", "接收短信"),
    READ_SMS("android.permission.READ_SMS", "读取短信"),
    RECEIVE_WAP_PUSH("android.permission.RECEIVE_WAP_PUSH", "接收WAP PUSH信息"),
    RECEIVE_MMS("android.permission.RECEIVE_MMS", "接收彩信"),
    READ_EXTERNAL_STORAGE("android.permission.READ_EXTERNAL_STORAGE", "读取手机存储"),
    WRITE_EXTERNAL_STORAGE("android.permission.WRITE_EXTERNAL_STORAGE", "写入手机存储"),
    UNKNOWN_PERMISSION("unknown_permission", "-");


    private String permission;
    private String tip;

    PermissionStatus(String permission, String tip) {
        this.permission = permission;
        this.tip = tip;
    }

    public String getPermission() {
        return permission;
    }

    public String getTip() {
        return tip;
    }

    /**
     * 获取对应的资源
     *
     * @param value
     * @return
     */
    public static String getPermissionTip(String value) {
        for (PermissionStatus permissionStatus : PermissionStatus.values()) {
            if (TextUtils.equals(value,permissionStatus.getPermission())) {
                return permissionStatus.getTip();
            }
        }
        return UNKNOWN_PERMISSION.getTip();
    }
}

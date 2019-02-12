package com.kawa.aspectjlib.utils;

import com.kawa.aspectjlib.constant.PermissionStatus;

import java.util.Arrays;
import java.util.List;

/****
 * <pre>
 *  Project_Name:    Bookkeeping
 *  Created:         Kawa on 2019/1/14 15:58.
 *  E-mail:          958129971@qq.com
 *  Desc:            
 * </pre> 
 ****/
public class StringTipUtils {

    public static String getPermissionTip(List<String> stringList) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("请先去设置(");
        int index = 0;
        for (String s : stringList) {
            stringBuffer.append(PermissionStatus.getPermissionTip(s));
            if (index != stringList.size() - 1) {
                stringBuffer.append("/");
            }
            index++;
        }
        stringBuffer.append(")权限才能继续操作噢！");
        return stringBuffer.toString();
    }

    public static void main(String[] ar) {
        String[] permissions = new String[]{"1"};
        List<String> list = Arrays.asList(permissions);
        System.out.print(list.toString());
    }
}

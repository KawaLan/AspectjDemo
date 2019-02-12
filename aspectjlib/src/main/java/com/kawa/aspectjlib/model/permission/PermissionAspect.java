package com.kawa.aspectjlib.model.permission;

import android.app.Fragment;
import android.content.Context;
import android.text.TextUtils;

import com.kawa.aspectjlib.annotation.Permission;
import com.kawa.aspectjlib.annotation.PermissionCancel;
import com.kawa.aspectjlib.annotation.PermissionDenied;
import com.kawa.aspectjlib.dialog.DialogUtils;
import com.kawa.aspectjlib.interf.PermissionListener;
import com.kawa.aspectjlib.utils.StringTipUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/****
 * <pre>
 *  Created:         Kawa on 2018/5/22 11:29.
 *  E-mail:          958129971@qq.com
 *  Desc:            权限的AOP
 * </pre> 
 ****/
@Aspect
public class PermissionAspect {

    private static final String PERMISSION_REQUEST_POINTCUT =
            "execution(@com.kawa.aspectjlib.annotation.Permission * *(..))";

    @Pointcut(PERMISSION_REQUEST_POINTCUT + " && @annotation(permission)")
    public void requestPermissionMethod(Permission permission) {
    }

    @Around("requestPermissionMethod(permission)")
    public void AroundJoinPoint(final ProceedingJoinPoint joinPoint, Permission permission) {
        Context context = null;
        final Object object = joinPoint.getThis();
        if (object instanceof Context) {
            context = (Context) object;
        } else if (object instanceof Fragment) {
            context = ((Fragment) object).getActivity();
        } else if (object instanceof android.support.v4.app.Fragment) {
            context = ((android.support.v4.app.Fragment) object).getActivity();
        }
        if (context == null || permission == null) {
            return;
        }

        final Context finalContext = context;
        PermissionRequestActivity.PermissionRequest(context, permission.value(), permission.requestCode(), new PermissionListener() {
            @Override
            public void PermissionGranted() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void PermissionDenied(int requestCode, List<String> denyList) {
                Class<?> currentClass = object.getClass();
                Method[] methods = currentClass.getDeclaredMethods();
                if (methods == null || methods.length == 0) {
                    return;
                }
                boolean isPermissionDeniedAnnotation = false;
                for (Method method : methods) {
                    isPermissionDeniedAnnotation = method.isAnnotationPresent(PermissionDenied.class);
                    if (isPermissionDeniedAnnotation) {
                        method.setAccessible(true);//允许通过反射访问字段
                        PermissionDenied permissionDenied = method.getAnnotation(PermissionDenied.class);
                        if (permissionDenied == null) {
                            return;
                        }
                        String[] value = permissionDenied.value();
                        String valueItem = (value != null && value.length > 0) ? (requestCode <= value.length - 1 ? value[requestCode] : "") : "";
                        boolean isDefault = permissionDenied.isDefaultDialog();
                        handlerOption(isDefault,
                                TextUtils.isEmpty(valueItem) ? StringTipUtils.getPermissionTip(denyList) : valueItem,
                                finalContext,
                                object,
                                method,
                                requestCode);
                        break;
                    }
                }
                if (!isPermissionDeniedAnnotation) {
                    DialogUtils.showDialog(finalContext, StringTipUtils.getPermissionTip(denyList), requestCode);
                }
            }

            @Override
            public void PermissionCanceled(int requestCode, List<String> permissions) {
                Class<?> currentClass = object.getClass();
                Method[] methods = currentClass.getDeclaredMethods();
                if (methods == null || methods.length == 0) {
                    return;
                }
                boolean isPermissionCancelAnnotation = false;
                for (Method method : methods) {
                    isPermissionCancelAnnotation = method.isAnnotationPresent(PermissionCancel.class);
                    if (isPermissionCancelAnnotation) {
                        method.setAccessible(true);//允许通过反射访问字段
                        PermissionCancel permissionCancel = method.getAnnotation(PermissionCancel.class);
                        if (permissionCancel == null) {
                            return;
                        }
                        String[] value = permissionCancel.value();
                        String valueItem = (value != null && value.length > 0) ? (requestCode < value.length - 1 ? value[requestCode] : "") : "";
                        boolean isDefault = permissionCancel.isDefaultDialog();
                        handlerOption(isDefault,
                                TextUtils.isEmpty(valueItem) ? StringTipUtils.getPermissionTip(permissions) : valueItem,
                                finalContext,
                                object,
                                method,
                                requestCode);
                        break;
                    }
                }
                if (!isPermissionCancelAnnotation) {
                    DialogUtils.showDialog(finalContext, StringTipUtils.getPermissionTip(permissions), requestCode);
                }
            }
        });
    }

    /**
     * 处理逻辑
     *
     * @param isDefault
     * @param msg
     * @param finalContext
     * @param object
     * @param method
     * @param requestCode
     */
    private void handlerOption(boolean isDefault, String msg, Context finalContext, Object object, Method method, int requestCode) {
        try {
            if (isDefault) {
                DialogUtils.showDialog(finalContext, msg, requestCode);
            }
            Class<?>[] types = method.getParameterTypes();
            //方法没有参数时
            if (types == null || types.length == 0) {
                method.invoke(object);
            } else {
                method.invoke(object, requestCode);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
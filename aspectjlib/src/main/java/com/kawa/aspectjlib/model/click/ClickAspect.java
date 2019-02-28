package com.kawa.aspectjlib.model.click;

import android.util.Log;

import com.kawa.aspectjlib.utils.ClickUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/****
 * <pre>
 *  Created:         Kawa on 2018/5/22 11:29.
 *  E-mail:          958129971@qq.com
 *  Desc:            点击事件的AOP
 * </pre> 
 ****/
@Aspect
public class ClickAspect {

    private boolean isEnableFastOnClick = false;

    @Before("execution(@com.kawa.aspectjlib.annotation.EnableFastOnClick * *..*.*(..))")
    public void enableFastOnClick(JoinPoint joinPoint) throws Throwable {
        isEnableFastOnClick = true;
    }

    @Around("execution(* android.view.View.OnClickListener.onClick(..)) " +
            "|| execution(* android.widget.AdapterView.OnItemClickListener.onItemClick(..))")
    public void onClickListener(ProceedingJoinPoint joinPoint) throws Throwable {
        if (ClickUtils.isEnableStartActivity()) {
            isEnableFastOnClick = false;
            joinPoint.proceed();
            Log.d("ClickAspect", "normal click!");
        } else {
            if (isEnableFastOnClick) {
                joinPoint.proceed();
                Log.d("ClickAspect", "already fast click!");
            }else{
                Log.d("ClickAspect", "The clicks are too fast!");
            }
        }
    }
}

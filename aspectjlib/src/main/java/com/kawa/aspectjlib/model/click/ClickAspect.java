package com.kawa.aspectjlib.model.click;

import android.util.Log;

import com.kawa.aspectjlib.utils.ClickUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/****
 * <pre>
 *  Created:         Kawa on 2018/5/22 11:29.
 *  E-mail:          958129971@qq.com
 *  Desc:            点击事件的AOP
 * </pre> 
 ****/
@Aspect
public class ClickAspect {

    @Around("execution(* android.view.View.OnClickListener.onClick(..)) " +
            "|| execution(* android.widget.AdapterView.OnItemClickListener.onItemClick(..))")
    public void onClickListener(ProceedingJoinPoint joinPoint) throws Throwable {
        if (ClickUtils.isEnableStartActivity()) {
            joinPoint.proceed();
            Log.d("ClickAspect", "normal click!");
        } else {
            Log.d("ClickAspect", "The clicks are too fast!");
        }
    }
}

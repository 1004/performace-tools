package com.performance.tools.collect.aop.activity;

import android.app.Activity;
import android.text.TextUtils;
import com.performance.tools.utils.PerformaceLogUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 创建时间: 2019/04/25 10:11 <br>
 * 作者: xiekongying001 <br>
 * 描述:activity切面
 */
@Aspect
public class ActivityCut {
  private final String Model = "ActivityCut";
  @Pointcut("execution(* android.app.Activity.on**(..))")
  public void activityOnXXX(){

  }

  @Around("activityOnXXX()")
  public Object activityOnXXXOperate(ProceedingJoinPoint proceedingJoinPoint){
    Object result = null;
    try {
      Object target = proceedingJoinPoint.getTarget();
      if (target == null || !(target instanceof Activity)){
        PerformaceLogUtils.e(Model,"无效的activity");
        return null;
      }
      Activity activity = (Activity) target;
      long startTime = System.currentTimeMillis();
      result = proceedingJoinPoint.proceed();
      String activityName = activity.getClass().getCanonicalName();
      Signature signature = proceedingJoinPoint.getSignature();
      String  sign = "";
      String methodName = "";
      if (signature != null){
        sign = signature.toString();
        methodName = signature.getName();
      }
      if (!TextUtils.isEmpty(activityName) && !TextUtils.isEmpty(sign) && sign.contains(activityName)){
          PerformaceLogUtils.i(Model,"开始切入");
      }

    }catch (Throwable e){
      e.printStackTrace();
    }
    return result;
  }


}

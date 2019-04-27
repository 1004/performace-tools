package com.performance.tools.collect.hook.activity;

import android.app.Instrumentation;
import com.performance.tools.utils.PerformaceLogUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 创建时间: 2019/04/26 12:24 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class InstrumentationHooker {

  public static void doHook(){
    try {
      PerformaceLogUtils.i("InstrumentationHooker","doHook");
      hookInstrumentation();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void hookInstrumentation() throws ClassNotFoundException, NoSuchMethodException,
      InvocationTargetException, IllegalAccessException, NoSuchFieldException {
    Class<?> c = Class.forName("android.app.ActivityThread");
    Method currentActivityThread = c.getDeclaredMethod("currentActivityThread");
    boolean acc = currentActivityThread.isAccessible();
    if (!acc) {
      currentActivityThread.setAccessible(true);
    }
    Object o = currentActivityThread.invoke(null);
    if (!acc) {
      currentActivityThread.setAccessible(acc);
    }
    Field f = c.getDeclaredField("mInstrumentation");
    acc = f.isAccessible();
    if (!acc) {
      f.setAccessible(true);
    }
    Instrumentation currentInstrumentation = (Instrumentation) f.get(o);
    Instrumentation ins = new PerformanceInstrumentation(currentInstrumentation);
    f.set(o, ins);
    if (!acc) {
      f.setAccessible(acc);
    }
  }
}

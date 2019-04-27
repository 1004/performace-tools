package com.performance.tools.utils;

import android.util.Log;
import com.performance.tools.config.global.Env;

/**
 * 创建时间: 2019/04/25 10:20 <br>
 * 作者: xiekongying001 <br>
 * 描述:日志类
 */
public class PerformaceLogUtils {
  private static final String LOG_FORMAT = "【%s】:%s";
  public static void i(String model,String msg){
    if (Env.DEBUG){
      Log.i(Env.TAG,String.format(LOG_FORMAT,model,msg));
    }
  }
  public static void d(String model,String msg){
    if (Env.DEBUG){
      Log.d(Env.TAG,String.format(LOG_FORMAT,model,msg));
    }
  }

  public static void e(String model,String msg){
    if (Env.DEBUG){
      Log.e(Env.TAG,String.format(LOG_FORMAT,model,msg));
    }
  }

  public static void w(String model,String msg){
    if (Env.DEBUG){
      Log.w(Env.TAG,String.format(LOG_FORMAT,model,msg));
    }
  }

}

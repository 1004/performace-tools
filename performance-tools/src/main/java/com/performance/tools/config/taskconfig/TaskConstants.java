package com.performance.tools.config.taskconfig;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建时间: 2019/04/25 10:50 <br>
 * 作者: xiekongying001 <br>
 * 描述:核心性能模块
 */
public class TaskConstants {

  public static final String TASK_ACTIVITY = "activity";
  public static final String TASK_FPS = "fps";
  public static final String TASK_MEMORY = "memory";
  public static final String TASK_BLOCK = "block";
  public static final String TASK_APP_START = "appstart";

  @StringDef({TASK_ACTIVITY,TASK_FPS,TASK_MEMORY,TASK_BLOCK,TASK_APP_START})
  @Retention(RetentionPolicy.SOURCE)
  public @interface TaskName{}


}

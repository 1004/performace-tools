package com.performance.tools.loader;

import android.app.Application;
import com.performance.tools.config.taskconfig.TaskConfig;

/**
 * 创建时间: 2019/04/25 10:48 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class PerformaceClient {

  /**
   * 初始化性能分析SDK
   * @param application
   * @param config
   */
  public static void initPerformanceTools(Application application,TaskConfig config){
    PerformanceManager.getInstance().init(application,config);
  }



}

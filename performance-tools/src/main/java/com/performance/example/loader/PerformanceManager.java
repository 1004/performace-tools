package com.performance.example.loader;

import android.app.Application;
import com.performance.example.config.ConfigManager;
import com.performance.example.config.taskconfig.TaskConfig;
import com.performance.example.core.TaskManager;
import com.performance.example.parser.ParseManager;
import com.performance.example.utils.PerformaceLogUtils;

/**
 * 创建时间: 2019/04/25 16:26 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class PerformanceManager {
  private static final PerformanceManager ourInstance = new PerformanceManager();
  private boolean isInit = false;
  public static PerformanceManager getInstance() {
    return ourInstance;
  }

  private PerformanceManager() {
  }

  /**
   * sdk 初始化
   * @param application
   * @param config
   */
  public void init(Application application,TaskConfig config){
    if (isInit){
      PerformaceLogUtils.i("PerformanceManager","has init");
      return;
    }
    isInit = true;
    ConfigManager.getInstance().init(application,config);
    ParseManager.getInstance().initParser();
    TaskManager.getInstance().init();
  }

}

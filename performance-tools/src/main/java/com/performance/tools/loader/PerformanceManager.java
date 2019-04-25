package com.performance.tools.loader;

/**
 * 创建时间: 2019/04/25 16:26 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class PerformanceManager {
  private static final PerformanceManager ourInstance = new PerformanceManager();

  public static PerformanceManager getInstance() {
    return ourInstance;
  }

  private PerformanceManager() {
  }
}

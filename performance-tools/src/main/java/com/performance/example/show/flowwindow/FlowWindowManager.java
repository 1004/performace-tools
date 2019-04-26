package com.performance.example.show.flowwindow;

/**
 * 创建时间: 2019/04/26 15:22 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class FlowWindowManager {
  private static final FlowWindowManager ourInstance = new FlowWindowManager();

  public static FlowWindowManager getInstance() {
    return ourInstance;
  }

  private FlowWindowManager() {
  }
}

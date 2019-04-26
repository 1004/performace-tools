package com.performance.example.core.base;

import com.performance.example.parser.base.IInfo;

/**
 * 创建时间: 2019/04/25 11:05 <br>
 * 作者: xiekongying001 <br>
 * 描述:核心抽象性能模块task
 */
public interface ITask {
   void setCanWork(boolean isCanWork);
   boolean isCanWork();
   void start();
   void stop();
   String getTaskName();
   void save(IInfo iInfo);
}

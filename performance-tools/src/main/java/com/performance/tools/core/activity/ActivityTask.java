package com.performance.tools.core.activity;

import com.performance.tools.cache.base.IStore;
import com.performance.tools.collect.hook.activity.InstrumentationHooker;
import com.performance.tools.config.ConfigManager;
import com.performance.tools.config.collectconfig.CollectActivityConfig;
import com.performance.tools.config.taskconfig.TaskConstants;
import com.performance.tools.core.base.ABaseTask;
import com.performance.tools.utils.PerformaceLogUtils;

/**
 * 创建时间: 2019/04/25 18:23 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class ActivityTask extends ABaseTask {
  @Override
  protected IStore initStore() {
    return null;
  }

  @Override
  public void start() {
    PerformaceLogUtils.i("ActivityTask","start");
    if (isCanWork && ConfigManager.getInstance().isTaskEnable(getTaskName())){
      if (!CollectActivityConfig.isActivityCollectAOP){
        //启动hook操作
        InstrumentationHooker.doHook();
      }
    }
  }

  @Override
  public void stop() {
    PerformaceLogUtils.i("ActivityTask","stop");
  }

  @Override
  public String getTaskName() {
    return TaskConstants.TASK_ACTIVITY;
  }
}

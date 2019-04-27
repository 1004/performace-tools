package com.performance.tools.core.activity;

import android.app.Activity;
import com.performance.tools.config.collectconfig.CollectActivityConfig;
import com.performance.tools.config.taskconfig.TaskConstants;
import com.performance.tools.core.TaskManager;

import static com.performance.tools.config.collectconfig.CollectActivityConfig.TYPE_ACTIVITY_FIRSTFRAME_STATE;
import static com.performance.tools.config.collectconfig.CollectActivityConfig.TYPE_ACTIVITY_ONDESTORY_STATE;

/**
 * 创建时间: 2019/04/25 10:45 <br>
 * 作者: xiekongying001 <br>
 * 描述:界面aop处理器 分发
 */
public class ActivityAopOperater {

  public static void invoke(Activity activity, long startTime, String lifeCycle) {
    boolean isAopTaskRuning = isActivityRuning();
    if (!isAopTaskRuning){
      return;
    }
    //严格控制生命周期
    int lifeState = CollectActivityConfig.lifeCycleState(lifeCycle);
    if (lifeState<TYPE_ACTIVITY_FIRSTFRAME_STATE  || lifeState>TYPE_ACTIVITY_ONDESTORY_STATE){
      return;
    }
    ActivityCore.onLifeCycleOperate(activity,startTime,lifeCycle);
  }

  public static boolean isActivityRuning() {
    return CollectActivityConfig.isActivityCollectAOP && TaskManager.getInstance().taskIsCanWork(
        TaskConstants.TASK_ACTIVITY);
  }
}

package com.performance.example.core.activity;

import android.app.Activity;
import android.text.TextUtils;
import com.performance.example.config.collectconfig.CollectActivityConfig;
import com.performance.example.config.taskconfig.TaskConstants;
import com.performance.example.core.TaskManager;
import com.performance.example.core.base.ITask;
import com.performance.example.parser.ParseManager;
import com.performance.example.parser.base.IParser;
import com.performance.example.utils.PerformaceLogUtils;

/**
 * 创建时间: 2019/04/25 16:39 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class ActivityCore {
  public static final String Model = "ActivityCore";
  public static boolean isFirst = true;//是否是第一次启动
  public static int startType;//启动类型

  public static void onLifeCycleOperate(Activity activity, long startTime, String lifeCycle) {
    if (TextUtils.equals(lifeCycle, CollectActivityConfig.TYPE_ACTIVITY_ONCREATE)){
     startType = isFirst ? CollectActivityConfig.COLD_START : CollectActivityConfig.HOT_START;
     activity.getWindow().getDecorView().post(new FirstFrameRunnable(activity,startType,startTime));
    }
    long curTime = System.currentTimeMillis();
    generateActivityInfo(activity,curTime-startTime,lifeCycle);
  }


  private static void generateActivityInfo(Activity activity, long time, String lifeCycle) {
    if(activity == null){
      PerformaceLogUtils.e(Model,"generateActivityInfo activity is null");
      return;
    }
    ActivityInfo activityInfo = new ActivityInfo();
    activityInfo.activityName = activity.getClass().getCanonicalName();
    activityInfo.lifeCycleName = lifeCycle;
    activityInfo.lifeCycleState = CollectActivityConfig.lifeCycleState(lifeCycle);
    activityInfo.startType = startType;
    activityInfo.time = time;
    ITask iTask = TaskManager.getInstance().getTask(TaskConstants.TASK_ACTIVITY);
    if (iTask != null){
      iTask.save(activityInfo);
    }
    IParser parser = ParseManager.getInstance().getParser(TaskConstants.TASK_ACTIVITY);
    if (parser != null){
      parser.parse(activityInfo);
    }
  }

  //首屏加载firstframe
 private static class FirstFrameRunnable implements Runnable{
    private Activity activity;//Activity
    private int startType;//启动类型
    private long startTime;//开始时间

    public FirstFrameRunnable(Activity activity, int startType, long startTime) {
      this.startTime = startTime;
      this.activity = activity;
      this.startType = startType;
    }

    @Override
    public void run() {
      //首帧
      if (System.currentTimeMillis()-startTime >= CollectActivityConfig.FIRST_ACTIVITY_FRAME_MIN_TIME){
        generateActivityInfo(activity,System.currentTimeMillis()-startTime,CollectActivityConfig.TYPE_ACTIVITY_FIRSTFRAME);
      }
      if (isFirst){
        isFirst = false;
        operateAppStart(activity);
      }

    }

    //app 首次启动的统计
    private void operateAppStart(Activity activity) {

    }
  }
}

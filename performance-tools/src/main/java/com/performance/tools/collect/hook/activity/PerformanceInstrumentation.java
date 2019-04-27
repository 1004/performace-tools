package com.performance.tools.collect.hook.activity;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.os.Bundle;
import com.performance.tools.config.collectconfig.CollectActivityConfig;
import com.performance.tools.config.taskconfig.TaskConstants;
import com.performance.tools.core.TaskManager;
import com.performance.tools.core.activity.ActivityCore;

/**
 * 创建时间: 2019/04/26 12:14 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class PerformanceInstrumentation extends Instrumentation{
  private Instrumentation mOldInstrumentatin;

  public PerformanceInstrumentation(Instrumentation instrumentation){
    this.mOldInstrumentatin = instrumentation;
  }

  @Override
  public void callApplicationOnCreate(Application app) {
    if (this.mOldInstrumentatin != null){
      this.mOldInstrumentatin.callApplicationOnCreate(app);
    }else {
      super.callApplicationOnCreate(app);
    }
  }

  @Override
  public void callActivityOnCreate(Activity activity, Bundle icicle) {
    long startTime = System.currentTimeMillis();
    if (this.mOldInstrumentatin != null){
      this.mOldInstrumentatin.callActivityOnCreate(activity,icicle);
    }else {
      super.callActivityOnCreate(activity, icicle);
    }
    if (isTaskRunning()){
      ActivityCore.onLifeCycleOperate(activity,startTime,CollectActivityConfig.TYPE_ACTIVITY_ONCREATE);
    }
  }

  @Override
  public void callActivityOnStart(Activity activity) {
    long startTime = System.currentTimeMillis();
    if (mOldInstrumentatin != null){
      mOldInstrumentatin.callActivityOnStart(activity);
    }else {
      super.callActivityOnStart(activity);
    }
    if (isTaskRunning()){
      ActivityCore.onLifeCycleOperate(activity,startTime,CollectActivityConfig.TYPE_ACTIVITY_ONSTARET);
    }
  }

  @Override
  public void callActivityOnResume(Activity activity) {
    long startTime = System.currentTimeMillis();
    if (mOldInstrumentatin != null){
      mOldInstrumentatin.callActivityOnResume(activity);
    }else {
      super.callActivityOnResume(activity);
    }
    if (isTaskRunning()){
      ActivityCore.onLifeCycleOperate(activity,startTime,CollectActivityConfig.TYPE_ACTIVITY_ONRESUME);
    }
  }

  @Override
  public void callActivityOnStop(Activity activity) {
    long startTime = System.currentTimeMillis();
    if (mOldInstrumentatin != null){
      mOldInstrumentatin.callActivityOnStop(activity);
    }else {
      super.callActivityOnStop(activity);
    }
    if (isTaskRunning()){
      ActivityCore.onLifeCycleOperate(activity,startTime,CollectActivityConfig.TYPE_ACTIVITY_ONSTOP);
    }
  }

  @Override
  public void callActivityOnPause(Activity activity) {
    long startTime = System.currentTimeMillis();
    if (mOldInstrumentatin != null){
      mOldInstrumentatin.callActivityOnPause(activity);
    }else {
      super.callActivityOnPause(activity);
    }
    if (isTaskRunning()){
      ActivityCore.onLifeCycleOperate(activity,startTime,CollectActivityConfig.TYPE_ACTIVITY_ONPAUSE);
    }
  }

  @Override
  public void callActivityOnDestroy(Activity activity) {
    long startTime = System.currentTimeMillis();
    if (mOldInstrumentatin != null){
      mOldInstrumentatin.callActivityOnDestroy(activity);
    }else {
      super.callActivityOnDestroy(activity);
    }
    if (isTaskRunning()){
      ActivityCore.onLifeCycleOperate(activity,startTime,CollectActivityConfig.TYPE_ACTIVITY_ONDESTORY);
    }
  }

  private boolean isTaskRunning(){
    return TaskManager.getInstance().taskIsCanWork(TaskConstants.TASK_ACTIVITY) && !CollectActivityConfig.isActivityCollectAOP;
  }

}

package com.performance.tools.config;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.performance.tools.config.taskconfig.TaskConstants;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2019/04/25 11:07 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class ConfigManager {
  private static final ConfigManager ourInstance = new ConfigManager();
  private Map<String,Boolean> mTaskEnable;
  private Application mApplication;

  public static ConfigManager getInstance() {
    return ourInstance;
  }

  private ConfigManager() {
    mTaskEnable = new HashMap<>();
  }

  public void init(Application application){
    this.mApplication = application;
    initTaskEnable();
  }

  public Context getContext(){
    if (this.mApplication != null){
      return this.mApplication.getApplicationContext();
    }
    return null;
  }

  public void initTaskEnable(){
    if (mTaskEnable == null){
      return;
    }
    mTaskEnable.put(TaskConstants.TASK_ACTIVITY,true);
    mTaskEnable.put(TaskConstants.TASK_FPS,true);
    mTaskEnable.put(TaskConstants.TASK_MEMORY,true);
    mTaskEnable.put(TaskConstants.TASK_BLOCK,true);
    mTaskEnable.put(TaskConstants.TASK_APP_START,true);
  }

  public boolean isTaskEnable(@TaskConstants.TaskName String taskName){
    if (TextUtils.isEmpty(taskName) || mTaskEnable == null || !mTaskEnable.containsKey(taskName)){
      return false;
    }
    return mTaskEnable.get(taskName);
  }




}

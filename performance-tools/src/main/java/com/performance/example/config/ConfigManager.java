package com.performance.example.config;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.performance.example.config.taskconfig.TaskConfig;
import com.performance.example.config.taskconfig.TaskConstants;
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

  public void init(Application application,TaskConfig config){
    this.mApplication = application;
    initTaskEnable(config);
  }

  public Context getContext(){
    if (this.mApplication != null){
      return this.mApplication.getApplicationContext();
    }
    return null;
  }

  public void initTaskEnable(TaskConfig config){
    if (mTaskEnable == null){
      return;
    }
    mTaskEnable.put(TaskConstants.TASK_ACTIVITY,config != null ? config.isActivityEnable() : true);
    mTaskEnable.put(TaskConstants.TASK_FPS,config != null ? config.isFpsEnable() : true);
    mTaskEnable.put(TaskConstants.TASK_MEMORY,config != null ? config.isMemoryEnable() : true);
    mTaskEnable.put(TaskConstants.TASK_BLOCK,config != null ? config.isBlockEnable() : true);
    mTaskEnable.put(TaskConstants.TASK_APP_START,config != null ? config.isAppStart() : true);
  }

  public boolean isTaskEnable(@TaskConstants.TaskName String taskName){
    if (TextUtils.isEmpty(taskName) || mTaskEnable == null || !mTaskEnable.containsKey(taskName)){
      return false;
    }
    return mTaskEnable.get(taskName);
  }




}

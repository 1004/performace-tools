package com.performance.tools.core;

import android.app.Application;
import android.text.TextUtils;
import com.performance.tools.config.ConfigManager;
import com.performance.tools.config.taskconfig.TaskConstants;
import com.performance.tools.core.base.ITask;
import com.performance.tools.utils.PerformaceLogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建时间: 2019/04/25 11:06 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class TaskManager {
  private static final String Model = "TaskManager";
  private static final TaskManager ourInstance = new TaskManager();
  private Map<String,ITask> mAllTaskMap;

  public static TaskManager getInstance() {
    return ourInstance;
  }

  private TaskManager() {
    mAllTaskMap = new HashMap<>();
  }

  public List<ITask> getAllTask(){
    List<ITask> taskList = new ArrayList<ITask>();
    if (null == mAllTaskMap) return taskList;
    for (Map.Entry<String, ITask> entry : mAllTaskMap.entrySet()) {
      taskList.add(entry.getValue());
    }
    return taskList;
  }

  public ITask getTask(@TaskConstants.TaskName String taskName){
    if (TextUtils.isEmpty(taskName)){
      return null;
    }
    return mAllTaskMap == null ? null : mAllTaskMap.get(taskName);
  }

  /**
   * 更新task开关
   * @param taskName
   * @param isCanWork
   */
  public void updataTaskSwitchByTaskName(@TaskConstants.TaskName String taskName,boolean isCanWork){
    if (TextUtils.isEmpty(taskName) || mAllTaskMap == null || mAllTaskMap.get(taskName)==null){
      return;
    }
    if (ConfigManager.getInstance().isTaskEnable(taskName)){
      mAllTaskMap.get(taskName).setCanWork(isCanWork);
    }else {
      mAllTaskMap.get(taskName).setCanWork(false);
    }

  }

  public boolean taskIsCanWork(@TaskConstants.TaskName String taskName){
    if (TextUtils.isEmpty(taskName) || mAllTaskMap == null || mAllTaskMap.get(taskName) == null){
      return false;
    }
    return mAllTaskMap.get(taskName).isCanWork();
  }

  /**
   * 启动所有可以启动
   */
  public void startWorkTasks(){
    if (mAllTaskMap == null || mAllTaskMap.size() ==0){
      PerformaceLogUtils.w(Model,"task map is null");
      return;
    }
    for (ITask task : getAllTask()){
      if (task.isCanWork()){
        PerformaceLogUtils.i(Model,"start-task:"+task.getTaskName());
        task.start();
      }
    }
  }

  public void stopWorkTasks(){
    if (mAllTaskMap == null || mAllTaskMap.size() ==0){
      PerformaceLogUtils.w(Model,"task map is null");
      return;
    }
    for (ITask task : getAllTask()){
      if (task.isCanWork()){
        PerformaceLogUtils.i(Model,"stop-task:"+task.getTaskName());
        task.stop();
      }
    }
  }

  public void registTask(){

  }

}

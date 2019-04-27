package com.performance.tools.config.taskconfig;

/**
 * 创建时间: 2019/04/26 10:27 <br>
 * 作者: xiekongying001 <br>
 * 描述:监控功能开关配置
 */
public class TaskConfig {
  private boolean activityEnable = true;
  private boolean memoryEnable = true;
  private boolean fpsEnable = true;
  private boolean blockEnable = true;
  private boolean appStart = true;



  private TaskConfig(boolean activityEnable, boolean memoryEnable, boolean fpsEnable,
      boolean blockEnable, boolean appStart) {
    this.activityEnable = activityEnable;
    this.memoryEnable = memoryEnable;
    this.fpsEnable = fpsEnable;
    this.blockEnable = blockEnable;
    this.appStart = appStart;
  }

  public boolean isActivityEnable() {
    return activityEnable;
  }

  public boolean isMemoryEnable() {
    return memoryEnable;
  }

  public boolean isFpsEnable() {
    return fpsEnable;
  }

  public boolean isBlockEnable() {
    return blockEnable;
  }

  public boolean isAppStart() {
    return appStart;
  }

  public static class Builder{
     boolean activityEnable = true;
     boolean memoryEnable = true;
     boolean fpsEnable = true;
     boolean blockEnable = true;
     boolean appStart = true;

     public Builder setActivityEnable(boolean activityEnable){
       this.activityEnable = activityEnable;
       return this;
     }

     public Builder setMemoryEnable(boolean memoryEnable){
       this.memoryEnable = memoryEnable;
       return this;
     }

     public Builder setFpsEnable(boolean fpsEnable){
       this.fpsEnable = fpsEnable;
       return this;
     }

     public Builder setBlockEnable(boolean blockEnable){
        this.blockEnable = blockEnable;
        return this;
     }

     public Builder setAppStart(boolean appStart){
       this.appStart = appStart;
       return this;
     }

     public TaskConfig Build(){
      return new TaskConfig(this.activityEnable,this.memoryEnable,this.fpsEnable,this.blockEnable,this.appStart);
    }
  }
}

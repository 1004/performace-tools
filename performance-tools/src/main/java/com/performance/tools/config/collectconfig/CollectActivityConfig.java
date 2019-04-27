package com.performance.tools.config.collectconfig;

/**
 * 创建时间: 2019/04/25 11:00 <br>
 * 作者: xiekongying001 <br>
 * 描述:采集配置类
 */
public class CollectActivityConfig {
  //activiy-application
  public static boolean isActivityCollectAOP = true;//采集方式

  //Activity生命周期函数
  public static final String TYPE_ACTIVITY_FIRSTFRAME= "firstFrame";
  public static final String TYPE_ACTIVITY_ONCREATE = "onCreate";
  public static final String TYPE_ACTIVITY_ONSTARET = "onStart";
  public static final String TYPE_ACTIVITY_ONRESUME= "onResume";
  public static final String TYPE_ACTIVITY_ONSTOP= "onStop";
  public static final String TYPE_ACTIVITY_ONPAUSE= "onPause";
  public static final String TYPE_ACTIVITY_ONDESTORY= "onDestroy";
  public static final String TYPE_ACTIVITY_UNKNOWN= "unKnown";

  //Activity生命周期函数状态
  public static final int TYPE_ACTIVITY_FIRSTFRAME_STATE= 1;
  public static final int TYPE_ACTIVITY_ONCREATE_STATE = TYPE_ACTIVITY_FIRSTFRAME_STATE+1;
  public static final int TYPE_ACTIVITY_ONSTARET_STATE = TYPE_ACTIVITY_ONCREATE_STATE+1;
  public static final int TYPE_ACTIVITY_ONRESUME_STATE= TYPE_ACTIVITY_ONSTARET_STATE+1;
  public static final int TYPE_ACTIVITY_ONPAUSE_STATE= TYPE_ACTIVITY_ONRESUME_STATE+1;
  public static final int TYPE_ACTIVITY_ONSTOP_STATE= TYPE_ACTIVITY_ONPAUSE_STATE+1;
  public static final int TYPE_ACTIVITY_ONDESTORY_STATE= TYPE_ACTIVITY_ONSTOP_STATE+1;


  public static final int COLD_START = 1;//冷启动
  public static final int HOT_START = 2;//热启动

  public static final int FIRST_ACTIVITY_FRAME_MIN_TIME = 300;//Activity生命周期第一帧最小数据收集时间间隔 单位：ms

  public static int lifeCycleState(String lifeStyle){
    int state = -1;
    switch (lifeStyle){
      case TYPE_ACTIVITY_FIRSTFRAME:
        state = TYPE_ACTIVITY_FIRSTFRAME_STATE;
        break;
      case TYPE_ACTIVITY_ONCREATE:
        state = TYPE_ACTIVITY_ONCREATE_STATE;
        break;
      case TYPE_ACTIVITY_ONSTARET:
        state = TYPE_ACTIVITY_ONSTARET_STATE;
        break;
      case TYPE_ACTIVITY_ONRESUME:
        state = TYPE_ACTIVITY_ONRESUME_STATE;
        break;
      case TYPE_ACTIVITY_ONPAUSE:
        state = TYPE_ACTIVITY_ONPAUSE_STATE;
        break;
      case TYPE_ACTIVITY_ONSTOP:
        state = TYPE_ACTIVITY_ONSTOP_STATE;
        break;
      case TYPE_ACTIVITY_ONDESTORY:
        state = TYPE_ACTIVITY_ONDESTORY_STATE;
        break;
    }
    return state;
  }


}

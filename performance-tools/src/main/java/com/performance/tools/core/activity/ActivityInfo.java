package com.performance.tools.core.activity;

import android.content.ContentValues;
import com.performance.tools.parser.base.BaseInfo;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 创建时间: 2019/04/25 17:31 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class ActivityInfo extends BaseInfo{
  private static final String KEY_ACTIVITY_NAME = "activity_name";
  private static final String KEY_START_TYPE = "start_type";
  private static final String KEY_TIME = "time";
  private static final String KEY_LIFE_CYCYLE_NAME = "life_cycle_name";
  private static final String KEY_LIFE_CYCYLE_STATE = "life_cycle_state";

  public String activityName="";
  public int startType = 0;
  public long time=0;
  public String lifeCycleName="";
  public int lifeCycleState=0;

  //用于上传用
  @Override
  public JSONObject toJson() throws JSONException {
    JSONObject ori =  new JSONObject();
    ori.put(KEY_ACTIVITY_NAME,activityName)
        .put(KEY_START_TYPE,startType)
        .put(KEY_TIME,time)
        .put(KEY_LIFE_CYCYLE_NAME,lifeCycleName)
        .put(KEY_LIFE_CYCYLE_STATE,lifeCycleState);
    return ori;
  }

  @Override
  public void parserJson(JSONObject json) throws JSONException {

  }

  //用于存库
  @Override
  public ContentValues toContentValues() {
    return null;
  }
}

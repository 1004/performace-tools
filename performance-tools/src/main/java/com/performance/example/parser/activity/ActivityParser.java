package com.performance.example.parser.activity;

import com.performance.example.core.activity.ActivityInfo;
import com.performance.example.parser.base.IInfo;
import com.performance.example.parser.base.IParser;
import com.performance.example.utils.PerformaceLogUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 创建时间: 2019/04/26 10:25 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class ActivityParser implements IParser {
  @Override
  public void parse(IInfo iInfo) {
    if (iInfo != null && iInfo instanceof ActivityInfo){
      ActivityInfo activityInfo = (ActivityInfo) iInfo;
      try {
        JSONObject jsonObject = iInfo.toJson();
        if (jsonObject != null){
          PerformaceLogUtils.i("ActivityParser","parse"+jsonObject.toString());
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }
}

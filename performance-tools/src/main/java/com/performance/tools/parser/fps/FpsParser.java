package com.performance.tools.parser.fps;

import com.performance.tools.parser.base.IInfo;
import com.performance.tools.parser.base.IParser;
import com.performance.tools.utils.PerformaceLogUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Author xiekongying001
 * @Date 2019/4/27 14:23
 * @Des
 */
public class FpsParser implements IParser {
  @Override
  public void parse(IInfo iInfo) {
    try {
      // fps次数越接近40，用户能感知到卡顿
      // 20以下卡顿更明显
      // 收集40以下的分析
      JSONObject jsonObject = iInfo.toJson();
      if (jsonObject != null){
        PerformaceLogUtils.i("FpsParser","result:"+jsonObject.toString());
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}

package com.performance.tools.core.fps;

import android.content.ContentValues;
import com.performance.tools.parser.base.BaseInfo;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Author xiekongying001
 * @Date 2019/4/27 14:12
 * @Des
 */
public class FpsInfo extends BaseInfo {
  private final static String FPS_RESULT = "fps";
  public int fpsResult;
  @Override
  public JSONObject toJson() throws JSONException {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(FPS_RESULT,fpsResult);
    return jsonObject;
  }

  @Override
  public void parserJson(JSONObject json) throws JSONException {

  }

  @Override
  public ContentValues toContentValues() {
    return null;
  }

}

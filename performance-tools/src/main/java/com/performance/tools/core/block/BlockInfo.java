package com.performance.tools.core.block;

import android.content.ContentValues;
import com.performance.tools.parser.base.BaseInfo;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Author xiekongying001
 * @Date 2019/4/27 16:30
 * @Des
 */
public class BlockInfo extends BaseInfo {
  public static final String BLOCK_STACK = "stack";
  public static final String BLOCK_TIME = "bt";

  public String blockStack;
  public long blockTime;


  @Override
  public JSONObject toJson() throws JSONException {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(BLOCK_STACK,blockStack).put(BLOCK_TIME,blockTime);
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

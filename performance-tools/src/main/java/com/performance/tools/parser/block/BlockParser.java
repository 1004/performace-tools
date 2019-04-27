package com.performance.tools.parser.block;

import com.performance.tools.parser.base.IInfo;
import com.performance.tools.parser.base.IParser;
import com.performance.tools.utils.PerformaceLogUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Author xiekongying001
 * @Date 2019/4/27 16:37
 * @Des
 */
public class BlockParser implements IParser {
  @Override
  public void parse(IInfo iInfo) {
    try {
      JSONObject jsonObject = iInfo.toJson();
      PerformaceLogUtils.i("BlockParser","block:"+jsonObject.toString());
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}

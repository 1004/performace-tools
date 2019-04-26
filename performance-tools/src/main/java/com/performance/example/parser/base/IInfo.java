package com.performance.example.parser.base;

import android.content.ContentValues;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 创建时间: 2019/04/25 11:58 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public interface IInfo {
  JSONObject toJson() throws JSONException;
  void parserJson(JSONObject json) throws JSONException;
  ContentValues toContentValues();
  String getId();
}

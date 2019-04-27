package com.performance.tools.cache.base;

import android.content.ContentValues;
import com.performance.tools.parser.base.IInfo;
import java.util.List;

/**
 * 创建时间: 2019/04/25 18:27 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public interface IStore {
  boolean save(IInfo iInfo);
  boolean delete(String id);
  boolean updata(String id,ContentValues contentValues);
  List<IInfo> queryAll();
  List<IInfo> queryData(int index,int count);
  IInfo get(String id);
  boolean clean();
}

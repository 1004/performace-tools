package com.performance.tools.parser;

import com.performance.tools.config.taskconfig.TaskConstants;
import com.performance.tools.parser.base.IParser;

/**
 * 创建时间: 2019/04/25 11:08 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class ParseManager {
  private static final ParseManager ourInstance = new ParseManager();

  public static ParseManager getInstance() {
    return ourInstance;
  }

  private ParseManager() {

  }

  /**
   * 获取解析器
   * @param taskName
   * @return
   */
  public IParser getParser(@TaskConstants.TaskName String taskName){
    return null;
  }


}

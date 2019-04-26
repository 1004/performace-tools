package com.performance.example.parser;

import com.performance.example.config.taskconfig.TaskConstants;
import com.performance.example.parser.activity.ActivityParser;
import com.performance.example.parser.base.IParser;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2019/04/25 11:08 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class ParseManager {
  private static final ParseManager ourInstance = new ParseManager();
  private Map<String,IParser> mParsers;
  public static ParseManager getInstance() {
    return ourInstance;
  }

  private ParseManager() {
    mParsers = new HashMap<>();
  }

  public void initParser(){
    registParser();
  }
  /**
   * 获取解析器
   * @param taskName
   * @return
   */
  public IParser getParser(@TaskConstants.TaskName String taskName){
    return mParsers.get(taskName);
  }

  private void registParser(){
    mParsers.put(TaskConstants.TASK_ACTIVITY,new ActivityParser());
  }


}

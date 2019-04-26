package com.performance.example.parser.base;

/**
 * 创建时间: 2019/04/25 12:05 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public abstract class BaseInfo implements IInfo{
  public String id;

  @Override
  public String getId() {
    return id;
  }
}

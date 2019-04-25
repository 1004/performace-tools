package com.performance.tools.core.base;

import com.performance.tools.cache.base.IStore;
import com.performance.tools.parser.base.IInfo;
import com.performance.tools.utils.PerformaceLogUtils;

/**
 * 创建时间: 2019/04/25 18:24 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public abstract class ABaseTask implements ITask{
  protected boolean isCanWork;
  protected IStore mStore;

  public ABaseTask() {
    mStore = initStore();
  }

  //初始化缓存引擎
  protected abstract IStore initStore();

  @Override
  public void setCanWork(boolean isCanWork) {
    this.isCanWork = isCanWork;
  }

  @Override
  public boolean isCanWork() {
    return isCanWork;
  }

  @Override
  public void save(IInfo iInfo) {
    PerformaceLogUtils.i("Store","save task:"+getTaskName());
    if (mStore != null && iInfo != null){
      mStore.save(iInfo);
    }
  }
}

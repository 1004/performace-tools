package com.performance.tools.core.fps;

import android.view.Choreographer;
import com.performance.tools.cache.base.IStore;
import com.performance.tools.config.taskconfig.TaskConstants;
import com.performance.tools.core.base.ABaseTask;
import com.performance.tools.parser.ParseManager;
import com.performance.tools.thread.AsyncThreadTask;

/**
 * 1s 刷新频率
 */
public class FpsTask extends ABaseTask implements Choreographer.FrameCallback {
  private long mLastFrameTimeNanos = 0; //最后一次时间
  private long mFrameTimeNanos = 0; //本次的当前时间
  private int mCurrentCount = 0; //当前采集条数
  private int mFpsCount = 0;
  public static final int TASK_DELAY_RANDOM_INTERVAL = 2 * 1000; //定时任务启动随机延迟时间
  public static int DEFAULT_ONCE_MAX_COUNT = 130; //默认高频任务采样，一次最大采样数量
  public static final int FPS_INTERVAL = 1000; //fps采样间隔
  public static int DEFAULT_PAUSE_INTERVAL = 2 * 60 * 60 * 1000; //默认高频任务采样暂停间隔


  @Override
  protected IStore initStore() {
    return null;
  }

  private void calculateFPS() {
    if (mLastFrameTimeNanos == 0) {
      mLastFrameTimeNanos = mFrameTimeNanos;
      return;
    }
    float costTime = (float) (mFrameTimeNanos - mLastFrameTimeNanos) / 1000000.0F;
    if (mFpsCount <= 0 && costTime <= 0.0F) {
      return;
    }
    int fpsResult = (int) (mFpsCount * 1000 / costTime);
    if (fpsResult < 0) {
      return;
    }
    FpsInfo fpsInfo = new FpsInfo();
    fpsInfo.fpsResult = fpsResult;
    save(fpsInfo);
    ParseManager.getInstance().getParser(TaskConstants.TASK_FPS).parse(fpsInfo);
    mLastFrameTimeNanos = mFrameTimeNanos;
    mFpsCount = 0;
  }

  private Runnable runnable = new Runnable() {
    @Override
    public void run() {
      if (!isCanWork()) {
        mCurrentCount = 0;
        return;
      }
      calculateFPS();
      mCurrentCount++;
      //实现分段采集
      if (mCurrentCount < DEFAULT_ONCE_MAX_COUNT) {
        AsyncThreadTask.getInstance().executeRunnableByDelay(runnable,FPS_INTERVAL);
      } else {
        AsyncThreadTask.getInstance().executeRunnableByDelay(runnable, DEFAULT_PAUSE_INTERVAL);
        mCurrentCount = 0;
      }
    }
  };


  @Override
  public void start() {
    AsyncThreadTask.getInstance().executeRunnableByDelay(runnable,Math.round(Math.random()*TASK_DELAY_RANDOM_INTERVAL));
    Choreographer.getInstance().postFrameCallback(this);
  }

  @Override
  public void stop() {

  }

  @Override
  public String getTaskName() {
    return TaskConstants.TASK_FPS;
  }

  @Override
  public void doFrame(long frameTimeNanos) {
    mFpsCount++;
    mFrameTimeNanos = frameTimeNanos;
    if (isCanWork()) {
      //注册下一帧回调
      Choreographer.getInstance().postFrameCallback(this);
    } else {
      mCurrentCount = 0;
    }
  }
}

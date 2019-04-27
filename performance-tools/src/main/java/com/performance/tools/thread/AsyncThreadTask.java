package com.performance.tools.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.NonNull;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author xiekongying001
 * @Date 2019/4/27 13:51
 * @Des
 */
public class AsyncThreadTask {
  private final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
  private final int DEFAULT_COUNT = CPU_COUNT+3;
  private final int KEEP_ALIVE = 3;
  private static final ThreadFactory sThreadFactory = new ThreadFactory() {
    private final AtomicInteger mCount = new AtomicInteger(1);
    @Override
    public Thread newThread(@NonNull Runnable runnable) {
      Thread thread = new Thread(runnable,"AsyncThradTask #"+mCount.getAndIncrement());
      thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);
      return thread;
    }
  };
  private ExecutorService mThreadPool = new ThreadPoolExecutor(CPU_COUNT,DEFAULT_COUNT,KEEP_ALIVE,TimeUnit.SECONDS
  ,new ArrayBlockingQueue<Runnable>(10000)
  ,sThreadFactory
  ,new ThreadPoolExecutor.DiscardOldestPolicy());
  private static final AsyncThreadTask ourInstance = new AsyncThreadTask();

  public static AsyncThreadTask getInstance() {
    return ourInstance;
  }

  private AsyncThreadTask() {

  }


  public void executeRunnable(Runnable runnable){
    mThreadPool.execute(runnable);
  }

  public void  executeRunnableByDelay(final Runnable runnable ,long delayTime){
    getHandler().postDelayed(new Runnable() {
      @Override
      public void run() {
        executeRunnable(runnable);
      }
    },delayTime);
  }

  public void executeRunnableByDelayToUI(Runnable runnable,long delayTime){
    getHandler().postDelayed(runnable,delayTime);
  }


  private Handler mInterHandle = null;
  private Handler getHandler(){
    synchronized (this){
      if (mInterHandle == null){
        mInterHandle = new InternalHandler();
      }
      return mInterHandle;
    }
  }

  private static class InternalHandler extends Handler{
    public InternalHandler(){
      super(Looper.getMainLooper());
    }

    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
    }
  }

}

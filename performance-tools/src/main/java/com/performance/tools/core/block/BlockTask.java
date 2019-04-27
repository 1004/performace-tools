package com.performance.tools.core.block;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Printer;
import com.performance.tools.cache.base.IStore;
import com.performance.tools.config.taskconfig.TaskConstants;
import com.performance.tools.core.base.ABaseTask;
import com.performance.tools.parser.ParseManager;
import com.performance.tools.parser.base.IParser;
import com.performance.tools.thread.AsyncThreadTask;
import com.performance.tools.utils.PerformaceLogUtils;

/**
 * @Author xiekongying001
 * @Date 2019/4/27 16:25
 * @Des
 */
public class BlockTask extends ABaseTask {
  private HandlerThread mBlockThread = new HandlerThread("blockThread");
  private Handler mHandler;
  public static final int DEFAULT_BLOCK_TIME = 4500; // block超时时间
  private long startTime=0L;


  @Override
  protected IStore initStore() {
    return null;
  }

  @Override
  public void start() {
    if (!mBlockThread.isAlive()){
      mBlockThread.start();
      mHandler = new Handler(mBlockThread.getLooper());
      Looper.getMainLooper().setMessageLogging(new Printer() {

        private static final String START = ">>>>> Dispatching";
        private static final String END = "<<<<< Finished";
        @Override
        public void println(String s) {
          if (s.startsWith(START)) {
            startMonitor();
          }
          if (s.startsWith(END)) {
            removeMonitor();
          }

        }
      });
    }
  }

  public void startMonitor() {
    startTime = System.currentTimeMillis();
    mHandler.postDelayed(mBlockRunnable, DEFAULT_BLOCK_TIME);
  }

  public void removeMonitor() {
    long time = System.currentTimeMillis() - startTime;
    if (time>=DEFAULT_BLOCK_TIME){
      PerformaceLogUtils.i("BlockTask", "RemoveTime:" + (System.currentTimeMillis() - startTime));
      BlockInfo blockInfo = new BlockInfo();
      blockInfo.blockTime = time;
      blockInfo.blockStack = "Block真正的时间";
      IParser parser = ParseManager.getInstance().getParser(TaskConstants.TASK_BLOCK);
      if (parser != null){
        parser.parse(blockInfo);
      }
    }
    mHandler.removeCallbacks(mBlockRunnable);
  }

  private Runnable mBlockRunnable = new Runnable() {
    @Override
    public void run() {
      if (!isCanWork()) {
        return;
      }
      StringBuilder sb = new StringBuilder();
      StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
      for (StackTraceElement s : stackTrace) {
        sb.append(s.toString() + "\n");
      }
      saveBlockInfo(sb.toString());
    }
  };

  private void saveBlockInfo(final String s) {
    AsyncThreadTask.getInstance().executeRunnable(new Runnable() {
      @Override
      public void run() {
        BlockInfo blockInfo = new BlockInfo();
        blockInfo.blockStack = s;
        blockInfo.blockTime = DEFAULT_BLOCK_TIME;
        save(blockInfo);
        IParser parser = ParseManager.getInstance().getParser(TaskConstants.TASK_BLOCK);
        if (parser != null){
          parser.parse(blockInfo);
        }
      }
    });
  }

  @Override
  public void stop() {

  }

  @Override
  public String getTaskName() {
    return TaskConstants.TASK_BLOCK;
  }
}

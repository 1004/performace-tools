package test.xky.com.myapplication;

import android.app.Application;
import com.performance.tools.config.taskconfig.TaskConfig;
import com.performance.tools.loader.PerformaceClient;

/**
 * 创建时间: 2019/04/26 16:32 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class MyApplication extends Application{
  @Override
  public void onCreate() {
    super.onCreate();
    PerformaceClient.initPerformanceTools(this,new TaskConfig.Builder().Build());
  }
}

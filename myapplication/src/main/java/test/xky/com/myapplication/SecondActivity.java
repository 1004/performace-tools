package test.xky.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 创建时间: 2019/04/26 19:52 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class SecondActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    findViewById(R.id.startthird).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(SecondActivity.this,ThridActivity.class));
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    SystemClock.sleep(2000);
  }

  @Override
  protected void onPause() {
    super.onPause();
  }
}

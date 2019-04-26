package com.performance.example.show.flowwindow.impl;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.performance.example.R;
import com.performance.example.show.flowwindow.baseflow.AMoveFlowWindow;

/**
 * 创建时间: 2019/04/26 16:14 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class PerformanceIconWindow extends AMoveFlowWindow{
  private View mIconContainer;
  public PerformanceIconWindow(Context context) {
    super(context);
  }

  @Override
  protected View getContentView() {
    View inflate = mInflater.inflate(R.layout.flow_icon_window, null);
    mIconContainer = inflate.findViewById(R.id.flow_icon_contaienr);
    return inflate;
  }

  @Override
  public void onClick(MotionEvent event, View view) {
    super.onClick(event, view);

  }
}

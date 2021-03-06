package com.performance.tools.utils;

import android.content.Context;

/**
 * 创建时间: 2019/04/26 15:09 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class Device {
  /**
   * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
   */
  public static int dip2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }
}

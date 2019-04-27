package com.performance.tools.show.flowwindow.baseflow;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xky on 16/7/1.
 */
public interface IFlowWindow {
    /**
     * 展示
     */
    public void show();

    /**
     * 最后的位置展示
     */
    public void showAtLastLocation();

    /**
     * 在摸个位置展示
     */
    public void showAtLocation(int left, int top);

    /**
     * 关闭
     */
    public void close();

    /**
     * 判断是否展示
     * @return
     */
    public boolean isShowIng();

    /**
     * 初始化位置
     * @param left
     * @param top
     */
    public void initLocation(int left, int top);

    /**
     * 获取悬浮窗最后的位置
     * @return
     */
    public int[] getLocation();

    /**
     * 设置参数
     * @param objects
     */
    public void setParams(Object... objects);

    public void onClick(MotionEvent event, View view);
    public boolean isMoveing();

}

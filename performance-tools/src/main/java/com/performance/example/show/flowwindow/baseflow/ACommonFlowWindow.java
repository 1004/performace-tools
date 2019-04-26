package com.performance.example.show.flowwindow.baseflow;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by xky on 16/7/1.
 * 普通的 不全屏/全屏显示的 不能移动的  可以点击的
 */
public abstract class ACommonFlowWindow extends AFlowWindow {

    public ACommonFlowWindow(Context context) {
        super(context);
    }

    @Override
    protected void operateSelfparams(WindowManager.LayoutParams layoutParams) {
        if (isMatchParent()){
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        }else {
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        }
    }

    protected abstract boolean isMatchParent();

}

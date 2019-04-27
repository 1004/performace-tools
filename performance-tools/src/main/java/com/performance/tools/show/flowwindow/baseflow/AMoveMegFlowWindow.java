package com.performance.tools.show.flowwindow.baseflow;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.performance.tools.utils.Device;

/**
 * Created by xky on 16/7/1.
 * 可以移动的  不一定靠边显示 含有内容  宽度固定
 */
public abstract class AMoveMegFlowWindow extends AFlowWindow {
    public AMoveMegFlowWindow(Context context) {
        super(context);
    }

    @Override
    protected View.OnTouchListener getOnTouchuListener() {
         View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            int x;
            int y;
             int firstx;
             int firsty;
            long time;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(canInterruptTouch(event,v)){
                    return true;
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = (int) event.getRawX();
                        y = (int) event.getRawY();
                        firstx = x;
                        firsty = y;
                        time = System.currentTimeMillis();
                        updataView(MotionEvent.ACTION_DOWN);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - x;
                        int dy = (int) event.getRawY() - y;
                        layoutParams.x = layoutParams.x + dx;
                        layoutParams.y = layoutParams.y + dy;
                        updataView(MotionEvent.ACTION_MOVE);
                        x = (int) event.getRawX();
                        y = (int) event.getRawY();
                        isMoveing = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        if(System.currentTimeMillis() - time < 300){
                            if (Math.abs(firstx-x)<= Device.dip2px(mContext, 6) && Math.abs(firsty-y)<=Device.dip2px(mContext, 6) ){
                                onClick(event,mContainerView);
                            }
                        }
                        x = (int) event.getRawX();
                        y = (int) event.getRawY();
                        int width = mWM.getDefaultDisplay().getWidth();
                        int height = mWM.getDefaultDisplay().getHeight();
                        int mDx = 0;
                        int mDy = 0;
                        if (x > width / 2) {
                            mDx = width - x;
                        } else {
                            mDx = x;
                        }
                        if (y > height / 2) {
                            mDy = height - y;
                        } else {
                            mDy = y;
                        }
                        if(mContainerView != null && isShowing ){
                            mWM.updateViewLayout(mContainerView, layoutParams);
                        }
                        updataView(MotionEvent.ACTION_UP);
                        break;
                }
                return false;
            }
        };
        return onTouchListener;
    }

    @Override
    protected void operateSelfparams(WindowManager.LayoutParams layoutParams) {
        int height,width;
        if (mWM.getDefaultDisplay().getHeight() > mWM.getDefaultDisplay().getWidth() ){
            height = mWM.getDefaultDisplay().getHeight();
            width = mWM.getDefaultDisplay().getWidth();
        }else {
            height = mWM.getDefaultDisplay().getWidth();
            width = mWM.getDefaultDisplay().getHeight();
        }
        if (getHeighPercent() != 1){
            layoutParams.height = (int)(width * getHeighPercent());
        }
        if (getWidthPercent() != -1){
            layoutParams.width = (int)(width * getWidthPercent());
        }
    }

    protected boolean canInterruptTouch(MotionEvent event, View v) {
        return false;
    }


    public float getHeighPercent(){
        return 1;
    }

    public float getWidthPercent(){
        return 1;
    }
}

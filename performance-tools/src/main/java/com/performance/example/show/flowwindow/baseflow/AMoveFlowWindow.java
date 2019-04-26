package com.performance.example.show.flowwindow.baseflow;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xky on 16/7/1.
 * 可以移动的 并且自动靠边显示 可以点击
 */
public abstract class AMoveFlowWindow extends AFlowWindow {

    public AMoveFlowWindow(Context context) {
        super(context);
    }
    @Override
    protected View.OnTouchListener getOnTouchuListener() {
         View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            int x;
            int y;
            long time;
             int firstx;
             int firsty;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = (int) event.getRawX();
                        y = (int) event.getRawY();
                        firstx = x;
                        firsty = y;
                        time = System.currentTimeMillis();
//                        Log.i("test","------ACTION_DOWN------");
                        updataView(MotionEvent.ACTION_DOWN);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - x;
                        int dy = (int) event.getRawY() - y;
                        layoutParams.x = layoutParams.x + dx;
                        layoutParams.y = layoutParams.y + dy;
//                        Log.i("test","------ACTION_MOVE------");
                        updataView(MotionEvent.ACTION_MOVE);
                        x = (int) event.getRawX();
                        y = (int) event.getRawY();
                        isMoveing = true;
                        break;
                    case MotionEvent.ACTION_UP:
//                        Log.i("test","------ACTION_UP------");
                        x = (int) event.getRawX();
                        y = (int) event.getRawY();
                        if(System.currentTimeMillis() - time < 300){
                            if (Math.abs(firstx-x)<=6 && Math.abs(firsty-y)<=6 ){
                                onClick(event,mContainerView);
                            }
                        }
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
                        if (x > width / 2) {
                            //右边
                            layoutParams.x = width;
                        } else {
                            //坐标
                            layoutParams.x = 0;
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


}

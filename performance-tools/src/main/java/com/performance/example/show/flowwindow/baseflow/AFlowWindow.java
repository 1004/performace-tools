package com.performance.example.show.flowwindow.baseflow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.performance.example.R;
import com.performance.example.utils.OSJudgementUtil;

/**
 * Created by xky on 16/7/1.
 */
public abstract class AFlowWindow implements IFlowWindow {
    protected Context mContext;
    protected WindowManager mWM;
    protected LayoutInflater mInflater;
    protected View mContainerView;
    protected WindowManager.LayoutParams layoutParams;
    protected boolean isShowing = false;
    private int[] location = new int[2];
    protected boolean isMoveing = false;
    protected final int ALPTH_NORMAL_TYPE = 1;
    protected final int ALPTH_VIEW_TYPE = 2;
    protected final int ALPTH_WINDOW_TYPE = 3;
    protected final static float AFTER_ALPHA = 0.7f;
    protected final static float BEFOLOR_ALPHA = 1.0f;
    private final static int DELAY_TIME = 3 * 1000;
    private Handler mAlphaHandler = new Handler();

    public AFlowWindow(Context context) {
        this.mContext = context;
        mWM = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        initView();
        initParams();
        operateSelfparams(this.layoutParams);
    }


    private void initParams() {
        layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.alpha = BEFOLOR_ALPHA;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            if ((OSJudgementUtil.isShowPermission())) {
                layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            }
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
    }

    private void initView() {
        mInflater = LayoutInflater.from(mContext);
        mContainerView = (View) mInflater.inflate(R.layout.flow_container_window, null);
        RelativeLayout container = (RelativeLayout) mContainerView.findViewById(R.id.allcontainer);
        View.OnTouchListener touchuListener = getOnTouchuListener();
        if (touchuListener != null) {
            mContainerView.setOnTouchListener(touchuListener);
        }
        View view = getContentView();
        container.addView(view);
    }

    protected void updataView(int type) {
        switch (type) {
            case MotionEvent.ACTION_DOWN:
                changeWindowAlph(true);
                break;
            case MotionEvent.ACTION_MOVE:
                changeWindowAlph(true);
                break;
            case MotionEvent.ACTION_UP:
                changeWindowAlph(false);
                break;
        }
        try {
            mWM.updateViewLayout(mContainerView, layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeWindowAlph(boolean isLight) {
        mAlphaHandler.removeCallbacks(alphaTask);
        if (layoutParams != null) {
            if (isLight) {
                switch (operateViewAlpha(BEFOLOR_ALPHA)) {
                    case ALPTH_NORMAL_TYPE:
                    case ALPTH_VIEW_TYPE:
                        break;
                    case ALPTH_WINDOW_TYPE:
                        layoutParams.alpha = BEFOLOR_ALPHA;
                        break;
                }
            } else {
            mAlphaHandler.postDelayed(alphaTask, DELAY_TIME);
          }
        }
    }

    Runnable alphaTask = new Runnable() {
        @Override
        public void run() {
            try {
                switch (operateViewAlpha(AFTER_ALPHA)) {
                    case ALPTH_NORMAL_TYPE:
                        return;
                    case ALPTH_VIEW_TYPE:
                        break;
                    case ALPTH_WINDOW_TYPE:
                        layoutParams.alpha = AFTER_ALPHA;
                        break;
                }
                if (isShowIng()) {
                    mWM.updateViewLayout(mContainerView, layoutParams);
                }
            } catch (Throwable t) {

            }
        }
    };

    protected int operateViewAlpha(float alphaValue) {
        return ALPTH_NORMAL_TYPE;
    }

    @Override
    public void show() {
        if (!isShowing) {
            changeWindowAlph(true);
            isShowing = true;
            isMoveing = false;
            try {
                mWM.addView(mContainerView, layoutParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
            changeWindowAlph(false);
        }
    }

    @Override
    public void showAtLastLocation() {
        if (!isShowing) {
            changeWindowAlph(true);
            isShowing = true;
            isMoveing = false;
            layoutParams.x = location[0];
            layoutParams.y = location[1];
            try {
                mWM.addView(mContainerView, layoutParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
            changeWindowAlph(false);
        }
    }

    @Override
    public void showAtLocation(int left, int top) {
        if (!isShowing) {
            changeWindowAlph(true);
            isShowing = true;
            isMoveing = false;
            layoutParams.x = left;
            layoutParams.y = top;
            try {
                mWM.addView(mContainerView, layoutParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
            changeWindowAlph(false);
        }
    }

    @Override
    public void close() {
        try {
            if (isShowing) {
                isShowing = false;
                mContainerView.getLocationOnScreen(location);
                mWM.removeView(mContainerView);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public boolean isShowIng() {
        return isShowing;
    }

    @Override
    public void initLocation(int left, int top) {
        if (left < 0) {
            left = 0;
        }
        if (top < 0) {
            top = 0;
        }
        if (left > mWM.getDefaultDisplay().getWidth()) {
            left = mWM.getDefaultDisplay().getWidth();
        }
        if (top > mWM.getDefaultDisplay().getHeight()) {
            top = mWM.getDefaultDisplay().getHeight();
        }
        layoutParams.x = left;
        layoutParams.y = top;
    }

    @Override
    public void setParams(Object... objects) {

    }

    @Override
    public boolean isMoveing() {
        return isMoveing;
    }

    @Override
    public int[] getLocation() {
        int[] location = new int[2];
        mContainerView.getLocationOnScreen(location);
        return location;
    }


    @Override
    public void onClick(MotionEvent event, View view) {

    }

    /**
     * 操作悬浮窗参数
     *
     * @param layoutParams
     */
    protected void operateSelfparams(WindowManager.LayoutParams layoutParams) {

    }

    /**
     * 获取到悬浮窗内容view
     *
     * @return
     */
    protected abstract View getContentView();

    /**
     * 触摸规则 有子类来确定
     *
     * @return
     */
    protected View.OnTouchListener getOnTouchuListener() {

        return null;
    }
}

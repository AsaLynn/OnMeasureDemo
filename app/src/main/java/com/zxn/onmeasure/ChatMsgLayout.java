package com.zxn.onmeasure;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by zxn on 2018/10/10.
 */
public class ChatMsgLayout extends RelativeLayout {

    private boolean isIntercepted;
    private int mMaxWidth;
    private static final int DEF_MAX_WIDTH = 229;

    public ChatMsgLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ChatMsgLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ChatMsgLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //250-16-5 = 229,
        int width = (int) SystemUtils.dp2px(context, DEF_MAX_WIDTH);
        if (null != attrs) {
            final TypedArray a = context.obtainStyledAttributes(
                    attrs, R.styleable.ChatMsgLayout, defStyleAttr, 0);
            //mMaxWidth = (int) a.getDimension(R.styleable.ChatMsgLayout_maxMsgWidth, DEF_MAX_WIDTH);
            mMaxWidth = a.getDimensionPixelSize(R.styleable.ChatMsgLayout_maxMsgWidth, width);
            a.recycle();
        } else {
            mMaxWidth = width;
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isIntercepted ? isIntercepted : super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getSize(widthMeasureSpec) > mMaxWidth) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, MeasureSpec.getMode(widthMeasureSpec));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setIntercepted(boolean intercepted) {
        isIntercepted = intercepted;
    }

    public void setMaxWidth(int mMaxWidth) {
        this.mMaxWidth = mMaxWidth;
    }
}

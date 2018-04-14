package me.msile.train;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 拖拽view 布局
 */

public class ViewDragFrameLayout extends FrameLayout {

    private ViewDragHelper mDragHelper;
    private boolean isTouchChild;
    private int touchChildX, touchChildY;
    private boolean isMoveChild;
    private View touchChild;
    private OnClickChildListener onClickChildListener;

    public ViewDragFrameLayout(Context context) {
        super(context);
        init();
    }

    public ViewDragFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewDragFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnClickChildListener(OnClickChildListener onClickChildListener) {
        this.onClickChildListener = onClickChildListener;
    }

    private void init() {
        mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - child.getWidth() - leftBound;

                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
                return newLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                final int topBound = getPaddingTop();
                final int bottomBound = getHeight() - child.getHeight() - topBound;

                final int newTop = Math.min(Math.max(top, topBound), bottomBound);
                return newTop;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        touchChild = mDragHelper.findTopChildUnder((int) ev.getX(), (int) ev.getY());
        if (touchChild == null) {
            isTouchChild = false;
            return false;
        } else {
            isTouchChild = true;
        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isTouchChild) {
            return false;
        }
        mDragHelper.processTouchEvent(event);
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                touchChildX = (int) event.getX();
                touchChildY = (int) event.getY();
                isMoveChild = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int moveY = (int) event.getY();
                if (Math.abs(touchChildX - moveX) >= mDragHelper.getTouchSlop() || Math.abs(touchChildY - moveY) >= mDragHelper.getTouchSlop()) {
                    isMoveChild = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!isMoveChild && onClickChildListener != null) {
                    onClickChildListener.onClick(touchChild);
                }
                break;
        }
        return true;
    }

    public interface OnClickChildListener {
        void onClick(View childView);
    }

}

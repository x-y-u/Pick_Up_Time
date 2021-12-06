package com.example.mytest.Common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

public class MyViewPager extends ViewPager {
    private boolean isCanScroll = true;
    public MyViewPager(@NonNull Context context) {
        super(context);
        try {
            Field mScroller=MyViewPager.class.getDeclaredField("mScroller");//反射Viewpager的mScroller字段
            mScroller.setAccessible(true);
            Interpolator sInterpolator = new AccelerateInterpolator();
            MyFixedScroller scroller = new MyFixedScroller(context, sInterpolator);
            mScroller.set(this, scroller);//修改指定对象的Scroller
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        try {
            Field mScroller=MyViewPager.class.getDeclaredField("mScroller");//反射Viewpager的mScroller字段
            mScroller.setAccessible(true);
            Interpolator sInterpolator = new AccelerateInterpolator();
            MyFixedScroller scroller = new MyFixedScroller(context, sInterpolator);
            mScroller.set(this, scroller);//修改指定对象的Scroller
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanScroll) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    public class MyFixedScroller extends Scroller {
        private int mDuration = 1000;  //设置滚动时间为500ms;

        public MyFixedScroller(Context context, Interpolator interpolator,
                               boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        public MyFixedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public MyFixedScroller(Context context) {
            super(context);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }

    public void setIsCanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
}

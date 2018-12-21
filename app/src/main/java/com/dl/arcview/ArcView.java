package com.dl.arcview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author zwl
 * @describe 圆弧
 * @date on 2018/5/16
 */
public class ArcView extends View {
    private int mWidth;
    private int mHeight;
    private int mArcHeight;//自定义的弧形高度
    private int mArcBgColor;//背景颜色
    private Paint mPaint;

    private int drawArcHeight;

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcView);
        mArcHeight = typedArray.getDimensionPixelSize(R.styleable.ArcView_arcHeight, 0);
        mArcBgColor = typedArray.getColor(R.styleable.ArcView_arcBgColor, Color.TRANSPARENT);
        typedArray.recycle();
        initPaint();

    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mArcBgColor);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制弧度区域
        Path path = new Path();
        path.moveTo(0, mArcHeight);
        path.quadTo(mWidth / 2, drawArcHeight - mArcHeight, mWidth, mArcHeight);
        path.close();
        canvas.drawPath(path, mPaint);
        //绘制设定的高度-弧度高度的区域
        Rect rect = new Rect(0, mArcHeight, mWidth, mHeight);
        canvas.drawRect(rect, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        mHeight = Math.max(mArcHeight, mHeight);
        setMeasuredDimension(mWidth, mHeight);
    }

    /**
     * 设置弧度
     *
     * @param scale
     */
    public void setArcHeight(float scale) {
        int height = (int) ((scale) * 2 * mArcHeight);
        this.drawArcHeight = height;
        postInvalidate();
    }
}

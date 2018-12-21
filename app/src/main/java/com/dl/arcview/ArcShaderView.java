package com.dl.arcview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author zwl
 * @describe 圆弧
 * @date on 2018/5/16
 */
public class ArcShaderView extends View {
    private int mWidth;
    private int mHeight;
    private int mArcHeight;//自定义的弧形高度
    private Paint mPaint;

    private int mArcStartBgColor;
    private int mArcEndBgColor;

    public ArcShaderView(Context context) {
        this(context, null);
    }

    public ArcShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcShaderView);
        mArcHeight = typedArray.getDimensionPixelSize(R.styleable.ArcShaderView_arcShaderHeight, 0);
        mArcStartBgColor = typedArray.getColor(R.styleable.ArcShaderView_arcShaderStartBgColor, Color.parseColor("#5C4F61"));
        mArcEndBgColor = typedArray.getColor(R.styleable.ArcShaderView_arcShaderEndBgColor, Color.parseColor("#232930"));
        typedArray.recycle();
        initPaint();

    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Shader mShader = new LinearGradient(0, 0, getWidth(), getHeight(), new int[]{mArcStartBgColor, mArcEndBgColor},
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);

        Rect rect = new Rect(0, 0, mWidth, mHeight - mArcHeight);
        canvas.drawRect(rect, mPaint);

        Path path = new Path();
        path.moveTo(0, mHeight - mArcHeight);
        path.quadTo(mWidth / 2, mHeight+mArcHeight, mWidth, mHeight - mArcHeight);
        canvas.drawPath(path, mPaint);
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
        setMeasuredDimension(mWidth, mHeight);
    }


    /**
     * 设置弧度
     *
     * @param scale
     */
    public void setArcHeight(float scale) {
//        int height = (int) ((scale) * 2 * mArcHeight);
//        this.drawArcHeight = height;
//        postInvalidate();
    }

}

package com.smallcake.guess.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Date: 2020/1/18
 * author: SmallCake
 */
public class GradientColorTextView extends AppCompatTextView {

    private Rect mTextBound = new Rect();
    private int[] colors ={0xFFFF0000, 0xFF5400FF};

    public GradientColorTextView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int mViewWidth = getMeasuredWidth();
        Paint mPaint = getPaint();
        String mTipText = getText().toString();
        mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);
        @SuppressLint("DrawAllocation") LinearGradient mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, colors, null, Shader.TileMode.REPEAT);
        mPaint.setShader(mLinearGradient);
        canvas.drawText(mTipText, getMeasuredWidth() / 2 - mTextBound.width() / 2, getMeasuredHeight() / 2 + mTextBound.height() / 2, mPaint);
    }
}

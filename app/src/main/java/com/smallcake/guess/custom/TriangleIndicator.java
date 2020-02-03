package com.smallcake.guess.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.model.PositionData;

import java.util.List;

/**
 * Date: 2020/1/20
 * author: SmallCake
 */
public class TriangleIndicator extends View implements IPagerIndicator {
    private List<PositionData> mPositionDataList;
    private Paint mPaint;
    private int mLineHeight;
    private int mLineColor;
    private int mTriangleHeight;
    private int mTriangleWidth;
    private boolean mReverse;
    private float mYOffset;

    private Path mPath = new Path();
    private Interpolator mStartInterpolator = new LinearInterpolator();
    private Interpolator mEndInterpolator = new LinearInterpolator();
    private float mAnchorX;
    private RectF mLineRect = new RectF();

    public TriangleIndicator(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mLineHeight = UIUtil.dip2px(context, 3);
        mTriangleWidth = UIUtil.dip2px(context, 14);
        mTriangleHeight = UIUtil.dip2px(context, 8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(mLineColor);
        if (mReverse) {
            canvas.drawRect(0, getHeight() - mYOffset - mTriangleHeight, getWidth(), getHeight() - mYOffset - mTriangleHeight + mLineHeight, mPaint);
        } else {
            canvas.drawRect(mLineRect,mPaint);
        }
        mPath.reset();
        if (mReverse) {
            mPath.moveTo(mAnchorX - mTriangleWidth / 2, getHeight() - mYOffset - mTriangleHeight);
            mPath.lineTo(mAnchorX, getHeight() - mYOffset);
            mPath.lineTo(mAnchorX + mTriangleWidth / 2, getHeight() - mYOffset - mTriangleHeight);
        } else {
            mPath.moveTo(mAnchorX - mTriangleWidth / 2, getHeight() - mYOffset);
            mPath.lineTo(mAnchorX, getHeight() - mTriangleHeight - mYOffset);
            mPath.lineTo(mAnchorX + mTriangleWidth / 2, getHeight() - mYOffset);
        }
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mPositionDataList == null || mPositionDataList.isEmpty()) {
            return;
        }

        // 计算锚点位置
        PositionData current = FragmentContainerHelper.getImitativePositionData(mPositionDataList, position);
        PositionData next = FragmentContainerHelper.getImitativePositionData(mPositionDataList, position + 1);

        float triangleLeftX = current.mLeft + (current.mRight - current.mLeft) / 2;
        float triangleRightX = next.mLeft + (next.mRight - next.mLeft) / 2;

        mAnchorX = triangleLeftX + (triangleRightX - triangleLeftX) * mStartInterpolator.getInterpolation(positionOffset);



        float leftX;
        float nextLeftX;
        float rightX;
        float nextRightX;
            leftX = current.mLeft;
            nextLeftX = next.mLeft;
            rightX = current.mRight;
            nextRightX = next.mRight;

        mLineRect.left = leftX + (nextLeftX - leftX) * mStartInterpolator.getInterpolation(positionOffset);
        mLineRect.right = rightX + (nextRightX - rightX) * mEndInterpolator.getInterpolation(positionOffset);
        mLineRect.top = getHeight() - mLineHeight - mYOffset;
        mLineRect.bottom = getHeight() - mYOffset;

        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPositionDataProvide(List<PositionData> dataList) {
        mPositionDataList = dataList;
    }

    public int getLineHeight() {
        return mLineHeight;
    }

    public void setLineHeight(int lineHeight) {
        mLineHeight = lineHeight;
    }

    public int getLineColor() {
        return mLineColor;
    }

    public void setLineColor(int lineColor) {
        mLineColor = lineColor;
    }

    public int getTriangleHeight() {
        return mTriangleHeight;
    }

    public void setTriangleHeight(int triangleHeight) {
        mTriangleHeight = triangleHeight;
    }

    public int getTriangleWidth() {
        return mTriangleWidth;
    }

    public void setTriangleWidth(int triangleWidth) {
        mTriangleWidth = triangleWidth;
    }

    public Interpolator getStartInterpolator() {
        return mStartInterpolator;
    }

    public void setStartInterpolator(Interpolator startInterpolator) {
        mStartInterpolator = startInterpolator;
        if (mStartInterpolator == null) {
            mStartInterpolator = new LinearInterpolator();
        }
    }

    public boolean isReverse() {
        return mReverse;
    }

    public void setReverse(boolean reverse) {
        mReverse = reverse;
    }

    public float getYOffset() {
        return mYOffset;
    }

    public void setYOffset(float yOffset) {
        mYOffset = yOffset;
    }
}

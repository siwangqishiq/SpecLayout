package com.xinlan.speclayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class SpecbgLayout extends LinearLayout {
    private int mPadding = 50;

    private Context mContext;
    private Paint mPaint = new Paint();
    private Paint mShaderPaint = new Paint();
    private Path mCarvePath;

    private int mCurSelectedIndex = 0;

    public SpecbgLayout(Context context) {
        super(context);
        initView(context);
    }

    public SpecbgLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SpecbgLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SpecbgLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(heightMeasureSpec);
//        int width = MeasureSpec.getSize(heightMeasureSpec);
//        System.out.println("width = " + width + "  mode = " + widthMode);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        renderCurveBg(canvas);
        super.dispatchDraw(canvas);
    }

    private void initView(Context context) {
        mContext = context;
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        //mShaderPaint.setColor(Color.BLACK);
        mShaderPaint.setStyle(Paint.Style.FILL);
        mShaderPaint.setStrokeJoin(Paint.Join.ROUND);
        mShaderPaint.setShadowLayer(20,0,0,Color.parseColor("#11000000"));
        //mShaderPaint.setShadowLayer(40,0,0,Color.YELLOW);

        mCarvePath = new Path();
    }

    private boolean renderCurveBg(Canvas canvas) {
        if (canvas == null)
            return false;

        View curView = null;
        for (int i = 0,N = getChildCount(); i < N; i++) {
            if(i == mCurSelectedIndex){
                curView = getChildAt(i);
                break;
            }
        }//end for i

        //not found selected
        if(curView == null || curView.getMeasuredWidth() == 0 || curView.getMeasuredHeight() == 0)
            return false;

        renderCurveBg(canvas , curView);
        return true;
    }

    private void renderCurveBg(Canvas canvas , View curView){
        if(canvas == null || curView == null)
            return;

        float x1 = getMeasuredWidth();
        float y1 = curView.getTop() - mPadding;

        mCarvePath.reset();
        mCarvePath.moveTo(x1 , y1);
        mCarvePath.lineTo(curView.getLeft() , curView.getTop()+ (curView.getMeasuredHeight()>>1));
        mCarvePath.lineTo(getMeasuredWidth() , curView.getBottom() + mPadding);
        mCarvePath.close();

        canvas.drawPath(mCarvePath , mShaderPaint);
        canvas.drawPath(mCarvePath , mPaint);
    }
}//end class

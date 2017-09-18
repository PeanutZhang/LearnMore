package com.jieyi.learnmore.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by zyh on 2017/9/18.
 */

public class PieChartView extends View {

    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private float mStartangle=0;
    private ArrayList<PieData> mData;
    private int width,height;
    private Paint mpaint;



    public PieChartView(Context context) {
        super(context,null);

    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mpaint = new Paint();
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        Log.e("zyh","onSizeChanged is going--- w-- 》 "+ w+ "  height--> "+h+ " oldW--> "+oldw +" oldH ->"+oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mData == null){
            return;
        }
       float currentStartangle = mStartangle;
        canvas.translate(width/2,height/2);
       Log.e("zyh"," onDraw is going  currentStartangele-->  "+currentStartangle);

        float r = (float) (Math.min(width,height)/2 * 0.8);
        RectF rectF = new RectF(-r,-r,r,r);
        for (int i = 0; i < mData.size(); i++) {

            PieData pieData = mData.get(i);

            if (i==0 ) {
                mpaint.setStyle(Paint.Style.STROKE);
                mpaint.setStrokeWidth(6);
                mpaint.setColor(Color.RED);
                canvas.drawArc(rectF,currentStartangle,155,true,mpaint);
            }else {
                mpaint.setStyle(Paint.Style.FILL);
                mpaint.setColor(pieData.getColor());
                canvas.drawArc(rectF,currentStartangle,pieData.getAngle(),true,mpaint);

            }
            Log.e("zyh","第"+i+"部分开始角度---》 "+currentStartangle);
            currentStartangle += pieData.getAngle();

        }


    }

   public void setStartangle(int mStartangle){
       this.mStartangle = mStartangle;
       invalidate();
   }
   public  void setPieData(ArrayList<PieData> mData){
       this.mData = mData;
       initData(mData);
       invalidate();
   }

    private void initData(ArrayList<PieData> mData) {

        if(mData == null || mData.size()== 0){
            return;
        }

        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            sumValue +=pieData.getValue();
            int j = i % mColors.length;
            pieData.setColor(mColors[j]);

        }

        float sumAngle = 0;

        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);

            float perrcentage = pieData.getValue()/sumValue;
            float angle = perrcentage * 360;
            pieData.setPercentage(perrcentage);
            pieData.setAngle(angle);
            sumAngle +=angle;


        }



    }


}

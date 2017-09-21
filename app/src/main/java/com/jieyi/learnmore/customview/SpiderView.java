package com.jieyi.learnmore.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * ***********************************************
 * **                  _oo0oo_                  **
 * **                 o8888888o                 **
 * **                 88" . "88                 **
 * **                 (| -_- |)                 **
 * **                 0\  =  /0                 **
 * **               ___/'---'\___               **
 * **            .' \\\|     |// '.             **
 * **           / \\\|||  :  |||// \\           **
 * **          / _ ||||| -:- |||||- \\          **
 * **          | |  \\\\  -  /// |   |          **
 * **          | \_|  ''\---/''  |_/ |          **
 * **          \  .-\__  '-'  __/-.  /          **
 * **        ___'. .'  /--.--\  '. .'___        **
 * **     ."" '<  '.___\_<|>_/___.' >'  "".     **
 * **    | | : '-  \'.;'\ _ /';.'/ - ' : | |    **
 * **    \  \ '_.   \_ __\ /__ _/   .-' /  /    **
 * **====='-.____'.___ \_____/___.-'____.-'=====**
 * **                  '=---='                  **
 * ***********************************************
 * **              佛祖保佑  永无Bug              **
 * ***********************************************
 */

public class SpiderView extends View {
    private  int mCount = 5;
    private float mRadius;
    private float mAngle = (float) (Math.PI*2/ mCount);
    private int mcenterX,mcenterY;

    private Paint mpolygonPaint,mtextPaint;

    private static final String[] INDEX = {"情商","智商","领导力","勇敢","幽默"};
    private static int MaxValue = 100;

    private Paint.FontMetrics fontMetrics;


    public SpiderView(Context context) {
        super(context,null);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mRadius = Math.min(w,h)/2 * 0.8f;
        mcenterX = w/2;
        mcenterY = h/2;


    }



    private void init() {
        mpolygonPaint = new Paint();
        mpolygonPaint.setAntiAlias(true);
        mpolygonPaint.setColor(Color.BLACK);
        mpolygonPaint.setDither(true);
        mpolygonPaint.setStyle(Paint.Style.STROKE);
        mpolygonPaint.setStrokeWidth(3);

        mtextPaint = new Paint();
        mtextPaint.setAntiAlias(true);
        mtextPaint.setColor(Color.BLUE);
        mtextPaint.setStrokeWidth(3);
        mtextPaint.setStyle(Paint.Style.FILL);
        mtextPaint.setTextSize(50);






    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.rotate(-18,mcenterX,mcenterY);
        //画多边形
        drawPolygon(canvas);
        //画中心辐射的直线
        drawRadiation(canvas);

        //绘制文字
        drawIndexText(canvas);

    }

    private void drawIndexText(Canvas canvas) {
        fontMetrics = mtextPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;

         for (int i= 0;i<mCount;i++){

             float x = (float) ((mRadius+fontHeight/2)*Math.cos(mAngle* i)+mcenterX);
             float y = (float) ((mRadius + fontHeight/2)* Math.sin(mAngle * i)+ mcenterY);

             if(mAngle * i >= 0 && mAngle *i <= Math.PI/2){
                    canvas.drawText(INDEX[i],x,y,mtextPaint);

             }else if(mAngle * i >3 * Math.PI/2 && mAngle * i <= Math.PI *2){
                 canvas.drawText(INDEX[i],x,y,mtextPaint);
             }else if(mAngle *i >= Math.PI && mAngle * i <= 3* Math.PI/2){
                 canvas.drawText(INDEX[i],x-mtextPaint.measureText(INDEX[i]),y,mtextPaint);

             }else if(mAngle *i >=Math.PI/2 && mAngle * i <= Math.PI){
                 canvas.drawText(INDEX[i],x-mtextPaint.measureText(INDEX[i]),y,mtextPaint);
             }



         }

    }

    private void drawRadiation(Canvas canvas) {

        Path path = new Path();
        for (int i = 0; i < mCount; i++) {
            path.reset();
            path.moveTo(mcenterX,mcenterY);
            float x = (float) (mRadius*Math.cos(mAngle*i)+mcenterX);
            float y = (float) (mRadius * Math.sin(mAngle*i)+mcenterY);
            path.lineTo(x,y);
            canvas.drawPath(path,mpolygonPaint);
        }

    }

    private void drawPolygon(Canvas canvas) {

        Path path = new Path();
        float r = mRadius/(mCount -1);

        for (int i = 1; i < mCount; i++) {
            float curR = r * i;
            path.reset();

           //计算每个点的坐标
            for (int j = 0; j < mCount; j++) {

                if(j == 0){
                    path.moveTo(mcenterX+curR,mcenterY);
                }else {

                    float x = (float) (curR * Math.cos(mAngle*j)+mcenterX);
                    float y = (float) (curR*Math.sin(mAngle*j)+mcenterY);
                    path.lineTo(x,y);
                }
            }
             path.close();
             canvas.drawPath(path,mpolygonPaint);
        }


    }
}

package com.jieyi.learnmore.customview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zyh on 2017/9/21.
 */

public class BzierDemo1 extends View {

    private Paint mpaint;
    private int centerX, centerY;
    private PointF start, end, control;

    public BzierDemo1(Context context) {
        this(context, null);

    }

    public BzierDemo1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setColor(Color.BLUE);
        mpaint.setStyle(Paint.Style.STROKE);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control = new PointF(0, 0);

        Log.e("zyh","init is goingaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w / 2;
        centerY = h / 2;
        // 初始化数据点和控制点的位置
        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY - 100;

  Log.e("zyh","init is goingiiiiiiiii");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        control.x = event.getX();
        control.y = event.getY();
        Log.e("zyh", "control.x--> " + control.x + " control.y--> " + control.y);
        invalidate();

        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mpaint.setColor(Color.RED);
        mpaint.setStrokeWidth(20f);
        mpaint.setAntiAlias(true);
        canvas.drawPoint(centerX, centerY, mpaint);

        mpaint.setColor(Color.GRAY);
        mpaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mpaint);
        canvas.drawPoint(end.x, end.y, mpaint);
        canvas.drawPoint(control.x, control.y, mpaint);

        mpaint.setColor(Color.GREEN);
        canvas.drawLine(start.x, start.y, control.x, control.y, mpaint);
         canvas.drawLine(end.x,end.y,control.x,control.y,mpaint);
         //绘制贝塞尔曲线
        mpaint.setColor(Color.RED);
        mpaint.setStrokeWidth(10);

        Path path = new Path();
        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);
        canvas.drawPath(path,mpaint);

    }
}

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

/**
 * Created by zyh on 2017/9/19.
 */

public class TranslateView extends View {

    private int mWidth;
    private int mheith;

    public TranslateView(Context context) {
        this(context,null);

    }

    public TranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

      int  Width = MeasureSpec.getSize(widthMeasureSpec);
      int  heith = MeasureSpec.getSize(heightMeasureSpec);
        Log.e("zyh","width--> "+Width + "  height--> "+heith);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        canvas.translate(mWidth/2,mheith/2);
        RectF rectF = new RectF(-400,-400,400,400);
        for (int i = 0; i < 50; i++) {
            canvas.scale(0.9f,0.9f);
            canvas.drawRect(rectF,paint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth =w;
        mheith = h;
        Log.e("zyh","onSizeChanged midth  "+w + "  height-->  "+mheith);
    }
}

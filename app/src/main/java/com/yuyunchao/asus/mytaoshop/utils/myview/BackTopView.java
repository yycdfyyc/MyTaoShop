package com.yuyunchao.asus.mytaoshop.utils.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.yuyunchao.asus.mytaoshop.R;

/**
 * Created by asus on 2016/11/22.
 */
public class BackTopView extends View {
    public BackTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paint.setStrokeWidth(5f);
        canvas.drawLine(30,30,110,30,paint);
        canvas.drawLine(70,30,30,70,paint);
        canvas.drawLine(70,30,110,70,paint);
        canvas.drawLine(70,30,70,110,paint);
    }
}

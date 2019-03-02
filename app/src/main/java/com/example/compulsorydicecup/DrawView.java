package com.example.compulsorydicecup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawView extends View {
    int die;
    Paint paint = new Paint();
    private void init() {

    }

    public DrawView(Context context, int d) {
        super(context);
        init();
        die = d;
    }

    @Override
    public void onDraw(Canvas canvas) {

        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, 100, 100, paint);
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawRect(1, 1, 99, 99, paint);
        drawDots(canvas);
    }

    private void drawDots(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(20);
        switch(die) {
            case 1:
                canvas.drawCircle(50, 50, 10, paint);
                break;
            case 2:
                canvas.drawCircle(25, 25, 10, paint);
                canvas.drawCircle(75, 75, 10, paint);
                break;
            case 3:
                canvas.drawCircle(25, 25, 10, paint);
                canvas.drawCircle(50, 50, 10, paint);
                canvas.drawCircle(75, 75, 10, paint);
                break;
            case 4:
                canvas.drawCircle(25, 25, 10, paint);
                canvas.drawCircle(75, 25, 10, paint);
                canvas.drawCircle(25, 75, 10, paint);
                canvas.drawCircle(75, 75, 10, paint);
                break;
            case 5:
                canvas.drawCircle(50, 50, 10, paint);
                canvas.drawCircle(25, 25, 10, paint);
                canvas.drawCircle(75, 25, 10, paint);
                canvas.drawCircle(25, 75, 10, paint);
                canvas.drawCircle(75, 75, 10, paint);
                break;
            case 6:
                canvas.drawCircle(25, 25, 10, paint);
                canvas.drawCircle(25, 50, 10, paint);
                canvas.drawCircle(25, 75, 10, paint);
                canvas.drawCircle(75, 25, 10, paint);
                canvas.drawCircle(75, 50, 10, paint);
                canvas.drawCircle(75, 75, 10, paint);
                break;
                default:
                    Log.i("Drawing", "Something went wrong");
        }
    }

}
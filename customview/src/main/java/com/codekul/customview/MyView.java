package com.codekul.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by aniruddha on 24/11/16.
 */

public class MyView extends EditText {

    private Paint paint;
    private Canvas canvas;
    private float circlex = 10, circley= 10;

    public MyView(Context context) {
        super(context);
        // by coding

        init();
    }

    public MyView(Context context, AttributeSet attr) {
        super(context,attr);

        // by xml

        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        canvas.drawText("{code}kul;",300,70,paint);
        paint.setColor(Color.BLACK);

        canvas.drawCircle(circlex,circley,20,paint);

        canvas.drawCircle(50,50,30,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        super.onTouchEvent(event);
        /*circlex = event.getX();
        circley = event.getY();*/

        if(event.getAction() ==  MotionEvent.ACTION_DOWN){

            float dx = Math.abs(event.getX() - 30);
            float dy = Math.abs(event.getY() - 30);

            float res = dx*dx + dy*dy;
            if(res > 900){
                paint.setColor(Color.RED);
            }
            else {
                paint.setColor(Color.BLUE);
            }
        }
        invalidate();
        return false;
    }
}

package com.codekul.bouncingball;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by aniruddha on 24/11/16.
 */

public class MyView extends TextView {

    private Paint paint;
    private int x = 10, y = 10, rad = 30, dx, dy;

    public MyView(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.RED);


        new MyTask().execute(getWidth(), getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(x, y, rad, paint);
    }

    private class MyTask extends AsyncTask<Integer, Float, Void> {

        @Override
        protected Void doInBackground(Integer... params) {

            Log.i("@codekul","Width - "+getWidth() +" Height - "+getHeight());
            while (true) {

                if (x >= params[0]) dx = -5;
                if (x <= 0) dx = 5;
                if (y >= params[1]) dy = -5;
                if (y <= 0) dy = 5;

                x += dx;
                y += dy;

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress();
            }
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            super.onProgressUpdate(values);

            invalidate();
        }
    }
}


package com.example.hitthetarget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class DrawingView extends View implements View.OnTouchListener{
    private Paint strokePaint = new Paint();
    private int[] circlesRadius = new int[]{20, 60, 100};
    private Pair<Float, Float> circlePosition;
    private Random random;
    private int score;

    public DrawingView(Context context) {
        super(context);
        setFocusable(true);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setAntiAlias(true);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(20);
        random = new Random();
        this.setOnTouchListener(this);
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {

            }

            public void onFinish() {
                Toast.makeText(context,"Time is out! Your score: "+score,Toast.LENGTH_LONG).show();
            }
        }.start();
    }

    public void onDraw(Canvas canvas) {
        circlePosition = new Pair<>(110 + random.nextFloat() * (canvas.getWidth() - 220), 110 + random.nextFloat() * (canvas.getWidth() - 220));
        canvas.drawCircle( circlePosition.first, circlePosition.second, circlesRadius[0], strokePaint);
        canvas.drawCircle(circlePosition.first, circlePosition.second, circlesRadius[1], strokePaint);
        canvas.drawCircle(circlePosition.first, circlePosition.second, circlesRadius[2], strokePaint);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN &&  insideCircle(motionEvent.getX(),motionEvent.getY())) {
            score++;
            this.invalidate();
        }
        return false;
    }

    private boolean insideCircle(float postionX, float positionY){
        if(postionX > circlePosition.first - 100 && postionX < circlePosition.first + 100 && positionY > circlePosition.second - 100 && positionY < circlePosition.second + 100)
            return  true;
        return false;
    }
}

package com.example.myapplication.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Joystick extends View {

    private final Paint paint;
    private float x;
    private float y;
    private final float r;
    public OnChangeJoystick myOnChange;

    public Joystick(Context context, AttributeSet attr) {
        super(context, attr);
        paint = new Paint();
        paint.setColor(Color.RED);
        x = 250;
        y = 250;
        r = 70;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);
        canvas.drawCircle(250, 250, 200, paint);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(x, y, r, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        System.out.println("x: "+x+", y: "+y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_DOWN:
                if(event.getX()>70&&event.getX()<430){
                    this.x = event.getX();
                }
                if(event.getY()>70&&event.getY()<430){
                    this.y = event.getY();
                }
                break;

            case MotionEvent.ACTION_UP:
                x = 250;
                y = 250;
                break;
            default:
        }
        myOnChange.onChange(x,y);
        invalidate();
        return true;
    }
}

/*import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.PrintWriter;
import java.lang.ref.PhantomReference;

public class Joystick extends View {
    private int x_inner;
    private int y_inner;
    private int x_outer;
    private int y_outer;
    private int r_inner;
    private int r_outer;
    private Paint p_inner;
    private Paint p_outer;
    public Joystick(Context context, AttributeSet a,int x,int y,int r1,int r2) {
        super(context,a);
        x_inner = x;
        x_outer = x;
        y_inner = y;
        y_outer = y;
        r_inner = r1;
        r_outer =r2;
        p_inner = new Paint();
        p_inner.setColor(Color.BLUE);
        p_inner.setStyle(Paint.Style.FILL_AND_STROKE);

        p_outer = new Paint();
        p_outer.setColor(Color.YELLOW);
        p_inner.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x_inner,y_inner,r_inner,p_inner);
        canvas.drawCircle(x_outer,y_outer,r_outer,p_outer);
    }
}*/

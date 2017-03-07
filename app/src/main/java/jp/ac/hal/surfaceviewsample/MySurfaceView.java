package jp.ac.hal.surfaceviewsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasuco on 2017/03/06.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private Canvas canvas;
    private List<Target> list;
    private Paint paint;
    private int width;
    private int height;
    private SurfaceHolder surfaceHolder;
    private Thread thread;

    private static final int radius = 50;
    private static final int targetRadius = 60;
    public MySurfaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        paint = new Paint();
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);

        targetGet();

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread = null;

    }

    @Override
    public void run() {
        while (thread != null) {

            canvas = surfaceHolder.lockCanvas();
            try {
                Thread.sleep(1);
            }catch (Exception e){}

            if(thread == null) {
                continue;
            }
            canvas.drawColor(Color.WHITE);
            for (int i = 0; i <  list.size();i++) {
                Target target = list.get(i);
                paint.setColor(target.getColor());
                int speedX = target.getSpeedX();
                int speedY = target.getSpeedY();

                int x = target.getX();
                int y = target.getY();

                x += speedX;
                y += speedY;

                if (x - radius < 0 || width < x + radius) {
                    target.setSpeedX(-speedX);
                }

                if (y - radius < 0 || height < y + radius) {
                    target.setSpeedY(-speedY);
                }

                target.setX(x);
                target.setY(y);
                canvas.drawCircle(x, y, radius, paint);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float touchX = e.getX();
                float touchY = e.getY();
                for (int i = 0; i < list.size(); i++) {
                    Target target = list.get(i);
                    int x = target.getX();
                    int y = target.getY();

                    if (x < touchX + targetRadius && touchX - targetRadius < x) {
                        if (y < touchY + targetRadius && touchY - targetRadius < y) {
                            list.remove(i);
                        }
                    }
                    if(list.size() == 0) {
                        targetGet();
                    }
                }
                break;
            default:
                break;
        }

        return super.onTouchEvent(e);
    }
    public void targetGet() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int x = (int) (Math.random() * 600) + radius;
            int y = (int) (Math.random() * 950) + radius;
            int speedX = (int) (Math.random() * 10) + 1;
            int speedY = (int) (Math.random() * 10) + 1;
            int r = (int) (Math.random() * 255);
            int g = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);
            int color = Color.rgb(r, g, b);
            Target target = new Target(x, y, speedX, speedY, color);
            list.add(target);
        }

    }
}

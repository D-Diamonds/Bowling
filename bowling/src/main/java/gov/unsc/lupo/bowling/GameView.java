package gov.unsc.lupo.bowling;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.view.GestureDetectorCompat;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, GestureDetector.OnGestureListener {

    private GameThread thread;
    private Context context;
    private GestureDetectorCompat gestureDetector;


    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.gestureDetector = new GestureDetectorCompat(this.context, this);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        System.out.println(getWidth());
        this.post(new Runnable() {
            @Override
            public void run() {
                setThread(new GameThread(getHolder(), getContext(), new Handler(){
                    @Override
                    public void publish(LogRecord record) {

                    }

                    @Override
                    public void flush() {

                    }

                    @Override
                    public void close() throws SecurityException {

                    }
                }, getThis().getRootView()));
                getThread().start();
            }
        });
    }


    public GameView getThis() {
        return this;
    }

    public Thread getThread() {
        return this.thread;
    }

    public void setThread(GameThread thread) {
        this.thread = thread;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.gestureDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        System.out.println("TRYING TO FLING");
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        System.out.println("FLING RIGHT!");
                    } else {
                        System.out.println("FLING LEFT!");
                    }
                    result = true;
                }
            }
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    System.out.println("FLING DOWN!");
                } else {
                    System.out.println("FLING UP!");
                }
                result = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.thread.stop();
    }
}

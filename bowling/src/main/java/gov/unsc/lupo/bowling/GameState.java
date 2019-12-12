package gov.unsc.lupo.bowling;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;


public class GameState {

    private int screenWidth;
    private int screenHeight;
    private View view;
    private Context context;
    private RectF[] pins;
    private RectF ball;
    private float time;


    public GameState(View view, Context context) {
        this.view = view;
        this.context = context;
        View gv = view.findViewById(R.id.gameView);
        this.screenWidth = gv.getWidth();
        this.screenHeight = gv.getHeight();
        pins = new RectF[10];
        for (int i = 0; i < pins.length; i++) {
            float x = screenWidth * 0.1f + (i * 10f);
            float y = screenHeight * 0.25f + ((i % 4) * 100);
            pins[i] = new RectF(x, y, x + 25, y + 25);
        }
        ball = new RectF(screenWidth * 0.5f, screenHeight * 0.7f, screenWidth * 0.5f + 25, screenHeight * 0.7f + 25);
    }
    public void update(float dt) {
        time = 1 / dt;
        ball.offset(0, -dt * 60);
        if (ball.top <= 0)
            ball.offset(0, dt);

    }


    public void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.argb(255, 255, 255, 255));
        for (RectF pin : pins) {
            canvas.drawOval(pin, paint);
        }
        canvas.drawOval(ball, paint);
        canvas.drawText("FPS: " + time, 10, 100, paint);
    }
}

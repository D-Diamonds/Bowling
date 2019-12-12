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
        int c = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <= i; j++) {
                float x = screenWidth * 0.3f + (j * 100f);
                float y = screenHeight * 0.45f - (i * 100f);
                pins[c] = new RectF(x, y, x + 50, y + 50);
                c++;
            }
        }
        ball = new RectF(screenWidth * 0.5f, screenHeight * 0.7f, screenWidth * 0.5f + 25, screenHeight * 0.7f + 25);
    }

    public void update(float dt) {
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
    }
}

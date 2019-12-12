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
        int radius = 75;
        int gap = 3;
        int c = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <= i; j++) {
                float x = (screenWidth * 0.5f) - ((i + 0.5f) * radius) - (i * gap) + (j * (gap + radius));
                float y = screenHeight * 0.3f - (i * (radius + gap));
                pins[c] = new RectF(x, y, x + radius, y + radius);
                c++;
            }
        }

        ball = new RectF(screenWidth * 0.5f - (radius), screenHeight * 0.7f, screenWidth * 0.5f + (2 * radius), screenHeight * 0.7f + (radius * 2));
    }

    public void update(float dt) {
    }


    public void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.argb(255, 255, 255, 255));
        for (RectF pin : pins) {
            canvas.drawOval(pin, paint);
        }
        //canvas.drawOval(ball, paint);
    }
}

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
    private Ball[] pins;
    private Ball ball;
    private float time;


    public GameState(View view, Context context) {
        this.view = view;
        this.context = context;
        View gv = view.findViewById(R.id.gameView);
        this.screenWidth = gv.getWidth();
        this.screenHeight = gv.getHeight();
        pins = new Ball[10];
        int c = 0;
        int radius = 75;
        for (int i = 0; i < 4; i++) {
            for (int j = 0 ; j <= i; j++) {
                float x = screenWidth * 0.35f + (j * 2 * radius);
                float y = screenHeight * 0.45f - (i * 2 * radius);
                pins[c] = new Ball(x, y, radius);
                c++;
            }
        }
        System.out.println(c);
        ball = new Ball(screenWidth * 0.5f, screenHeight * 0.7f, 100);
    }

    public void update(float dt) {
        //ball.update(dt);
//        for (Ball pin : pins) {
//            pin.update(dt);
//        }
    }


    public void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.argb(255, 255, 255, 255));
        for (Ball pin : pins) {
            pin.draw(canvas, paint);
        }
        ball.draw(canvas, paint);
    }
}

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
        int radius = 100;
        int gap = 3;
        for (int i = 0; i < 4; i++) {
            for (int j = 0 ; j <= i; j++) {
                float x = (screenWidth * .5f) - ((i + .5f) * radius) - (i * gap) + (j * 2 * (gap + radius));
                float y = screenHeight * 0.4f - (i * (2 * radius + gap));
                pins[c] = new Ball(x, y, radius);
                c++;
            }
        }
        System.out.println(c);
        ball = new Ball(screenWidth * 0.5f, screenHeight * 0.7f, radius);
        ball.move(90, 50);
    }

    public void update(float dt) {
        for (Ball pin : pins) {
            for (Ball pin2 : pins) {
                if (!pin.equals(pin2) && pin.collides(pin2)) {
                    float pin1Dx = (float) (pin.getSpeed() * Math.cos(Math.toRadians(pin.getTheta())));
                    float pin2Dx = (float) (pin2.getSpeed() * Math.cos(Math.toRadians(pin2.getTheta())));

                    float pin1Dy = (float) (pin.getSpeed() * Math.sin(Math.toRadians(pin.getTheta())));
                    float pin2Dy = (float) (pin2.getSpeed() * Math.sin(Math.toRadians(pin2.getTheta())));

                    float finalX = (pin1Dx + pin2Dx) / 2;
                    float finalY = (pin1Dy + pin2Dy) / 2;
                    pin.move((float ) Math.atan(finalY / finalX), (float) Math.sqrt((finalX * finalX) + (finalY * finalY)));
                }
            }
        }
        ball.update(dt);
        for (Ball pin : pins) {
            pin.update(dt);
        }
    }


    public void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.argb(255, 255, 255, 255));
        for (Ball pin : pins) {
            pin.draw(canvas, paint);
        }
        ball.draw(canvas, paint);
    }
}

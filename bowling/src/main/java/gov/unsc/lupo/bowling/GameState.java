package gov.unsc.lupo.bowling;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.renderscript.Matrix2f;
import android.view.View;

import java.util.Vector;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector2f;


public class GameState {

    private int screenWidth;
    private int screenHeight;
    private View view;
    private Context context;
    private Ball[] pins;
    private Ball[] balls;
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
        ball.setVelocity(new Vector2f(0, -50));

        balls = new Ball[pins.length + 1];
        for (int i = 0; i < pins.length; i++) {
            balls[i] = pins[i];
        }
        balls[balls.length - 1] = ball;
    }

    public void update(float dt) {
        for (Ball pin : balls) {
            for (Ball pin2 : balls) {
                if (pin.getID() != pin2.getID() && pin.collides(pin2)) {
                    pin.setColor(Color.RED);
                    Vector2f p1 = new Vector2f(pin.getPosition());
                    Vector2f p2 = new Vector2f(pin2.getPosition());
                    Vector2f v1 = new Vector2f(pin.getVelocity());
                    Vector2f v2 = new Vector2f(pin2.getVelocity());
                    float mag1 = v1.length();
                    float mag2 = v2.length();
                    Vector2f p3 = new Vector2f(p2);
                    p3.sub(p1);
                    p3.normalize();
                    p3.scale((mag1 + mag2) / 2);
                    Vector2f p4 = new Vector2f(p3);
                    Matrix2f m1 = new Matrix2f();
                    m1.rotate((float) Math.PI / 2);
                    m1.multiply(new Matrix2f(new float[]{p4.x, 0, 0, p4.y}));
                    p4.set(new float[]{m1.get(0, 0), m1.get(1, 1)});
                    pin2.setVelocity(p3);
                    pin.setVelocity(p4);
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

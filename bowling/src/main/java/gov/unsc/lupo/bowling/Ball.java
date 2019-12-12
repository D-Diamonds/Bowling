package gov.unsc.lupo.bowling;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {

    private float screenWidth;
    private float screenHeight;
    private float radius;
    private float x;
    private float y;
    private float dx;
    private float dy;
    private String direction = "";

    private final float SPEED = 1;

    public Ball(float x, float y, float radius) {
//        this.screenWidth = screenWidth;
//        this.screenHeight = screenHeight;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
    }

    public void setDirection(String direction) {
        this.direction = direction;
        updateDirection();
    }

    public String getDirection() {
        return this.direction;
    }

    public void updateDirection() {
        if (this.direction.equals("right")) {
            this.dx = SPEED;
            this.dy = 0;
        }
        else if (this.direction.equals("left")) {
            this.dx = -SPEED;
            this.dy = 0;
        }
        else if (this.direction.equals("up")) {
            this.dx = 0;
            this.dy = -SPEED;
        }
        else if (this.direction.equals("down")) {
            this.dx = 0;
            this.dy = SPEED;
        }
        else {
            this.dx = 0;
            this.dy = 0;
        }

        //System.out.println("Dx: " + this.dx);
        //System.out.println("Dy: " + this.dy);

    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        //System.out.println("Ball placed at (" + this.x + ", " + this.y + ")");
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getRadius() {
        return this.radius;
    }


    public void update(float dt) {
        this.x += this.dx * dt;
        this.y += this.dy * dt;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        canvas.drawCircle(this.x, this.y, this.radius, paint);
    }
}

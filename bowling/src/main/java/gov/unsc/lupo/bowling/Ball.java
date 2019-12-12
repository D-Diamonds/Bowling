package gov.unsc.lupo.bowling;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.Nullable;

public class Ball {

    private float x;
    private float y;
    private float radius;
    private float theta;
    private int color = Color.BLUE;
    private float speed = 0;
    private int id;

    private static int ballID = 0;

    public Ball(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.theta = 0;
        this.id = ballID++;
    }

    public void move(float theta, float speed) {
        this.theta = theta;
        this.speed = speed;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void update(float dt) {
        this.x += this.speed * Math.cos(Math.toRadians(theta)) * dt;
        this.y += this.speed * Math.sin(Math.toRadians(theta)) * dt;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        canvas.drawOval(new RectF(this.x, this.y, this.x + this.radius, this.y + this.radius), paint);
    }

    public boolean collides(Ball b) {
        float lx = b.x - x;
        float ly = b.y - y;
        return Math.sqrt((lx * lx) + (ly * ly)) <= radius + b.radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getTheta() {
        return theta;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        assert obj instanceof Ball;
        return ((Ball) obj).id == id;
    }
}

package gov.unsc.lupo.bowling;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import javax.vecmath.Vector2f;

import androidx.annotation.Nullable;

public class Ball {

    private Vector2f position;
    private Vector2f velocity;
    private float radius;
    private int color = Color.BLUE;
    private int id;

    private static int ballID = 0;

    public Ball(float x, float y, float radius) {
        this.position = new Vector2f(x, y);
        this.radius = radius;
        this.id = ballID++;
        velocity = new Vector2f();
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public void update(float dt) {
        Vector2f d = new Vector2f(velocity);
        d.scale(dt);
        position.add(d);
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawOval(new RectF(this.position.x, this.position.y, this.position.x + this.radius, this.position.y + this.radius), paint);
    }

    public boolean collides(Ball b) {
        Vector2f v = new Vector2f(b.position);
        v.sub(position);
        return v.length() <= radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public Vector2f getPosition() {
        return position;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        assert obj instanceof Ball;
        return ((Ball) obj).id == id;
    }

    public int getID() {
        return id;
    }
}

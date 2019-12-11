package gov.unsc.lupo.bowling;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class GameState {

    private int screenWidth;
    private int screenHeight;
    private View view;
    private Context context;
    private Bitmap maze;
    private Ball player;
    private int time;
    private boolean foundGoal;


    public GameState(View view, Context context) {
        this.view = view;
        this.context = context;
//        this.screenWidth = view.findViewById(R.id.gameView).getWidth();
//        this.screenHeight = view.findViewById(R.id.gameView).getHeight();
//        this.player = new com.example.maze4.Ball(this.screenWidth, this.screenHeight);

    }

    public void reset() {
    }

    public Ball getPlayer() {
        return this.player;
    }

    public Bitmap getMaze() {
        return this.maze;
    }

    public void win() {
        System.out.println("WIN!");
        this.foundGoal = true;
        this.player.setDirection("null");

    }

    public boolean isGreenPixel(int pixel) {
        return Color.red(pixel) == 0 && Color.green(pixel) == 255 && Color.blue(pixel) == 0;
    }

    public boolean isRedPixel(int pixel) {
        return Color.red(pixel) == 255 && Color.green(pixel) == 0 && Color.blue(pixel) == 0;
    }

//    public boolean isWhitePixel(int pixel) {
//        return Color.red(pixel) == 255 && Color.green(pixel) == 255 && Color.blue(pixel) == 255;
//    }

    public boolean isWall(int pixel) {
        return Color.red(pixel) < 50 && Color.green(pixel) < 50 && Color.blue(pixel) < 50;
    }

    public void update(float dt) {
        //System.out.println(this.time);


        // ensure player stays in view
//        if (this.player.getX() < 0) {
//            this.player.setPosition(this.player.getRadius() + 2, this.player.getY());
//            this.player.setDirection("right");
//        }
//        else if (this.player.getX() > this.screenWidth) {
//            this.player.setPosition(this.screenWidth - this.player.getRadius() - 2, this.player.getY());
//            this.player.setDirection("left");
//        }
//        else if (this.player.getY() < 0) {
//            this.player.setPosition(this.player.getX(), this.player.getRadius() + 2);
//            this.player.setDirection("down");
//        }
//        else if (this.player.getY() > this.screenHeight) {
//            this.player.setPosition(this.player.getX(), this.screenHeight - this.player.getRadius() - 2);
//            this.player.setDirection("up");
//        }

        // collision detection
        if (this.player.getX() - this.player.getRadius() > 0 && this.player.getX() + this.player.getRadius() < this.screenWidth && this.player.getY() - this.player.getRadius() > 0 && this.player.getY() + this.player.getRadius() < this.screenHeight) {
            if (this.player.getDirection().equals("right")) {
                int pixelRight = this.maze.getPixel((int) (this.player.getX() + this.player.getRadius()), (int) this.player.getY());
                int pixelTop = this.maze.getPixel((int) this.player.getX(), (int) (this.player.getY() - this.player.getRadius()));
                int pixelBottom = this.maze.getPixel((int) this.player.getX(), (int) (this.player.getY() + this.player.getRadius()));
                // with wall
                if (isWall(pixelRight)) {
                    this.player.setPosition(this.player.getX() - 2, this.player.getY());
                    this.player.setDirection("left");
                }
                if (isWall(pixelTop))
                    this.player.setPosition(this.player.getX(), this.player.getY() + 1);
                if (isWall(pixelBottom))
                    this.player.setPosition(this.player.getX(), this.player.getY() - 1);

                // with goal
                if (isRedPixel(pixelRight))
                    win();
            }
            else if (this.player.getDirection().equals("left")) {
                int pixelLeft = this.maze.getPixel((int) (this.player.getX() - this.player.getRadius()), (int) this.player.getY());
                int pixelTop = this.maze.getPixel((int) this.player.getX(), (int) (this.player.getY() - this.player.getRadius()));
                int pixelBottom = this.maze.getPixel((int) this.player.getX(), (int) (this.player.getY() + this.player.getRadius()));
                // with wall
                if (isWall(pixelLeft)) {
                    this.player.setPosition(this.player.getX() + 2, this.player.getY());
                    this.player.setDirection("right");
                }
                if (isWall(pixelTop))
                    this.player.setPosition(this.player.getX(), this.player.getY() + 1);
                if (isWall(pixelBottom))
                    this.player.setPosition(this.player.getX(), this.player.getY() - 1);

                // with goal
                if (isRedPixel(pixelLeft))
                    win();
            }
            else if (this.player.getDirection().equals("up")) {
                int pixelTop = this.maze.getPixel((int) this.player.getX(), (int) (this.player.getY() - this.player.getRadius()));
                int pixelRight = this.maze.getPixel((int) (this.player.getX() + this.player.getRadius()), (int) this.player.getY());
                int pixelLeft = this.maze.getPixel((int) (this.player.getX() - this.player.getRadius()), (int) this.player.getY());
                // with wall
                if (isWall(pixelTop)) {
                    this.player.setPosition(this.player.getX(), this.player.getY() + 2);
                    this.player.setDirection("down");
                }
                if (isWall(pixelRight))
                    this.player.setPosition(this.player.getX() - 1, this.player.getY());
                if (isWall(pixelLeft))
                    this.player.setPosition(this.player.getX() + 1, this.player.getY());
                // with goal
                if (isRedPixel(pixelTop))
                    win();
            }
            else if (this.player.getDirection().equals("down")) {
                int pixelBottom = this.maze.getPixel((int) this.player.getX(), (int) (this.player.getY() + this.player.getRadius()));
                int pixelRight = this.maze.getPixel((int) (this.player.getX() + this.player.getRadius()), (int) this.player.getY());
                int pixelLeft = this.maze.getPixel((int) (this.player.getX() - this.player.getRadius()), (int) this.player.getY());
                // with wall
                if (isWall(pixelBottom)) {
                    this.player.setPosition(this.player.getX(), this.player.getY() - 2);
                    this.player.setDirection("up");
                }
                if (isWall(pixelRight))
                    this.player.setPosition(this.player.getX() - 1, this.player.getY());
                if (isWall(pixelLeft))
                    this.player.setPosition(this.player.getX() + 1, this.player.getY());
                // with goal
                if (isRedPixel(pixelBottom))
                    win();
            }
        }
        this.player.update(dt);

    }


    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.maze, 0, 0, paint);
        this.player.draw(canvas, paint);
    }
}

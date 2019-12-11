package gov.unsc.lupo.bowling;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.View;


import java.util.logging.Handler;

public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private GameState gameState;
    private long lastTime;

    public GameThread(SurfaceHolder holder, Context context, Handler handler, View view) {
        this.surfaceHolder = holder;
        this.paint = new Paint();
        this.gameState = new GameState(view, context);
        this.lastTime = System.nanoTime();
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void run() {

//        long startTime;
//        long timeMillis;
//        long waitTime;
//        long totalTime = 0;
//        int frameCount = 0;
//        long targetTime = 1000 / 60;
        while (true) {
            long time = System.nanoTime();
//            startTime = System.nanoTime();

            Canvas canvas = this.surfaceHolder.lockCanvas();
            int dt = (int) ((time - this.lastTime) / 10000000);
            this.gameState.update(dt);
            this.gameState.draw(canvas, this.paint);
            //System.out.println(dt);
//
//            timeMillis = (System.nanoTime() - startTime) / 1000000;
//            waitTime = targetTime - timeMillis;
//
//            try {
//                this.sleep(waitTime);
//            } catch (Exception e) {}
//
//            totalTime += System.nanoTime() - startTime;
//            frameCount++;
//            if (frameCount == 60) {
//                this.fps = 1000 / ((totalTime / frameCount) / 1000000);
//                frameCount = 0;
//                totalTime = 0;
//                //System.out.println(this.fps);
//            }
            this.surfaceHolder.unlockCanvasAndPost(canvas);
            this.lastTime = time;
        }
    }
}

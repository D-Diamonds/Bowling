package gov.unsc.lupo.bowling;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.View;

import java.util.logging.Handler;

public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private GameState gameState;

    public GameThread(SurfaceHolder holder, Context context, Handler handler, View view) {
        this.surfaceHolder = holder;
        this.paint = new Paint();
        this.paint.setTextSize(48);
        this.gameState = new GameState(view, context);
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        while (true) {
            Canvas canvas = this.surfaceHolder.lockCanvas();
            long time = System.nanoTime();
            float dt = (time - lastTime) / 1000000000f;
            lastTime = time;
            this.gameState.update(dt);
            this.gameState.draw(canvas, this.paint);
            this.surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}

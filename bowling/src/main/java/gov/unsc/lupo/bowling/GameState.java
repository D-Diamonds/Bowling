package gov.unsc.lupo.bowling;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;


public class GameState {

    private int screenWidth;
    private int screenHeight;
    private View view;
    private Context context;
    private Bitmap maze;
    private Ball[] pins;
    private int time;
    private boolean foundGoal;


    public GameState(View view, Context context) {
        this.view = view;
        this.context = context;
//        this.screenWidth = view.findViewById(R.id.gameView).getWidth();
//        this.screenHeight = view.findViewById(R.id.gameView).getHeight();
//        this.player = new com.example.maze4.Ball(this.screenWidth, this.screenHeight);
    }
    public void update(float dt) {

    }


    public void draw(Canvas canvas, Paint paint) {

    }
}

package com.example.bowling;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.bowling.GameView;
import com.example.bowling.R;


/*
    This maze app has simple functionality. On startup it displays 1 of the mazes and centers the player on the green block.
    At this time a timer starts. The goal is for the player to make it to the red block. Once the player makes it to the red block
    the timer stops and the game freezes telling the player it won in x seconds. To do another maze click Randomize. To reset the maze click Reset.
    The app may lag/freeze during randomize/reset. Also app lag may mess with collision detection causing going through walls, but logically if the app runs
    smoothly it should not go through walls. Also im not sure if the app breaks if you close the app to the background, so just dont do that because
    im not sure yet how to fix that. Ik it crashes if you implement new code without restarting the app. Those are the only two issues but those have
    been an issue for awhile... i think its how im threading my app.
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private GameView gameView;

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.gameView = findViewById(R.id.gameView);
    }
}

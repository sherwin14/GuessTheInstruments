package com.webteq.guesstheinstruments.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.webteq.guesstheinstruments.R;

/**
 * Created by user on 19/01/2017.
 */

public class ChooseGameActivity extends BaseActivity implements View.OnClickListener{

    private Button music,image;
    private Bundle b = new Bundle();
    private Intent i;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Game Type");

        image = (Button) findViewById(R.id.game_image);
        music = (Button) findViewById(R.id.game_sound);

        image.setOnClickListener(this);
        music.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.game_image:
                i = new Intent(ChooseGameActivity.this,GameActivity.class);
                b.putString("game","picture");
                startActivity(i);
                finish();
                break;
            case R.id.game_sound:
                i = new Intent(ChooseGameActivity.this,GameActivity.class);
                 b.putString("game","sound");
                startActivity(i);
                finish();
                break;
        }
    }
}

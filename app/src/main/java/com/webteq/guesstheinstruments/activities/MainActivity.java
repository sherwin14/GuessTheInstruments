package com.webteq.guesstheinstruments.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.webteq.guesstheinstruments.R;
import com.webteq.guesstheinstruments.SoundMediaPlayer;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button playGame,instrument,instructions;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playGame = (Button) findViewById(R.id.buttonPlay);
        instrument = (Button) findViewById(R.id.buttonList);
        instructions = (Button) findViewById(R.id.buttonInstruction);

        playGame.setOnClickListener(this);
        instrument.setOnClickListener(this);
        instructions.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPlay:
                Intent it = new Intent(this,ChooseGameActivity.class);
                startActivity(it);
                break;
            case R.id.buttonList:
                Intent intent = new Intent(this,InstrumentsActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonInstruction:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

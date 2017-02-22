package com.webteq.guesstheinstruments.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;
import com.webteq.guesstheinstruments.R;
import com.webteq.guesstheinstruments.SoundMediaPlayer;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button playGame,instrument,instructions;
    private TextView title,description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playGame     = (Button) findViewById(R.id.buttonPlay);
        instrument   = (Button) findViewById(R.id.buttonList);
        instructions = (Button) findViewById(R.id.buttonInstruction);
  /*      title        = (TextView) findViewById(R.id.textTitle);
        description  = (TextView) findViewById(R.id.textDescription);*/

        playGame.setOnClickListener(this);
        instrument.setOnClickListener(this);
        instructions.setOnClickListener(this);

       // title.setTypeface(EasyFonts.caviarDreamsBold(this));
       // description.setTypeface(EasyFonts.caviarDreams(this));
        playGame.setTypeface(EasyFonts.caviarDreamsBold(this));
        instrument.setTypeface(EasyFonts.caviarDreamsBold(this));
        instructions.setTypeface(EasyFonts.caviarDreamsBold(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPlay:
                Intent it = new Intent(this,GameActivity.class);
                Bundle b = new Bundle();
                b.putString("game","picture");
                startActivity(it);
                break;
            case R.id.buttonList:
                Intent intent = new Intent(this,InstrumentsActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonInstruction:
                Intent iInstruction = new Intent(this,InstructionActivity.class);
                startActivity(iInstruction);
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

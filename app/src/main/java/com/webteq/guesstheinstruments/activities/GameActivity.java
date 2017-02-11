package com.webteq.guesstheinstruments.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vstechlab.easyfonts.EasyFonts;
import com.webteq.guesstheinstruments.Models.GameModels;
import com.webteq.guesstheinstruments.R;
import com.webteq.guesstheinstruments.SoundMediaPlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;


public class GameActivity extends BaseActivity implements View.OnClickListener{
    private ArrayList<GameModels> gameModels;
    private ArrayList<GameModels> tempGameModels;
    private int index;

    private ImageView img1,img2,img3;
    private Button btnA,btnB,btnC,btnD,btnNext;
    private GameModels model;
    private ImageButton play;
    private SoundMediaPlayer smp;
    private Chronometer chronometer;
    private TextView levels;

    private long timeWhenStopped = 0;
    private int wrong_ans = 0;
    private int correct_ans = 0;
    private int questionSize=0;
    private boolean firstPlay = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Game");
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        play = (ImageButton) findViewById(R.id.game_play);
        levels = (TextView) findViewById(R.id.textView3);

        img1 = (ImageView) findViewById(R.id.imageView1);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img3 = (ImageView) findViewById(R.id.imageView3);

        btnA = (Button) findViewById(R.id.choiceOne);
        btnB = (Button) findViewById(R.id.choiceTwo);
        btnC = (Button) findViewById(R.id.choiceThree);
        btnD = (Button) findViewById(R.id.choiceFour);
        btnNext = (Button) findViewById(R.id.next);
        btnNext.setVisibility(View.INVISIBLE);
        btnA.setTypeface(EasyFonts.caviarDreams(this));
        btnB.setTypeface(EasyFonts.caviarDreams(this));
        btnC.setTypeface(EasyFonts.caviarDreams(this));
        btnD.setTypeface(EasyFonts.caviarDreams(this));


        loadQuestions();

        model = getGameMusic();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNext();
            }
        });

        loadData();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(firstPlay){
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();
                    firstPlay = false;
                }

                if(smp.isPlaying()){
                    smp.stop();
                    play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_circle_filled));
                }else {
                    smp.play();
                    play.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_circle_filled));

                }
            }
        });


        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(levels.getText().equals("Level 1")){
                    if(chronometer.getText().equals("05:00")){
                        chronometer.stop();
                        smp.stop();
                        showPopUp("Times Up!","Please wait",R.layout.game_over);
                        levels.setText("Level 2");
                    }
                }
            }
        });
    }

    private void goNext(){
        Log.v("Answer",tempGameModels.size()+"");
        if(tempGameModels.size() > 1){

            removeIndex();
            smp.stop();
            play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_circle_filled));

            model = getGameMusic();
            loadData();
            if(smp.isPlaying()){
                smp.stop();
                play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_circle_filled));
            }else {
                smp.play();
                play.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_circle_filled));
            }
        }else{
            showPopUp("CONGRATULATION", "You have " + correct_ans + " points!", R.layout.game_over);
        }

    }

    @Override
    public void onClick(View view) {
        String answer = ((Button)view).getText().toString();
        smp.stop();
        play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_circle_filled));

        if(model.getAnswer().contains(answer)){
            Toast.makeText(GameActivity.this,"Correct",Toast.LENGTH_SHORT).show();
            correct_ans++;
        }else{
            wrong_ans++;

            if(wrong_ans == 1){
                img3.setImageDrawable(getResources().getDrawable(R.drawable.smile_red));
            }else if(wrong_ans == 2){
                img2.setImageDrawable(getResources().getDrawable(R.drawable.smile_red));
            }else if(wrong_ans > 2) {
                img1.setImageDrawable(getResources().getDrawable(R.drawable.smile_red));
                showPopUp("Game Over","You don't have enough life!",R.layout.game_over);
            }
        }
        goNext();

    }

    private void showPopUp(String Title,String Message,int Drawable){
        TextView title = new TextView(this);
        title.setText(Title);
        title.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        title.setPadding(10, 15, 15, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);

        title.setTextSize(22);
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setCustomTitle(title);

        builder.setMessage(Message);
        builder.setView(Drawable);
        builder.setCancelable(false);
        builder.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                onBackPressed();
            }
        });

        builder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void loadData(){
        smp = new SoundMediaPlayer(this,model.getSound());

        btnA.setText(model.getChoice_one());
        btnB.setText(model.getChoice_two());
        btnC.setText(model.getChoice_three());
        btnD.setText(model.getChoice_four());

        Log.d("Answer",model.getAnswer());
    }

    private void loadQuestions(){
        gameModels = new ArrayList<>();
        gameModels.add(new GameModels(R.drawable.bagpipe_clipart,R.raw.bagpipes_sound,"Banjo","Bagpipes","Clarinet","Piano","Bagpipes"));
        gameModels.add(new GameModels(R.drawable.banjo_clipart,R.raw.banjo_sound,"Banjo","Bass Drum","Electric Guitar","Piano","Banjo"));
        gameModels.add(new GameModels(R.drawable.bass_clipart,R.raw.bass_sound,"Bagpipes","Bass Drum","Piano","Clarinet","Bass Drum"));
        gameModels.add(new GameModels(0,R.raw.clarinet_sound,"Electric Guitar","Bagpipes","Clarinet","Piano","Clarinet"));
        gameModels.add(new GameModels(0,R.raw.drum_set_sound,"Drum Set","Bagpipes","Clarinet","Piano","Drum Set"));
        gameModels.add(new GameModels(0,R.raw.electric_sound,"Drums","Bagpipes","Clarinet","Electric Guitar","Electric Guitar"));
        gameModels.add(new GameModels(0,R.raw.flute_sound,"Flute","Bagpipes","Clarinet","Electric Guitar","Flute"));
        gameModels.add(new GameModels(0,R.raw.guitar_sound,"Guitar","Bagpipes","Clarinet","Electric Guitar","Guitar"));
        gameModels.add(new GameModels(0,R.raw.harmonica_sound,"Drums","Bagpipes","Harmonica","Electric Guitar","Harmonica"));
        gameModels.add(new GameModels(0,R.raw.harp_sound,"Harp","Bagpipes","Flute","Piano","Harp"));
        gameModels.add(new GameModels(0,R.raw.horn_sound,"Drums","Horn","Clarinet","Harp","Horn"));
        gameModels.add(new GameModels(0,R.raw.mandolin_sound,"Piano","Mandolin","Clarinet","Horn","Mandolin"));
        gameModels.add(new GameModels(0,R.raw.maraca_sound,"Electric Guitar","Drums","Maraca","Clarinet","Maraca"));
        gameModels.add(new GameModels(0,R.raw.piano_sound,"Drums","Mandolin","Piano","Electric Guitar","Piano"));
        gameModels.add(new GameModels(0,R.raw.saxophone_sound,"Flute","Piano","Saxophone","Electric Guitar","Saxophone"));
        gameModels.add(new GameModels(0,R.raw.tambourine_sound,"Guitar","Bagpipes","Clarinet","Tambourine","Tambourine"));
        gameModels.add(new GameModels(0,R.raw.triangles_sound,"Drums","Triangles","Harmonica","Electric Guitar","Triangles"));
        gameModels.add(new GameModels(0,R.raw.trombone_sound,"Harp","Bagpipes","Trombone","Piano","Trombone"));
        gameModels.add(new GameModels(0,R.raw.trumpet_sound,"Trumpet","Horn","Clarinet","Harp","Trumpet"));
        gameModels.add(new GameModels(0,R.raw.tuba_sound,"Piano","Mandolin","Tuba","Horn","Tuba"));
        gameModels.add(new GameModels(0,R.raw.violin_sound,"Violin","Drums","Maraca","Clarinet","Violin"));
        gameModels.add(new GameModels(0,R.raw.xylophone_sound,"Drums","Mandolin","Xylophone","Electric Guitar","Xylophone"));
        gameModels.add(new GameModels(0,R.raw.lyre_sound,"Electric Guitar","Drums","Lyre","Clarinet","Lyre"));
        gameModels.add(new GameModels(0,R.raw.drum_sound,"Drums","Mandolin","Piano","Electric Guitar","Drums"));

        tempGameModels = new ArrayList<>(gameModels);

    }


    private void removeIndex(){
        tempGameModels.remove(index);
    }

    private GameModels getGameMusic(){
        return tempGameModels.get(getRandomIndex(tempGameModels.size()));
    }

    private int getRandomIndex(int size)   {
       // throw new IllegalArgumentException(" size must be positive or greater than zero");
        Random rand = new Random();
        index = size > 0 ? rand.nextInt(size): null;

        return index;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(smp != null)
            smp.reset();
    }
}

package com.webteq.guesstheinstruments.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.webteq.guesstheinstruments.Models.GameModels;
import com.webteq.guesstheinstruments.R;
import com.webteq.guesstheinstruments.SoundMediaPlayer;

import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends BaseActivity {
    private ArrayList<GameModels> gameModels;
    private ArrayList<Integer> list;
    private int questionSize=0;
    private ImageView imgThumb;
    private Button btnA,btnB,btnC,btnD,btnNext;
    private GameModels model;
    private ImageButton play;
    private SoundMediaPlayer smp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Game");
        play = (ImageButton) findViewById(R.id.game_play);

        btnA = (Button) findViewById(R.id.choiceOne);
        btnB = (Button) findViewById(R.id.choiceTwo);
        btnC = (Button) findViewById(R.id.choiceThree);
        btnD = (Button) findViewById(R.id.choiceFour);
        btnNext = (Button) findViewById(R.id.next);


        loadQuestions();
        questionSize = gameModels.size();
        Log.d("c",questionSize+"");

        model = gameModels.get(randomInt());
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smp.stop();
                play.setImageDrawable(getResources().getDrawable(R.drawable.play));
                if(((Button)view).getText() == "Done"){
                    Toast.makeText(getApplication(),"DONE!!",Toast.LENGTH_SHORT).show();
                }else{
                    model = gameModels.get(randomInt());
                    loadData();
                }
            }
        });

        loadData();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(smp.isPlaying()){
                    smp.stop();
                    play.setImageDrawable(getResources().getDrawable(R.drawable.play));
                }else {
                    smp.play();
                    play.setImageDrawable(getResources().getDrawable(R.drawable.pause));
                }
            }
        });

    }
    private void loadData(){
     /*   Glide.with(getBaseContext())
                .load(model.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgThumb);*/
        smp = new SoundMediaPlayer(this,model.getSound());

        btnA.setText(model.getChoice_one());
        btnB.setText(model.getChoice_two());
        btnC.setText(model.getChoice_three());
        btnD.setText(model.getChoice_four());
    }

    private void loadQuestions(){
        gameModels = new ArrayList<>();
        gameModels.add(new GameModels(R.drawable.bagpipe_clipart,R.raw.bagpipes_sound,"Banjo","Bagpipes","Clarinet","Piano","Bagpipes"));
        gameModels.add(new GameModels(R.drawable.banjo_clipart,R.raw.banjo_sound,"Banjo","Bass Drum","Electric Guitar","Piano","Banjo"));
        gameModels.add(new GameModels(R.drawable.bass_clipart,R.raw.bass_sound,"Bagpipes","Bass Drum","Piano","Clarinet","Bass Drum"));
        gameModels.add(new GameModels(0,R.raw.clarinet_sound,"Electric Guitar","Bagpipes","Clarinet","Piano","Clarinet"));
        gameModels.add(new GameModels(0,R.raw.drums_sound,"Drums","Bagpipes","Clarinet","Piano","Drums"));
        gameModels.add(new GameModels(0,R.raw.electric_sound,"Drums","Bagpipes","Clarinet","Electric Guitar","Electric Guitar"));
        gameModels.add(new GameModels(0,R.raw.flute_sound,"Flute","Bagpipes","Clarinet","Electric Guitar","Flute"));

        gameModels.add(new GameModels(0,R.raw.guitar_sound,"Guitar","Bagpipes","Clarinet","Electric Guitar","Guitar"));
        gameModels.add(new GameModels(0,R.raw.harmonica_sound,"Drums","Bagpipes","Harmonica","Electric Guitar","Harmonica"));
        gameModels.add(new GameModels(0,R.raw.harp_sound,"Harp","Bagpipes","Flute","Piano","Harp"));
        gameModels.add(new GameModels(0,R.raw.horn_sound,"Drums","Horn","Clarinet","Harp","Horn"));
        gameModels.add(new GameModels(0,R.raw.mandolin_sound,"Piano","Mandolin","Clarinet","Horn","Mandolin"));
        gameModels.add(new GameModels(0,R.raw.maraca_sound,"Electric Guitar","Drums","Maraca","Clarinet","Maraca"));
        gameModels.add(new GameModels(0,R.raw.piano_sound,"Drums","Mandolin","Piano","Electric Guitar","Piano"));
        list = new ArrayList<>(gameModels.size());
        for(int i = 1; i <=gameModels.size(); i++) {
            list.add(i);
        }
    }

    private int randomInt(){
        int index;


        Log.d("pos",list.size() + "");
        Random rand = new Random();
        index = rand.nextInt(list.size());
        list.remove(index);

        if (list.size() < 1)
        {
            btnNext.setText("Done");
        }
        return index;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(smp != null)
            smp.stop();
    }
}

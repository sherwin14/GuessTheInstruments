package com.webteq.guesstheinstruments.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.vstechlab.easyfonts.EasyFonts;
import com.webteq.guesstheinstruments.Models.GameModels;
import com.webteq.guesstheinstruments.R;
import com.webteq.guesstheinstruments.SoundMediaPlayer;

import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends BaseActivity implements View.OnClickListener{
    private ArrayList<GameModels> gameModels;
    private ArrayList<Integer> list;
    private int questionSize=0;
    private ImageView imgThumb,img1,img2,img3;
    private Button btnA,btnB,btnC,btnD,btnNext;
    private GameModels model;
    private ImageButton play;
    private SoundMediaPlayer smp;
    private int wrong_ans = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Game");
        play = (ImageButton) findViewById(R.id.game_play);

        img1 = (ImageView) findViewById(R.id.imageView1);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img3 = (ImageView) findViewById(R.id.imageView3);

        btnA = (Button) findViewById(R.id.choiceOne);
        btnB = (Button) findViewById(R.id.choiceTwo);
        btnC = (Button) findViewById(R.id.choiceThree);
        btnD = (Button) findViewById(R.id.choiceFour);
        btnNext = (Button) findViewById(R.id.next);

        btnA.setTypeface(EasyFonts.caviarDreams(this));
        btnB.setTypeface(EasyFonts.caviarDreams(this));
        btnC.setTypeface(EasyFonts.caviarDreams(this));
        btnD.setTypeface(EasyFonts.caviarDreams(this));


        loadQuestions();
        questionSize = gameModels.size();
        Log.d("c",questionSize+"");

        model = gameModels.get(randomInt());
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smp.stop();
                play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_circle_filled));
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
    }

    @Override
    public void onClick(View view) {
        String answer = ((Button)view).getText().toString();



        if(model.getAnswer().contains(answer)){
            Toast.makeText(GameActivity.this,"Correct",Toast.LENGTH_SHORT).show();
        }else{
            wrong_ans++;

            if(wrong_ans == 1){
                img3.setImageDrawable(getResources().getDrawable(R.drawable.smile_red));
            }else if(wrong_ans == 2){
                img2.setImageDrawable(getResources().getDrawable(R.drawable.smile_red));
            }else if(wrong_ans == 3){
                img1.setImageDrawable(getResources().getDrawable(R.drawable.smile_red));
            }
        }
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
        gameModels.add(new GameModels(0,R.raw.drum_set_sound,"Drums","Bagpipes","Clarinet","Piano","Drum Set"));
        gameModels.add(new GameModels(0,R.raw.electric_sound,"Drums","Bagpipes","Clarinet","Electric Guitar","Electric Guitar"));
        gameModels.add(new GameModels(0,R.raw.flute_sound,"Flute","Bagpipes","Clarinet","Electric Guitar","Flute"));
        gameModels.add(new GameModels(0,R.raw.guitar_sound,"Guitar","Bagpipes","Clarinet","Electric Guitar","Guitar"));
        gameModels.add(new GameModels(0,R.raw.harmonica_sound,"Drums","Bagpipes","Harmonica","Electric Guitar","Harmonica"));
        gameModels.add(new GameModels(0,R.raw.harp_sound,"Harp","Bagpipes","Flute","Piano","Harp"));
        gameModels.add(new GameModels(0,R.raw.horn_sound,"Drums","Horn","Clarinet","Harp","Horn"));
        gameModels.add(new GameModels(0,R.raw.mandolin_sound,"Piano","Mandolin","Clarinet","Horn","Mandolin"));
        gameModels.add(new GameModels(0,R.raw.maraca_sound,"Electric Guitar","Drums","Maraca","Clarinet","Maraca"));
        gameModels.add(new GameModels(0,R.raw.piano_sound,"Drums","Mandolin","Piano","Electric Guitar","Piano"));
        gameModels.add(new GameModels(0,R.raw.saxophone_sound,"Flute","Bagpipes","Clarinet","Electric Guitar","Saxophone"));
        gameModels.add(new GameModels(0,R.raw.tambourine_sound,"Guitar","Bagpipes","Clarinet","Electric Guitar","Tambourine"));
        gameModels.add(new GameModels(0,R.raw.triangles_sound,"Drums","Bagpipes","Harmonica","Electric Guitar","Triangles"));
        gameModels.add(new GameModels(0,R.raw.trombone_sound,"Harp","Bagpipes","Flute","Piano","Trombone"));
        gameModels.add(new GameModels(0,R.raw.trumpet_sound,"Drums","Horn","Clarinet","Harp","Trumpet"));
        gameModels.add(new GameModels(0,R.raw.tuba_sound,"Piano","Mandolin","Clarinet","Horn","Tuba"));
        gameModels.add(new GameModels(0,R.raw.violin_sound,"Electric Guitar","Drums","Maraca","Clarinet","Violin"));
        gameModels.add(new GameModels(0,R.raw.xylophone_sound,"Drums","Mandolin","Piano","Electric Guitar","Xylophone"));
        gameModels.add(new GameModels(0,R.raw.lyre_sound,"Electric Guitar","Drums","Lyre","Clarinet","Lyre"));
        gameModels.add(new GameModels(0,R.raw.drum_sound,"Drums","Mandolin","Piano","Electric Guitar","Drums"));



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

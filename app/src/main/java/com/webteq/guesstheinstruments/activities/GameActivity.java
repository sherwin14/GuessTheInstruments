package com.webteq.guesstheinstruments.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.webteq.guesstheinstruments.Models.GameModels;
import com.webteq.guesstheinstruments.R;

import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends BaseActivity {
    private ArrayList<GameModels> gameModels;
    private ArrayList<Integer> list;
    private int questionSize=0;
    private ImageView imgThumb;
    private Button btnA,btnB,btnC,btnD,btnNext;
    private GameModels model;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Game");
        imgThumb = (ImageView) findViewById(R.id.game_picture);

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

                if(((Button)view).getText() == "Done"){
                    Toast.makeText(getApplication(),"DONE!!",Toast.LENGTH_SHORT).show();
                }else{
                    model = gameModels.get(randomInt());
                    loadData();
                }
            }
        });

        loadData();


    }
    private void loadData(){
        Glide.with(getBaseContext())
                .load(model.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgThumb);

        btnA.setText(model.getChoice_one());
        btnB.setText(model.getChoice_two());
        btnC.setText(model.getChoice_three());
        btnD.setText(model.getChoice_four());
    }

    private void loadQuestions(){
        gameModels = new ArrayList<>();
        gameModels.add(new GameModels(R.drawable.bagpipe_clipart,R.raw.bagpipes_sound,"Banjo","Bagpipes","Clarinet","Keyboard","Bagpipes"));
        gameModels.add(new GameModels(R.drawable.banjo_clipart,R.raw.banjo_sound,"Banjo","Bagpipes","Clarinet","Keyboard","Banjo"));
        gameModels.add(new GameModels(R.drawable.bass_clipart,R.raw.bass_drum_sound,"Bagpipes","Bass Drum","Keyboard","Clarinet","Bass Drum"));
        gameModels.add(new GameModels(R.drawable.bagpipe_clipart,R.raw.bagpipes_sound,"Banjo","Bagpipes","Clarinet","Keyboard","Bagpipes"));
        gameModels.add(new GameModels(R.drawable.banjo_clipart,R.raw.banjo_sound,"Banjo","Bagpipes","Clarinet","Keyboard","Banjo"));
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
}

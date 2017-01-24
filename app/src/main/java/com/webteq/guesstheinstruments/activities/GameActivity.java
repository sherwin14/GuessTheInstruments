package com.webteq.guesstheinstruments.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

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
    private Button btnA,btnB,btnC,btnD;
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

        loadQuestions();
        questionSize = gameModels.size();
        list = new ArrayList<>(questionSize);

        GameModels model = gameModels.get(randomInt());

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

    }

    private int randomInt(){
        for(int i = 1; i <= questionSize; i++) {
            list.add(i);
        }

        Random rand = new Random();
        int index = rand.nextInt(list.size());
        list.remove(index);
        return index;
    }
}

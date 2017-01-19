package com.webteq.guesstheinstruments.activities;

import android.os.Bundle;

import com.webteq.guesstheinstruments.R;

/**
 * Created by user on 19/01/2017.
 */

public class GameActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Game");
    }
}

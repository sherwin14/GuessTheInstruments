package com.webteq.guesstheinstruments.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.webteq.guesstheinstruments.GuessTheInstruments;

/**
 * Created by user on 19/01/2017.
 */

public class BaseActivity extends AppCompatActivity {
    protected GuessTheInstruments application;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (GuessTheInstruments) getApplication();
    }
}

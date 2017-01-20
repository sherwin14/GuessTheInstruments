package com.webteq.guesstheinstruments;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Created by user on 19/01/2017.
 */

public class GuessTheInstruments extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}

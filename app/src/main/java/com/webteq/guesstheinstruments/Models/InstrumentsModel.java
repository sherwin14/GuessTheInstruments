package com.webteq.guesstheinstruments.Models;

/**
 * Created by Sherwin on 1/19/2017.
 */

public class InstrumentsModel {
    private String intrumentName;

    private String description;

    private int instrumentDrawable;

    private int instrumentSounds;

    public InstrumentsModel(String intrumentName, String description, int Drawable, int Sounds) {
        this.intrumentName = intrumentName;
        this.description = description;
        this.instrumentDrawable = Drawable;
        this.instrumentSounds = Sounds;
    }

    public int getInstrumentSounds() {
        return instrumentSounds;
    }

    public void setInstrumentSounds(int instrumentSounds) {
        this.instrumentSounds = instrumentSounds;
    }

    public int getInstrumentDrawable() {
        return instrumentDrawable;
    }

    public void setInstrumentDrawable(int instrumentDrawable) {
        this.instrumentDrawable = instrumentDrawable;
    }

    public String getIntrumentName() {
        return intrumentName;
    }

    public void setIntrumentName(String intrumentName) {
        this.intrumentName = intrumentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

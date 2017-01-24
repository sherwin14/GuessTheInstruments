package com.webteq.guesstheinstruments.Models;


/**
 * Created by user on 19/01/2017.
 */

public class GameModels  {

    private int image;

    private int sound;

    private String choice_one;

    private String choice_two;

    private String choice_three;

    private String choice_four;

    private String answer;

    public GameModels( int image, int sound, String choice_one, String choice_two, String choice_three, String choice_four, String answer) {

        this.image = image;
        this.sound = sound;
        this.choice_one = choice_one;
        this.choice_two = choice_two;
        this.choice_three = choice_three;
        this.choice_four = choice_four;
        this.answer = answer;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public String getChoice_one() {
        return choice_one;
    }

    public void setChoice_one(String choice_one) {
        this.choice_one = choice_one;
    }

    public String getChoice_two() {
        return choice_two;
    }

    public void setChoice_two(String choice_two) {
        this.choice_two = choice_two;
    }

    public String getChoice_three() {
        return choice_three;
    }

    public void setChoice_three(String choice_three) {
        this.choice_three = choice_three;
    }

    public String getChoice_four() {
        return choice_four;
    }

    public void setChoice_four(String choice_four) {
        this.choice_four = choice_four;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

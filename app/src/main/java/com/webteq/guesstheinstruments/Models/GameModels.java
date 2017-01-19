package com.webteq.guesstheinstruments.Models;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by user on 19/01/2017.
 */

public class GameModels extends DataSupport {
    @Column(nullable = false)
    private String game_type;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String sound;

    @Column(nullable = false)
    private String choice_one;

    @Column(nullable = false)
    private String choice_two;

    @Column(nullable = false)
    private String choice_three;

    @Column(nullable = false)
    private String choice_four;

    @Column(nullable = false)
    private String answer;

    public GameModels(String game_type, String image, String sound, String choice_one, String choice_two, String choice_three, String choice_four, String answer) {
        this.game_type = game_type;
        this.image = image;
        this.sound = sound;
        this.choice_one = choice_one;
        this.choice_two = choice_two;
        this.choice_three = choice_three;
        this.choice_four = choice_four;
        this.answer = answer;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
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

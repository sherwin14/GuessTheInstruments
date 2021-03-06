package com.webteq.guesstheinstruments;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;


/**
 * Created by sherw on 1/4/2017.
 */

public class SoundMediaPlayer {
    private Context _context;
    private int _raw;
    private MediaPlayer mediaPlayer;



    public SoundMediaPlayer(Context _context, int _raw) {
        this._context = _context;
        this._raw = _raw;
        //Testing comment
        mediaPlayer = MediaPlayer.create(_context,_raw);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void play() {
       if(isPlaying()){
           mediaPlayer.stop();
       }
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

    }
    public void stop(){
        if(isPlaying()){
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }

        //mediaPlayer.release();
    }

    public boolean isPlaying() throws IllegalStateException  {
        return mediaPlayer.isPlaying();
    }

    public void pause(){
        mediaPlayer.pause();
    }


    public void reset(){
       if(mediaPlayer!=null){
           mediaPlayer.stop();
           mediaPlayer.release();
       }
    }

}

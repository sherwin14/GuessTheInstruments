package com.webteq.guesstheinstruments.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.webteq.guesstheinstruments.Models.InstrumentsModel;
import com.webteq.guesstheinstruments.R;
import com.webteq.guesstheinstruments.SoundMediaPlayer;
import com.webteq.guesstheinstruments.adapters.InstrumentsAdapter;
import com.webteq.guesstheinstruments.interfaces.OnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherwin on 1/19/2017.
 */

public class InstrumentsActivity extends BaseActivity {

    private RecyclerView instrumentRecycler;
    private LinearLayoutManager llm;
    //private InstrumentsModel im;
    private InstrumentsAdapter adapter;
    private List<InstrumentsModel> instrumentsModelList;
    private ImageView imageView;
    private TextView textView;
    private SoundMediaPlayer soundPlayer;
    private LinearLayout ll;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Instruments List");

        ll = (LinearLayout) findViewById(R.id.player_container);

        imageView = (ImageView) findViewById(R.id.imageClip);
        textView  = (TextView)  findViewById(R.id.caption);

        instrumentRecycler = (RecyclerView) findViewById(R.id.fragment_art_recyclerview);
        instrumentRecycler.setHasFixedSize(true);
        instrumentRecycler.setDrawingCacheEnabled(true);
        instrumentRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        instrumentRecycler.setLayoutManager(llm);
        LoadData();
        adapter = new InstrumentsAdapter(instrumentsModelList, this, new OnClickListener() {
            @Override
            public void OnItemClick(View view, InstrumentsModel model) {

                if(soundPlayer != null){
                    soundPlayer.stop();
                }


                ll.setVisibility(View.VISIBLE);

                Glide.with(getBaseContext())
                        .load(model.getInstrumentDrawable())
                        .error(R.drawable.note)
                        .placeholder(R.drawable.note)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);

                textView.setText(model.getIntrumentName());
                soundPlayer = new SoundMediaPlayer(getBaseContext() ,model.getInstrumentSounds());

                final ImageButton play = (ImageButton) findViewById(R.id.play);

                final Handler mHandler = new Handler();
                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        soundPlayer.play();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                play.setEnabled(!soundPlayer.isPlaying());
                                //play.setEnabled(!soundPlayer.isPlaying());
                                mHandler.postDelayed(this,1);
                            }
                        });
                        play.setEnabled(!soundPlayer.isPlaying());
                    }
                });

                final ImageButton stop = (ImageButton) findViewById(R.id.stop);
                stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        soundPlayer.stop();
                        play.setEnabled(true);
                    }
                });

                final ImageButton pause = (ImageButton) findViewById(R.id.pause);
                pause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        soundPlayer.pause();
                        play.setEnabled(true);
                    }
                });

            }
        });
        adapter.setHasStableIds(true);
        instrumentRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void LoadData(){
        instrumentsModelList = new ArrayList<>();
        instrumentsModelList.add(new InstrumentsModel("BAGPIPE","null",R.drawable.bagpipe_clipart,R.raw.bagpipes_sound));
        instrumentsModelList.add(new InstrumentsModel("BANJO","null",R.drawable.banjo_clipart,R.raw.banjo_sound));
        instrumentsModelList.add(new InstrumentsModel("BASSDRUM","null",R.drawable.bass_clipart,R.raw.bass_sound));
        instrumentsModelList.add(new InstrumentsModel("CLARINET","null",R.drawable.clarinet_clipart,R.raw.clarinet_sound));
        instrumentsModelList.add(new InstrumentsModel("DRUM SET","null",R.drawable.drum_set_clipart,R.raw.drum_set_sound));
        instrumentsModelList.add(new InstrumentsModel("DRUM","null",R.drawable.drum_clipart,R.raw.drum_sound));
        instrumentsModelList.add(new InstrumentsModel("ELECTRIC GUITAR","null",R.drawable.electric_guitar_clipart,R.raw.electric_sound));
        instrumentsModelList.add(new InstrumentsModel("FLUTE","null",R.drawable.flute_clipart,R.raw.flute_sound));
        instrumentsModelList.add(new InstrumentsModel("FRENCH HORN","null",R.drawable.french_horn_clipart,R.raw.horn_sound));
        instrumentsModelList.add(new InstrumentsModel("GUITAR","null",R.drawable.guitar_clipart,R.raw.guitar_sound));
        instrumentsModelList.add(new InstrumentsModel("HARMONICA","null",R.drawable.harmonica_clipart,R.raw.harmonica_sound));
        instrumentsModelList.add(new InstrumentsModel("HARP","null",R.drawable.harp_clipart,R.raw.harp_sound));
        instrumentsModelList.add(new InstrumentsModel("LYRE","null",R.drawable.lyre_clipart,R.raw.lyre_sound));
        instrumentsModelList.add(new InstrumentsModel("MANDOLIN","null",R.drawable.mandolin_clipart,R.raw.mandolin_sound));
        instrumentsModelList.add(new InstrumentsModel("MARACAS","null",R.drawable.maracas_clipart,R.raw.maraca_sound));
        instrumentsModelList.add(new InstrumentsModel("PIANO","null",R.drawable.piano_clipart,R.raw.piano_sound));
        instrumentsModelList.add(new InstrumentsModel("SAXOPHONE","null",R.drawable.saxophone_clipart,R.raw.saxophone_sound));
        instrumentsModelList.add(new InstrumentsModel("TAMBOURINE","null",R.drawable.tambourine_clipart,R.raw.tambourine_sound));
        instrumentsModelList.add(new InstrumentsModel("TRIANGLE","null",R.drawable.triangle_clipart,R.raw.triangles_sound));
        instrumentsModelList.add(new InstrumentsModel("TROMBONE","null",R.drawable.trombone_clipart,R.raw.trombone_sound));
        instrumentsModelList.add(new InstrumentsModel("TRUMPET","null",R.drawable.trumpet_clipart,R.raw.trumpet_sound));
        instrumentsModelList.add(new InstrumentsModel("TUBA","null",R.drawable.tuba_clipart,R.raw.tuba_sound));
        instrumentsModelList.add(new InstrumentsModel("VIOLIN","null",R.drawable.violin_clipart,R.raw.violin_sound));
        instrumentsModelList.add(new InstrumentsModel("XYLOPHONE","null",R.drawable.xylophone_clipart,R.raw.xylophone_sound));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(soundPlayer != null)
            soundPlayer.stop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}

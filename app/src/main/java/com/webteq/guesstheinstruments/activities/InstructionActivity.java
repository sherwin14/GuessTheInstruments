package com.webteq.guesstheinstruments.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.MenuItem;
import android.widget.TextView;

import com.webteq.guesstheinstruments.R;

import org.w3c.dom.Text;

/**
 * Created by user on 20/02/2017.
 */

public class InstructionActivity extends BaseActivity {

    private String instruction = "<body><h1><strong>How to play</strong></h1>\n" +
            "<p>Press \"<span style=\"color: #800080;\">Play Game</span>\" to start.</p>\n" +
            "<p>You have only limited time to each questions so you need to hurry what the answer is!</p>\n" +
            "<p>You have (3) lives for the game but if you used it all the game would be over :(</p>\n" +
            "<p>The music will automatically play once you started the game, Guess what the instrument is playing and press the answer you think is correct then press next until you've reached the last level!</p>\n" +
            "<h3>Each difficulty has (y) seconds only.</h3>\n" +
            "<ul>\n" +
            "<br><li>(60) seconds for Easy.</li>\n" +
            "<br><li>(40) seconds for Medium.</li>\n" +
            "<br><li>(20) seconds for Hard.</li>\n" +
            "</ul></body>";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Instructions");
        TextView ins = (TextView) findViewById(R.id.textInstructions);
        Spanned result = Html.fromHtml(instruction,null,null);
        ins.setText(result);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}

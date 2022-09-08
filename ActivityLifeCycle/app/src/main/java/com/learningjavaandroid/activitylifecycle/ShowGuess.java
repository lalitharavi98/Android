package com.learningjavaandroid.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ShowGuess extends AppCompatActivity {
    private TextView showGuessTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        //Bundle = get all extras and bundle it in one place
        Bundle extra = getIntent().getExtras();

        showGuessTextView = findViewById(R.id.received_textview);

//        if(getIntent().getStringExtra("guess") != null){
//            String value = getIntent().getStringExtra("guess");
//            showGuessTextView.setText(value);
//        }

        //With Bundle preferable

        if(extra != null){
            showGuessTextView.setText(extra.getString("guess"));
        }

        //Return to main activity on clicking textView

        showGuessTextView.setOnClickListener(v -> {
            // Returns the intent that started this activity.
            Intent intent = getIntent();
            intent.putExtra("message_back","Back from second activity");

            /* Send data back to main activity and finish this activity i.e remove it from stack */
            setResult(123,intent);
            finish();
        });











    }
}
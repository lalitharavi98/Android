package com.learningjavaandroid.playmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBarId);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://buildappswithpaulo.com/music/watch_me.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaPlayer.OnPreparedListener preparedListener = mp -> playButton.setOnClickListener(v -> {
            seekBar.setMax(mp.getDuration());
            if(mp.isPlaying()){
                // stop and give users the option to start again
                    mp.pause();
                    playButton.setText(R.string.play_text);

            }else {
                    mp.start();
                    playButton.setText(R.string.pause_text);

            }
        });
        mediaPlayer.setOnPreparedListener(preparedListener);
        mediaPlayer.prepareAsync();
//        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.correct);


        playButton = findViewById(R.id.playButton);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.pause();
            mediaPlayer.release();
        }

    }
}
package com.learningjavaandroid.playvideoexop;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {
    private StyledPlayerView playerView;
    private ExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.playerView);

    }

    //https://buildappswithpaulo.com/videos/outro_music.mp4
    @Override
    protected void onStart() {
        super.onStart();
        player = new ExoPlayer.Builder(MainActivity.this).build();
        playerView.setPlayer(player);

        MediaItem mediaItem = MediaItem.fromUri(Uri.parse("https://buildappswithpaulo.com/videos/outro_music.mp4"));

        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();



    }
}
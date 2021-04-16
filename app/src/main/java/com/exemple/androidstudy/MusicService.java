package com.exemple.androidstudy;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() { // 초기화
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.sound_exem); //음악 지정
        mediaPlayer.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { // 시작

        mediaPlayer.start(); // 음악 시작

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() { // 종료
        super.onDestroy();

        mediaPlayer.stop(); // 음악 종료
    }
}

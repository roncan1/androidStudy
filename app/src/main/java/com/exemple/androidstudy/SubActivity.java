package com.exemple.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {

    TextView text_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        text_test = findViewById(R.id.text_test);

        Intent intent = getIntent(); // 인텐트 받기
        String toss = intent.getStringExtra("toss"); // 변수에 값받아 저장
        text_test.setText(toss); // 변수값을 text_test에 세팅
    }
}

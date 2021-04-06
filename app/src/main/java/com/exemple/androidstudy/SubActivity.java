package com.exemple.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SubActivity extends AppCompatActivity {

    TextView text_test;
    ListView list_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        text_test = (TextView) findViewById(R.id.text_test);

        Intent intent = getIntent(); // 인텐트 받기
        String toss = intent.getStringExtra("toss"); // 변수에 값받아 저장
        text_test.setText(toss); // 변수값을 text_test에 세팅


        list_test = (ListView) findViewById(R.id.list_test);

        List<String> data = new ArrayList<>(); // 문자형으로 리스트 생성

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_2,data);
        //어뎁터 생성

        list_test.setAdapter(adapter);//어뎁터 세팅

        data.add("테스트 a"); //데이터 추가
        data.add("테스트 b"); //데이터 추가
        data.add("테스트 c"); //데이터 추가
        adapter.notifyDataSetChanged(); // 어뎁터를통해 추가한 데이터 세팅

    }
}

package com.exemple.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_move; // 버튼 생성
    EditText edit_test;
    ImageView toast_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //액티비티와 xml파일을 연결

        btn_move = (Button) findViewById(R.id.btn_move); //변수로 생성한 버튼과 xml속 버튼을 연결
        edit_test = (EditText) findViewById(R.id.edit_test);

        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = edit_test.getText().toString(); // 문자열로 a변수을 선언하여 edit_test의 값을 넣어준다
                Intent intent = new Intent(MainActivity.this,SubActivity.class); //인텐트를 생성하며 지금MainActivity에서 SubActivity로 넘어간다고 선언
                intent.putExtra("toss", a); // putExtra를 이용하여 toss라는 이름안에 a값을 넣기
                startActivity(intent); // 액티비티 이동
            }
        });

        toast_img = findViewById(R.id.toast_test);
        toast_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "토스트 테스트",Toast.LENGTH_LONG).show();
            }
        });


    }
}
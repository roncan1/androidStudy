package com.exemple.androidstudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    View drawerView;
    Button btn_start, btn_and, btn_dialog;
    Thread thread;
    Boolean isThread = false;
    TextView tv_dia_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_dialog = (Button)findViewById(R.id.btn_dialog);
        tv_dia_result = (TextView)findViewById(R.id.tv_dia_result);

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this); //dialog를 세팅하는 빌더를 생성
                ad.setIcon(R.mipmap.ic_launcher); // 이미지 지정 (기본 아이콘으로)
                ad.setTitle("제목"); // 제목지정
                ad.setMessage("dialog test"); // 내용지정

                //추가적인 구현
                final EditText et = new EditText(MainActivity.this); // EditText 생성
                ad.setView(et); // 팝업창에 EditText 세팅

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() { // '확인' 이라는 이름의 버튼 세팅
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { // '확인'을 누를경우

                        String result = et.getText().toString(); // 문자열 변수를 선언하여 et안에 써진 값을 저장
                        tv_dia_result.setText(result); // 액티비티에 텍스트뷰에 result 값을 세팅
                        dialogInterface.dismiss();  // 팝업창 닫기
                    }
                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() { // '취소' 이라는 이름의 버튼 세팅
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { // '최소'를 누를경우
                        dialogInterface.dismiss(); // 취소 이기 떄문에 아무런 동작 없이 팝업창 닫기
                    }
                });

                ad.show(); // btn_dialog를 눌렀을떄 팝업창을 show 해준다
            }
        });

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isThread = true; // 스레드가 실행되고있기에 값을 true로 변경

                thread = new Thread() { // 쓰레드를 생성
                  public void run() { // run이라는 메소드 생성
                      while(isThread) { // while문으로 isThread가 true일 경우 반복
                          try {
                              sleep(5000);  // 5초에 한번씩 쓰레드가 실행되도록 함
                          } catch (InterruptedException e) {
                              e.printStackTrace();
                          }                      // try / catch 로 예외처리를 해준다.
                          handler.sendEmptyMessage(0); // 핸들러에 메세지를 전송
                      }
                  }
                };
                thread.start(); // 스레드를 시작
            }
        });

        btn_and = (Button)findViewById(R.id.btn_and);  // 스레드 종료
        btn_and.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isThread = false;
            }
        });

        String logExem = "로그출력테스트";
        Log.e("MainActivity : ",logExem); // 로그 테스트

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        Button btn_open = (Button)findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        Button btn_close = (Button)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });



    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Toast.makeText(getApplicationContext(), "핸들러 테스트",Toast.LENGTH_SHORT).show();
        }
    };

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
}
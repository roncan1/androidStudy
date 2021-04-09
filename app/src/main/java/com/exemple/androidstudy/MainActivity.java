package com.exemple.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_move; // 버튼 생성
    EditText edit_test,et_savetest;

    ImageView toast_img;
    String save = "file";

    WebView webView_test;
    String url = "https://github.com/roncan1";

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

        et_savetest = (EditText)findViewById(R.id.ET_savetest);

        SharedPreferences sharedPreferences = getSharedPreferences(save, 0);
        // SharedPreferences 불러오기 만들어둔 /String save = "file";/을 인자값으로 넣어줌
        String value = sharedPreferences.getString("test", "");
        // 저장된 값불러오기
        et_savetest.setText(value);
        //불러온 값 원래데로 세팅

        webView_test = (WebView)findViewById(R.id.webview_test);
        webView_test.getSettings().setJavaScriptEnabled(true);  // 자바스크립트를 허용해주는가
        webView_test.loadUrl(url);                              // url 불러오기

        // 아래는 뷰를 여는것에 관한 추가적인 코드드
        webView_test.setWebChromeClient(new WebChromeClient());  // 크롬으로 실행
        webView_test.setWebViewClient(new WebViewClientClass()); // 따로 WebViewClient를 세팅해서 사용

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView_test.canGoBack()) { //
            webView_test.goBack();                                            // 웹뷰에서 뒤로가기를 눌렀을떄
            return true;                                                      // 뒤로 이동하도록 실행
        }                                                                     //
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() { // 액티비티가 파괴되었을때
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(save, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit(); //에디터 불러와서 연결
        String value = et_savetest.getText().toString(); //et_savetest의 값을 문자열로 가져와 저장
        editor.putString("test", value); // 에디터에 String형태로 test라는 이름으로 value값을 저장
        editor.commit(); // 실제적으로 세이브를 완료
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) { // 현재페이지에 url을 읽어오기 *알아두기
            view.loadUrl(url);
            return true;
        }
    }
}
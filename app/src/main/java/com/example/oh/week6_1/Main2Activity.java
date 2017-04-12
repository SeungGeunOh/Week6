package com.example.oh.week6_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5, e6;
    Button bt1, bt2;
    RadioButton rb1, rb2, rb3;
    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("나의 맛집");

        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        e3 = (EditText)findViewById(R.id.e3);
        e4 = (EditText)findViewById(R.id.e4);
        e5 = (EditText)findViewById(R.id.e5);
        e6 = (EditText)findViewById(R.id.e6);

        rb1 = (RadioButton)findViewById(R.id.rb1);
        rb2 = (RadioButton)findViewById(R.id.rb2);
        rb3 = (RadioButton)findViewById(R.id.rb3);

        bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.rb1 :
                rb1.setChecked(true);
                type = 0;
                break;
            case R.id.rb2 :
                rb2.setChecked(true);
                type = 1;
                break;
            case R.id.rb3 :
                rb3.setChecked(true);
                type = 2;
                break;
            case R.id.bt1 :
                finish();
                break;
            case R.id.bt2 :
                Intent intent = getIntent();

                String name = e1.getText().toString();
                String tel = e2.getText().toString();
                String menu1 = e3.getText().toString();
                String menu2 = e4.getText().toString();
                String menu3 = e5.getText().toString();
                String homepage = e6.getText().toString();

                if (name.length() == 0 || tel.length() == 0 || menu1.length() == 0 || menu2.length() == 0 ||
                        menu3.length() == 0 || homepage.length() == 0) {
                    Toast.makeText(getApplicationContext(), "처음부터 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    FoodData foodData = new FoodData(name, type, tel, menu1, menu2, menu3, homepage);
                    intent.putExtra("foodData", foodData);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }
}

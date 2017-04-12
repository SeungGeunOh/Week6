package com.example.oh.week6_1;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class Main3Activity extends AppCompatActivity {
    String name;
    int type;
    String tel;
    String menu1;
    String menu2;
    String menu3;
    String homepage;
    String TELL_FORMAT = "tel:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("나의 맛집");
        receiveData();
        setData();
    }

    public void receiveData(){
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        type = intent.getIntExtra("type", 111);
        tel = intent.getStringExtra("tel");
        menu1 = intent.getStringExtra("menu1");
        menu2 = intent.getStringExtra("menu2");
        menu3 = intent.getStringExtra("menu3");
        homepage = intent.getStringExtra("homepage");
    }
    public void setData(){
        ImageView iv = (ImageView) findViewById(R.id.mainImage);
        if (type == 0)
            iv.setImageResource(R.drawable.chicken);
        else if (type == 1)
            iv.setImageResource(R.drawable.hamburger);
        else if (type == 2)
            iv.setImageResource(R.drawable.pizza);

        TextView tName = (TextView) findViewById(R.id.name);
        TextView tTel = (TextView) findViewById(R.id.tel);
        TextView tMenu1 = (TextView) findViewById(R.id.mn1);
        TextView tMenu2 = (TextView) findViewById(R.id.mn2);
        TextView tMeun3 = (TextView) findViewById(R.id.mn3);
        TextView tHomepage = (TextView) findViewById(R.id.hp);
        TextView tDate = (TextView) findViewById(R.id.date);

        tName.setText(name);
        tTel.setText(tel);
        tMenu1.setText(menu1);
        tMenu2.setText(menu2);
        tMeun3.setText(menu3);
        tHomepage.setText(homepage);
        tDate.setText(getDateString());
    }

    @TargetApi(Build.VERSION_CODES.N)
    public String getDateString(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat curDateFormat = new SimpleDateFormat("yyyy년 mm월 dd일");
        String str = curDateFormat.format(date);
        return str;
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.cc :
                finish();
                break;
            case R.id.call :
                startActivity(new Intent(Intent.ACTION_DIAL,
                        Uri.parse(TELL_FORMAT + tel.substring(0,3) + "-"
                        + tel.substring(3,7) + "-" + tel.substring(7))));
                break;
            case R.id.uri :
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(homepage)));
                break;
        }
    }
}


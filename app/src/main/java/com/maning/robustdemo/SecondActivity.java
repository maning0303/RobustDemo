package com.maning.robustdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.meituan.robust.patch.annotaion.Modify;

public class SecondActivity extends AppCompatActivity {

    private TextView tv_show;

    @Override
    @Modify
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv_show = findViewById(R.id.tv_show);


//        tv_show.setText("我是Bug");
        tv_show.setText("我是Bug-Fix");
    }
}

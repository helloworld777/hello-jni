package com.example.lu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lu.AopTest;
import com.example.lu.R;

public class SuperTextViewActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_text_view);

        setTitle("DraggerTestActivity");

        sayHello("hahaha");


        AopTest aopTest=new AopTest();
        aopTest.testAop(1,2);
    }
    public String sayHello(String a){
        return "hello....."+a;
    }

}

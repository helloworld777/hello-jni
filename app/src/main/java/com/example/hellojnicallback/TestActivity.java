package com.example.hellojnicallback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hellojnicallback.adapter.LuAdapter;
import com.example.hellojnicallback.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {


    ListView listView;
    List<ActivityInfo> activityInfoList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        listView= (ListView) findViewById(R.id.listView);

        activityInfoList.add(new ActivityInfo("AndFix示例",MainActivity.class));
        activityInfoList.add(new ActivityInfo("Aidl示例",TestAidl2Activity.class));
        activityInfoList.add(new ActivityInfo("ArcMenu示例",AcrMenuActivity.class));
        LuAdapter<ActivityInfo> adapter=new LuAdapter<ActivityInfo>(this,activityInfoList,android.R.layout.simple_list_item_1) {
            @Override
            public void convert(ViewHolder helper, ActivityInfo item) {
                helper.setString(android.R.id.text1,item.name);
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(TestActivity.this,activityInfoList.get(position).clazz));
            }
        });
    }
    static class ActivityInfo{
        String name;
        Class clazz;
        public ActivityInfo(String name,Class clazz){
            this.name=name;
            this.clazz=clazz;
        }
    }

}
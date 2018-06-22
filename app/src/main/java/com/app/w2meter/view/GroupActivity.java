package com.app.w2meter.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.app.w2meter.MainActivity;
import com.app.w2meter.R;
import com.app.w2meter.adapter.AdapterContact;
import com.app.w2meter.adapter.AdapterGraph;
import com.app.w2meter.adapter.AdapterUser;

import java.util.ArrayList;
import java.util.HashMap;


public class GroupActivity extends AppCompatActivity {
    ListView listView1;
    AdapterUser adapterUser;
    ArrayList<HashMap<String,String>> arrayUser;

    ListView listView2;
    AdapterContact adapterContact;
    ArrayList<HashMap<String,String>> arrayContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        ImageButton ib_back=findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GroupActivity.this,MainActivity.class));
                finish();
            }
        });

        arrayUser=new ArrayList<>();
        HashMap<String,String> list=new HashMap<>();
        list.put("name","User 1");
        arrayUser.add(list);

        HashMap<String,String> list2=new HashMap<>();
        list2.put("name","User 2");
        arrayUser.add(list2);

        HashMap<String,String> list3=new HashMap<>();
        list3.put("name","User 3");
        arrayUser.add(list3);

        adapterUser=new AdapterUser(GroupActivity.this,arrayUser);
        listView1=findViewById(R.id.listView1);
        listView1.setAdapter(adapterUser);


        arrayContact=new ArrayList<>();
        HashMap<String,String> list21=new HashMap<>();
        list21.put("name","contact 1");
        arrayContact.add(list21);

        HashMap<String,String> list22=new HashMap<>();
        list22.put("name","contact 2");
        arrayContact.add(list22);

        HashMap<String,String> list23=new HashMap<>();
        list23.put("name","contact 3");
        arrayContact.add(list23);

        adapterContact=new AdapterContact(GroupActivity.this,arrayContact);
        listView2=findViewById(R.id.listView2);
        listView2.setAdapter(adapterContact);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(GroupActivity.this,MainActivity.class));
        finish();
    }
}

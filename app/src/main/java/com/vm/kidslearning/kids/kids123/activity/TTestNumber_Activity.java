package com.vm.kidslearning.kids.kids123.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.vm.kidslearning.kids.kids123.R;

public class TTestNumber_Activity extends AppCompatActivity {
    String id;
    String tnum;
    public static String catName;
    Button btn1, btn2;
    public static int tp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_test_number);
        id = getIntent().getStringExtra("id");
        if(tp ==0 ){
            catName = getIntent().getStringExtra("cat");
        }
        tp =0;
        setTitle(catName);


        btn1 = findViewById(R.id.btntest1);
        btn2 = findViewById(R.id.btntest2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(TTestNumber_Activity.this, TQuestion_Activity.class);
                in.putExtra("id",id);
                tnum = "1";
                in.putExtra("tnum",tnum);
                in.putExtra("cat",getIntent().getStringExtra("cat"));
                startActivity(in);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(TTestNumber_Activity.this, TQuestion_Activity.class);
                in.putExtra("id",id);
                tnum = "2";
                in.putExtra("tnum",tnum);
                in.putExtra("cat",getIntent().getStringExtra("cat"));
                startActivity(in);
            }
        });
    }
}
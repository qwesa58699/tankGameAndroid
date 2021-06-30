package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class step3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step3);
        Button mtank = findViewById(R.id.mtankBTN3);
        ImageButton mback = findViewById(R.id.mbackIBTN3);
        ImageButton muser = findViewById(R.id.muserIBTN3);
        mtank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(step3.this , step3.class);
                startActivity(intent);
            }
        });
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(step3.this , step1.class);
                startActivity(intent);
            }
        });

        muser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(step3.this , step4.class);
                startActivity(intent);
            }
        });
    }
}
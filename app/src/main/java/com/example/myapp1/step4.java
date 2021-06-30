package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class step4 extends AppCompatActivity {
    String[] countryArray = new String[]{"","Taiwan","Korea","Japan"};
    String[] genderArray = new String[]{"","男","女"};
    String acc,nname,country,gender;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step4);
        TextView macc = findViewById(R.id.maccVT4_1);
        TextView mnname = findViewById(R.id.mnnameTV4_1);
        TextView mcountry = findViewById(R.id.mcountryTV4_1);
        TextView mgender = findViewById(R.id.mgenderTV4_1);
        step1 st1 = new step1();
        PreparedStatement ps = null;
        mssql ms = new mssql();
        ImageButton mback = findViewById(R.id.mbackIBTN4);

        try {
            ps = ms.connect.prepareStatement("select account, nickname, country, gender from [dbo].[login];");
            ResultSet tmp = ps.executeQuery();
            while (tmp.next()) {
                macc.setText(tmp.getString("account"));
                mnname.setText(tmp.getString("nickname"));
                mcountry.setText(countryArray[Integer.parseInt(tmp.getString("country"))]);
                mgender.setText(genderArray[Integer.parseInt(tmp.getString("gender"))]);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(step4.this, step3.class);
                startActivity(intent);
            }
        });




    }
}
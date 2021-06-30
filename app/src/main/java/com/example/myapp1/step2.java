package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class step2 extends AppCompatActivity  {
    int country;
    int gender = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step2);


        EditText macc = (EditText) findViewById(R.id.maccET2);
        EditText mpwd = (EditText) findViewById(R.id.mpwdET2);
        EditText mnname = (EditText) findViewById(R.id.mnnameET2);
        ImageButton mback = (ImageButton) findViewById(R.id.mbackIBTN2);
        ImageButton msubmit = (ImageButton) findViewById(R.id.msubmitIBTN2);
        RadioButton mmgender = (RadioButton) findViewById(R.id.mmaleRB2);
        RadioButton mfmgender = (RadioButton) findViewById(R.id.mfmaleRB2);
        RadioGroup mrg = (RadioGroup) findViewById(R.id.mgenderRG2);
        Spinner mcountry = (Spinner) findViewById(R.id.mcountrySPN2);
        TextView mprompt = findViewById(R.id.mpromptTV2);


        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(step2.this, step1.class);
                startActivity(intent);
            }
        });

        mcountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                country = adapterView.getSelectedItemPosition()+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        mrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                switch (mrg.getCheckedRadioButtonId()){
                    case R.id.mfmaleRB2:
                        gender = 2;
                        break;
                    case R.id.mmaleRB2:
                        gender = 1;
                        break;
                }
            }
        });


        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreparedStatement ps = null;
                try {


                    int x = 0;
                    mssql ms = new mssql();
                    ps = ms.connect.prepareStatement("select account from [dbo].[appuser]");
                    mprompt.setText("123");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        if (rs.getString("account").equals(macc.getText().toString())) {
                            mprompt.setText("此帳號已經被註冊");
                            x++;
                        }
                    }
                    if (x == 0) {
                        mprompt.setText("註冊成功");
                        ps = ms.connect.prepareStatement("insert into [dbo].[appuser] values(?,?,?,?,?)");
                        ps.setString(1, macc.getText().toString());
                        ps.setString(2, mpwd.getText().toString());
                        ps.setString(3,mnname.getText().toString());
                        ps.setInt(4,country);
                        ps.setInt(5, gender);

                        ps.executeUpdate();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }
}


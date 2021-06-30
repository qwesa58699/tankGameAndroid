package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class step1 extends AppCompatActivity {
    HashMap<String, String> user = new HashMap<String, String>();
    EditText macc, mpwd;
    Button mjoin, mlogin;
    TextView mprompt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step1);

        macc = findViewById(R.id.maccET1);
        mpwd = findViewById(R.id.mpwdET1);
        mjoin = findViewById(R.id.mjoingBTN1);
        mlogin = findViewById(R.id.mloginBTN1);
        mprompt = findViewById(R.id.mpromptTV1);
        mjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(step1.this, step2.class);
                startActivity(intent);
            }
        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PreparedStatement ps = null;
                mssql ms = new mssql();
                try {
                    ps = ms.connect.prepareStatement("select account, password from [dbo].[appuser]");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        user.put(rs.getString("account"), rs.getString("password"));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (user.containsKey(macc.getText().toString())) {
                    if (user.get(macc.getText().toString()).equalsIgnoreCase(mpwd.getText().toString())) {
                        try {
                            ms.connect.prepareStatement("TRUNCATE TABLE [dbo].[login];").executeQuery();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {

                            mprompt.setText("登入成功");
                            PreparedStatement ps1 = null;
                            ps1 = ms.connect.prepareStatement("select account, nickname, country, gender from [dbo].[appuser] where account = '"+macc.getText().toString()+"'");
                            ResultSet tmp1 = ps1.executeQuery();
                            while (tmp1.next()) {
                                ps1 = ms.connect.prepareStatement("insert into [dbo].[login] values(?,?,?,?)");
                                ps1.setString(1, tmp1.getString("account"));
                                ps1.setString(2,tmp1.getString("nickname"));
                                ps1.setInt(3,Integer.parseInt(tmp1.getString("country")));
                                ps1.setInt(4, Integer.parseInt(tmp1.getString("gender")));
                                ps1.executeUpdate();
                            }

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        Intent intent = new Intent();
                        intent.setClass(step1.this, step3.class);
                        startActivity(intent);

                    } else {
                        mprompt.setText("密碼錯誤");
                    }

                } else {
                    mprompt.setText("帳號錯誤");
                }
            }
        });
    }
    public String uacc() {
        return macc.getText().toString();
    }

}

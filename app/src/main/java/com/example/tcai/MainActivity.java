package com.example.tcai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button login;
    EditText username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("spp",Context.MODE_PRIVATE);

        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userN = username.getText().toString().trim();
                String passW = password.getText().toString().trim();

                String user1N = "Ram";
                String user1C = "Computing";
                String user2N = "Shyam";
                String user2C = "Computing";
                String user3N = "Hari";
                String user3C = "BBA";
                String user4N = "Bishnu";
                String user4C = "Environment Science";
                String user5N = "Shiva";
                String user5C = "Environment Science";

                if(userN.equals("") || passW.equals("")){
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                if (userN.equals("user1") && passW.equals("pass1")||
                        userN.equals("user2") && passW.equals("pass2")||
                        userN.equals("user3") && passW.equals("pass3")||
                        userN.equals("user4") && passW.equals("pass4")||
                        userN.equals("user5") && passW.equals("pass5")){

                    SharedPreferences.Editor edt = sp.edit();

                    if(userN.equals("user1")) {
                        edt.putString("Name", user1N);
                        edt.putString("Faculty", user1C);
                        edt.commit();
                    }
                    else  if(userN.equals("user2")) {
                        edt.putString("Name", user2N);
                        edt.putString("Faculty", user2C);
                        edt.commit();
                    }
                    else  if(userN.equals("user3")) {
                        edt.putString("Name", user3N);
                        edt.putString("Faculty", user3C);
                        edt.commit();
                    }
                    else  if(userN.equals("user4")) {
                        edt.putString("Name", user4N);
                        edt.putString("Faculty", user4C);
                        edt.commit();
                    }
                    else if(userN.equals("user5")) {
                        edt.putString("Name", user5N);
                        edt.putString("Faculty", user5C);
                        edt.commit();
                    }



                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(MainActivity.this, DecisionActivity.class);
                    it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(it);

                }else {
                    Toast.makeText(MainActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                }
            }}
        });


    }
}
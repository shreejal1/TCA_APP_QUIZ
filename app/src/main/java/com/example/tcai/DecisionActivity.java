package com.example.tcai;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DecisionActivity extends AppCompatActivity {


    TextView name, faculty;
    Button quizcont, mathcont, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);

        name = findViewById(R.id.name);
        faculty = findViewById(R.id.faculty);
        quizcont = findViewById(R.id.quizcont);
        mathcont = findViewById(R.id.mathcont);
        logout = findViewById(R.id.logout);

        SharedPreferences sp = getSharedPreferences("spp",Context.MODE_PRIVATE);

        String Name1 = sp.getString("Name", "");
        String Faculty1 = sp.getString("Faculty","");
        name.setText("Name: "+Name1);
        faculty.setText("Faculty: "+Faculty1);

        Log.d("name", "onCreate: "+ Name1);

        quizcont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DecisionActivity.this, QuizActivity.class);
                startActivity(it);
            }
        });
        mathcont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DecisionActivity.this, MathQuizActivity.class);
                startActivity(it);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(mathcont.getContext());
                ab.setCancelable(true);
                ab.setTitle("Logout");
                ab.setMessage("Are you sure to logout " + Name1 + "?");
                ab.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                SharedPreferences.Editor edt = sp.edit();
                                edt.putString("Name", "");
                                edt.putString("Faculty", "");
                                edt.commit();
                                Intent it = new Intent(DecisionActivity.this, MainActivity.class);
                                startActivity(it);
                                finish();
                                Toast.makeText(DecisionActivity.this, Name1 +" logged out", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                ab.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = ab.create();
                dialog.show();
            }
        });
    }
}
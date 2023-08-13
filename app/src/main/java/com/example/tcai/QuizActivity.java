package com.example.tcai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_WRITE_STORAGE = 112;
    TextView name, faculty, quest;

    Button submit, ansa, ansb, ansc, ansd;
    ImageView back;
    int sc = 0;
    String answer = "";
    int questionnumber = 0;
    int total = quizqa.questions.length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        name = findViewById(R.id.name);
        faculty = findViewById(R.id.faculty);
        quest = findViewById(R.id.quest);
        ansa = findViewById(R.id.ansa);
        ansb = findViewById(R.id.ansb);
        ansc = findViewById(R.id.ansc);
        ansd = findViewById(R.id.ansd);
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);

        ansa.setOnClickListener(this);
        ansb.setOnClickListener(this);
        ansc.setOnClickListener(this);
        ansd.setOnClickListener(this);
        submit.setOnClickListener(this);



        SharedPreferences sp = getSharedPreferences("spp", Context.MODE_PRIVATE);

        String Name1 = sp.getString("Name", "");
        String Faculty1 = sp.getString("Faculty","");

        name.setText("Name: "+Name1);
        faculty.setText("Faculty: "+Faculty1);
        Log.d("name", "onCreate: "+ Name1);
        newquestions();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QuizActivity.this, DecisionActivity.class);
                startActivity(it);
                finish();
            }
        });

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                answer = answ.getText().toString().trim();
//
//                if(questionnumber == 10){
//                    completion();
//                    return;
//                }else{
//                    if(answer.equals(quizqa.answers[questionnumber])) {
//                        sc++;
//                    }
//                    questionnumber++;
//                    newquestions();
//                }}
//        });

    }
    @Override
    public void onClick(View view) {

        ansa.setBackgroundColor(Color.GRAY);
        ansb.setBackgroundColor(Color.GRAY);
        ansc.setBackgroundColor(Color.GRAY);
        ansd.setBackgroundColor(Color.GRAY);

        Button clicked = (Button) view;
        if(clicked.getId()==R.id.submit){
            if(answer.equals(quizqa.answers[questionnumber])) {
                sc++;
            }
            questionnumber++;
            newquestions();
        }
        else{
            answer = clicked.getText().toString();
            clicked.setBackgroundColor(Color.RED);
        }
    }

    void newquestions(){
        if(questionnumber == total){
            completion();
            return;
        }
        quest.setText(quizqa.questions[questionnumber]);
        ansa.setText(quizqa.options[questionnumber][0]);
        ansb.setText(quizqa.options[questionnumber][1]);
        ansc.setText(quizqa.options[questionnumber][2]);
        ansd.setText(quizqa.options[questionnumber][3]);

    }
    void completion(){
        SharedPreferences sp = getSharedPreferences("spp", Context.MODE_PRIVATE);

        String Name1 = sp.getString("Name", "");
//        String Faculty1 = sp.getString("Faculty","");
        Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(this).setTitle("Score").setMessage("Your score is: "+ sc).setPositiveButton("Save", (dialogInterface, i) -> writeToFile(Name1+sc+".txt",sc)).setCancelable(false).show();
    }
    void exit(){
        sc = 0;
        questionnumber = 0;
        newquestions();
    }
    private void writeToFile(String name, int score){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
            return;
        }
        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir, name);
        try{
            FileWriter fw = new FileWriter(file);
            fw.append((char) score);
            fw.flush();
            fw.close();
            Toast.makeText(this, "Saved as: "+file, Toast.LENGTH_SHORT).show();
            Intent it = new Intent(QuizActivity.this, DecisionActivity.class);
            startActivity(it);
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
        }
    }
}


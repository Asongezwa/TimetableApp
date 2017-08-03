package com.example.administrator.timetableapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.timetableapp.timetable.activities.student.RegisterStudentActivity;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private TextView welcomeNote;
    private TextView info;
    private Button login;
    private int counter =5;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.etUserName);
        password = (EditText) findViewById(R.id.etPassword);
        info = (TextView) findViewById(R.id.tvInfo);
        welcomeNote = (TextView) findViewById(R.id.tvWelcomeNote);
        login = (Button) findViewById(R.id.btnSignIn);
        info.setText("No of attempts remaining: "+String.valueOf(counter));

        login.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    validate(userName.getText().toString().trim(), password.getText().toString().trim());
                }
    });

}

    private void validate (String userName,String userPassword){


        //if(userName.equals("Admin")  ){
            startActivity(new Intent(this, RegisterStudentActivity.class));
        //}
        /*
        else{
            counter--;
            info.setText("No of attempts remaining "+String.valueOf(counter));
            if(counter==0){
                login.setEnabled(false);
            }
        }*/

    }


}

package com.example.administrator.timetableapp.timetable.services.student.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.administrator.timetableapp.timetable.conf.util.App;
import com.example.administrator.timetableapp.timetable.domain.student.Student;
import com.example.administrator.timetableapp.timetable.repository.student.Impl.StudentRepositoryImpl;
import com.example.administrator.timetableapp.timetable.repository.student.StudentRepository;
import com.example.administrator.timetableapp.timetable.services.student.StudentLogin;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017/07/31.
 */

public class StudentLoginImpl extends Service implements StudentLogin {

    final private StudentRepository repo;
    private static StudentLoginImpl service = null;

    public static StudentLoginImpl getInstance(){
        if(service == null)
            service = new StudentLoginImpl();
        return service;
    }

    private final IBinder localBinder = new AccountServiceLocalBinder();


    public StudentLoginImpl(){
        repo = new StudentRepositoryImpl(App.getAppContext());
    }


    @Override
    public String activateAccount(String username, String password) {
        String account ="NOT ACTIVATED";
        if(!username.equals(null)&& !password.equals(null)) {
            Student studentAccount = new Student.StudentBuilder()
                    .setUserName(username)
                    .setPassword(password)
                    .build();
            try {
                repo.save(studentAccount);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "ACTIVATED";
        }else{
            return account;
        }
    }



    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }


    public class AccountServiceLocalBinder extends Binder {
        public StudentLoginImpl getService(){

            return StudentLoginImpl.this;
        }
    }
}



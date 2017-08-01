package com.example.administrator.timetableapp.timetable.services.subject.Impl;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.IBinder;
import android.widget.ArrayAdapter;

import com.example.administrator.timetableapp.timetable.conf.util.App;
import com.example.administrator.timetableapp.timetable.repository.subject.Impl.SubjectRepositoryImpl;
import com.example.administrator.timetableapp.timetable.repository.subject.SubjectRepository;
import com.example.administrator.timetableapp.timetable.services.subject.ViewSubjects;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/07/31.
 */

public class ViewAllSubjects extends Service implements ViewSubjects {

    final private SubjectRepository repo;
    private static ViewAllSubjects service = null;
    private final IBinder localBinder = new ViewAllSubjectsLocalBinder();

    public ViewAllSubjects() {
        repo = new SubjectRepositoryImpl(App.getAppContext());
    }

    public static ViewAllSubjects getInstance(){
        if(service == null)
            service = new ViewAllSubjects();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public ArrayAdapter<String> displayAllSubject() {
        ArrayAdapter adapter;
        ArrayList<String> subjectArray;
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        subjectArray = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor = repo.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (cursor.moveToNext()) {
            do {
                String id = String.valueOf(cursor.getLong(0));
                String subName = cursor.getString(1);
                String subCode = cursor.getString(2);


                subjectArray.add(id);
                subjectArray.add(subName);
                subjectArray.add(subCode);


            } while (cursor.moveToNext());
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjectArray);
            return (adapter);
        }
        else
            return null;
    }


    public class ViewAllSubjectsLocalBinder extends Binder {
        public ViewAllSubjects getService(){

            return ViewAllSubjects.this;
        }
    }
}



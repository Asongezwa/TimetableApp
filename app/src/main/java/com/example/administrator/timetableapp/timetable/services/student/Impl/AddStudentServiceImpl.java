package com.example.administrator.timetableapp.timetable.services.student.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.administrator.timetableapp.timetable.conf.util.App;
import com.example.administrator.timetableapp.timetable.domain.student.Student;
import com.example.administrator.timetableapp.timetable.repository.student.Impl.StudentRepositoryImpl;
import com.example.administrator.timetableapp.timetable.repository.student.StudentRepository;
import com.example.administrator.timetableapp.timetable.services.student.AddStudentService;

/**
 * Created by Administrator on 2017/07/31.
 */

public class AddStudentServiceImpl extends IntentService implements AddStudentService {
    private final StudentRepository repo;
    private static AddStudentServiceImpl service = null;

    private static final String ACTION_ADD ="package com.example.administrator.timetableapp.timetable.services.student.Impl.action.ADD";
    private static final String ACTION_UPDATE ="package com.example.administrator.timetableapp.timetable.services.student.Impl.action.UPDATE";
    private static final String ACTION_DELETE ="package com.example.administrator.timetableapp.timetable.services.student.Impl.action.DELETE";

    private static final String EXTRA_ADD = "package com.example.administrator.timetableapp.timetable.services.student.Impl.extra.ADD";
    private static final String EXTRA_UPDATE = "package com.example.administrator.timetableapp.timetable.services.student.Impl.extra.UPDATE";
    private static final String EXTRA_DELETE = "package com.example.administrator.timetableapp.timetable.services.student.Impl.extra.DELETE";


    public static AddStudentServiceImpl getInstance(){
        if(service == null)
            service = new AddStudentServiceImpl();
        return service;
    }

    public AddStudentServiceImpl() {
        super("AddStudentServiceImpl");
        repo = new StudentRepositoryImpl(getBaseContext());
    }

    @Override
    public void addStudent(Context context, Student student) {
        Intent intent = new Intent(context,AddStudentServiceImpl.class);
        intent.setAction("add");
        intent.putExtra(EXTRA_ADD, student);
        context.startService(intent);
    }

    @Override
    public void deleteStudent(Context context, Student student) {
        Intent intent = new Intent(context,AddStudentServiceImpl.class);
        intent.setAction(ACTION_DELETE);
        intent.putExtra(EXTRA_DELETE, student);
        context.startService(intent);
    }

    @Override
    public void updateStudent(Context context, Student student) {
        Intent intent = new Intent(context,AddStudentServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, student);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent != null) {
            final String action =  intent.getAction().trim();
            if(action.equalsIgnoreCase("add")) {
                Student addStudent = (Student) intent.getSerializableExtra(EXTRA_ADD);
                add(addStudent);
            }
            else
            if(ACTION_UPDATE.equals(action)){
                Student updateStudent = (Student) intent.getSerializableExtra(EXTRA_UPDATE);
                update(updateStudent);

            }
            else
            if(ACTION_DELETE.equals(action)){
                Student deleteStudent = (Student) intent.getSerializableExtra(EXTRA_DELETE);
                delete(deleteStudent);
            }
        }

    }

    private void delete(Student student){
        try{
            repo.delete(student);
            Toast.makeText(App.getAppContext(), "Student has been removed", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void update(Student student){
        try{
            repo.update(student);
            Toast.makeText(App.getAppContext(), "Student has been updated", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void add(Student student){
        try {
            //StudentRepository repo = new StudentRepositoryImpl(getBaseContext());
            repo.save(student);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}



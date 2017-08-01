package com.example.administrator.timetableapp.timetable.services.subject.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.administrator.timetableapp.timetable.conf.util.App;
import com.example.administrator.timetableapp.timetable.domain.subject.Subject;
import com.example.administrator.timetableapp.timetable.repository.subject.Impl.SubjectRepositoryImpl;
import com.example.administrator.timetableapp.timetable.repository.subject.SubjectRepository;
import com.example.administrator.timetableapp.timetable.services.subject.AddSubjectService;

/**
 * Created by Administrator on 2017/07/31.
 */

public class AddSubjectServiceImpl extends IntentService implements AddSubjectService {
    private final SubjectRepository repository;
    private static AddSubjectServiceImpl service = null;

    private static final String ACTION_ADD ="package com.example.administrator.timetableapp.timetable.services.subject.Impl.action.ADD";
    private static final String ACTION_UPDATE ="package com.example.administrator.timetableapp.timetable.services.subject.Impl.action.UPDATE";
    private static final String ACTION_DELETE ="package com.example.administrator.timetableapp.timetable.services.subject.Impl.action.DELETE";


    private static final String EXTRA_ADD = "package com.example.administrator.timetableapp.timetable.services.subject.Impl.extra.ADD";
    private static final String EXTRA_UPDATE = "package com.example.administrator.timetableapp.timetable.services.subject.Impl.extra.UPDATE";
    private static final String EXTRA_DELETE = "package com.example.administrator.timetableapp.timetable.services.subject.Impl.extra.DELETE";


    public AddSubjectServiceImpl() {
        super("AddSubjectServiceImpl");
        repository = new SubjectRepositoryImpl(App.getAppContext());
    }

    public static AddSubjectServiceImpl getInstance(){
        if(service == null)
            service = new AddSubjectServiceImpl();
        return service;
    }

    @Override
    public void addSubject(Context context, Subject subject) {
        Intent intent = new Intent(context,AddSubjectServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD,subject);
        context.startService(intent);
    }

    @Override
    public void updateSubject(Context context, Subject subject) {
        Intent intent = new Intent(context,AddSubjectServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE,subject);
        context.startService(intent);
    }

    @Override
    public void deleteSubject(Context context, Subject subject) {
        Intent intent = new Intent(context,AddSubjectServiceImpl.class);
        intent.setAction(ACTION_DELETE);
        intent.putExtra(EXTRA_DELETE,subject);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent != null) {
            final String action = intent.getAction();
            if(ACTION_ADD.equals(action)) {
                Subject subjectResources = (Subject) intent.getSerializableExtra(EXTRA_ADD);
                add(subjectResources);
            }
            else
            if(ACTION_UPDATE.equals(action)){
                Subject updateSubject =(Subject)intent.getSerializableExtra(EXTRA_UPDATE);
                update(updateSubject);
            }
            else
            if(ACTION_DELETE.equals(action)){
                Subject deleteSubject = (Subject)intent.getSerializableExtra(EXTRA_DELETE);
                delete(deleteSubject);
            }
        }
    }

    private void update(Subject subject){
        try{
            repository.update(subject);
            Toast.makeText(App.getAppContext(),"Subject has been updated",Toast.LENGTH_LONG).show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void delete(Subject subject){
        try{
            repository.delete(subject);
            Toast.makeText(App.getAppContext(),"Subject has been removed",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void add(Subject subject){
        try{
            Subject addSubject = new Subject.SubjectBuilder()
                    .setSubCode(subject.getSubCode())
                    .setSubName(subject.getSubName())
                    .build();
            repository.save(addSubject);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}




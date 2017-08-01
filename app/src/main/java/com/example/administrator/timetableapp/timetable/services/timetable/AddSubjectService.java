package com.example.administrator.timetableapp.timetable.services.timetable;

import android.content.Context;

import com.example.administrator.timetableapp.timetable.domain.subject.Subject;

/**
 * Created by Administrator on 2017/07/31.
 */

public interface AddSubjectService {
    void addSubject(Context context, Subject subject);
    void updateSubject(Context context,Subject subject);
    void deleteSubject(Context context,Subject subject);
}

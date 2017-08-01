package com.example.administrator.timetableapp.timetable.services.student;

import android.content.Context;

import com.example.administrator.timetableapp.timetable.domain.student.Student;

/**
 * Created by Administrator on 2017/07/31.
 */

public interface AddStudentService {
    void addStudent(Context context, Student student);
    void deleteStudent(Context context,Student student);
    void updateStudent(Context context,Student student);
}

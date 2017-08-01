package com.example.administrator.timetableapp.timetable.factories;


import com.example.administrator.timetableapp.timetable.domain.student.Student;

/**
 * Created by Administrator on 2017/07/25.
 */

public class StudentFactory {
    public static Student getStudent(String userName, String password, long studentID){

        return new Student.StudentBuilder()

                .setUserName(userName)
                .setPassword(password)
                .setStudentID(studentID)
                .build();

    }

}

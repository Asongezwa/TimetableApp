package com.example.administrator.timetableapp.timetable.factories;

import com.example.administrator.timetableapp.timetable.domain.subject.Subject;

/**
 * Created by Administrator on 2017/07/25.
 */

public class SubjectFactory {
    public static Subject getSubject(String subName, String subCode){

        return new Subject.SubjectBuilder()

                .setSubName(subName)
                .setSubCode(subCode)
                .build();

    }

}

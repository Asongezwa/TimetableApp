package com.example.administrator.timetableapp.timetable.factories;

import com.example.administrator.timetableapp.timetable.domain.timetable.Timetable;

/**
 * Created by Administrator on 2017/07/25.
 */

public class TimetableFactory {
    public static Timetable getTimetable(String scheduledActivity1, String scheduledActivity2, String scheduledActivity3, String scheduledActivity4, String scheduledActivity5) {

        return new Timetable.TimetableBuilder()
                .setScheduledActivity1(scheduledActivity1).setScheduledActivity2(scheduledActivity2)
                .setScheduledActivity3(scheduledActivity3)
                .setScheduledActivity4(scheduledActivity4)
                .setScheduledActivity5(scheduledActivity5)
                .build();

    }

}
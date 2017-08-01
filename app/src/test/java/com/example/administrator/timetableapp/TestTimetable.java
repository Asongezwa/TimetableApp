package com.example.administrator.timetableapp.timetable.domain.subject;

import com.example.administrator.timetableapp.timetable.domain.timetable.Timetable;
import com.example.administrator.timetableapp.timetable.factories.TimetableFactory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2017/07/26.
 */

public class TestTimetable {
    @Test
    public void createTimetable() throws Exception {
        Timetable timetable = TimetableFactory.getTimetable("PHY152","FREE","MAT211","CSC211","FREE");
        assertEquals("PHY152",timetable.getScheduledActivity1());
    }
    @Test
    public void updateTimetable() throws Exception {

        Timetable timetable = TimetableFactory.getTimetable("MAT211","CSC211","PHY152","FREE","FREE");
        assertEquals("FREE",timetable.getScheduledActivity4());

        Timetable updateTimetable = new Timetable.TimetableBuilder()
                .copy(timetable)
                .setScheduledActivity2("FREE")
                .build();
        assertEquals("FREE",updateTimetable.getScheduledActivity2());
        assertNotNull(updateTimetable);

    }
}

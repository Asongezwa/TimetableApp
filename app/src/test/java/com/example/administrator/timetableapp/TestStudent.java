package com.example.administrator.timetableapp;

import com.example.administrator.timetableapp.timetable.domain.student.Student;
import com.example.administrator.timetableapp.timetable.factories.StudentFactory;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2017/07/25.
 */

public class TestStudent {
    @Test
    public void createStudent() throws Exception {
        Student student = StudentFactory.getStudent("Asongezwa","aso123",12);
        assertEquals("Asongezwa",student.getUserName());
    }
    @Test
    public void updateStudent() throws Exception {

        Student student = StudentFactory.getStudent("Asongezwa","aso123",12);
        assertEquals("Asongezwa",student.getUserName());

        Student updateStudent = new Student.StudentBuilder()
                .copy(student)
                .setUserName("Siraaj")
                .build();
        assertEquals("Siraaj",updateStudent.getUserName());
        assertNotNull(updateStudent);

    }
}

package com.example.administrator.timetableapp.timetable.domain.subject;

import com.example.administrator.timetableapp.timetable.factories.SubjectFactory;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.junit.Test;

/**
 * Created by Administrator on 2017/07/26.
 */

public class TestSubject {
   @Test
    public void createSubject() throws Exception {
        Subject subject = SubjectFactory.getSubject("MATH","MAT211");
        assertEquals("MATH",subject.getSubName());
    }
    @Test
    public void updateSubject() throws Exception {

        Subject subject = SubjectFactory.getSubject("MATH","MAT211");
        assertEquals("MATH",subject.getSubName());

        Subject updateSubject = new Subject.SubjectBuilder()
                .copy(subject)
                .setSubName("PHYSICS")
                .setSubCode("PHY152")
                .build();
        assertEquals("PHYSICS",updateSubject.getSubName());
        assertNotNull(updateSubject);

    }
}

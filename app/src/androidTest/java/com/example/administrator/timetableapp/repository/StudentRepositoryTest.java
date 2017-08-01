package com.example.administrator.timetableapp.repository;

import android.test.AndroidTestCase;

import com.example.administrator.timetableapp.timetable.domain.student.Student;
import com.example.administrator.timetableapp.timetable.repository.student.Impl.StudentRepositoryImpl;
import com.example.administrator.timetableapp.timetable.repository.student.StudentRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Administrator on 2017/07/27.
 */

public class StudentRepositoryTest extends AndroidTestCase{
    public static final String TAG = "STUDENT TEST";
    private Long id;

    public  void testCreateUpdateDelete() throws Exception {
        StudentRepository repo = new StudentRepositoryImpl(this.getContext());

        //create object

        Student createEntity = new Student.StudentBuilder()
                .setUserName("asongezwa@gmail.com")
                .setPassword("zikhos20030205")
                .build();

        //write object to database

        Student insertedEntity = repo.save(createEntity);
        id = insertedEntity.getStudentID();
        Assert.assertNotNull(TAG + " CREATE",insertedEntity);

        //Read all
        Set<Student> students = repo.findAll();
        Assert.assertNotNull(TAG + " READ ALL", students.size()>0);

        //Read entity
        Student entity = repo.findByid(id);


        //UPDATE ENTITY
        Student updateEntity = new Student.StudentBuilder()
                .copy(entity)
                .setUserName("Kashiefa")
                .build();
        repo.update(updateEntity);
        Student newEntity  = repo.findByid(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY","Kashiefa",updateEntity.getUserName());

        //DELETE ENTITY
        repo.delete(updateEntity);
        Student deletedEntity = repo.findByid(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

        //Detele All
        repo.deleteAll();


    }
}

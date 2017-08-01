package com.example.administrator.timetableapp.services.student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.test.AndroidTestCase;

import com.example.administrator.timetableapp.MainActivity;
import com.example.administrator.timetableapp.timetable.conf.util.App;
import com.example.administrator.timetableapp.timetable.domain.student.Student;
import com.example.administrator.timetableapp.timetable.repository.student.Impl.StudentRepositoryImpl;
import com.example.administrator.timetableapp.timetable.repository.student.StudentRepository;
import com.example.administrator.timetableapp.timetable.services.student.Impl.AddStudentServiceImpl;

import org.junit.Assert;

import static com.example.administrator.timetableapp.timetable.conf.util.App.getAppContext;

/**
 * Created by Administrator on 2017/07/31.
 */

public class StudentAddServiceTest extends AndroidTestCase {

   // public MainActivity activity;

    //public void onAttach(MainActivity activity){
       // this.activity = activity;
    //}


    @Override
    public void setUp() throws Exception {
        super.setUp();
        //onAttach(activity);
        //intent = new Intent(App.getAppContext(),AddStudentServiceImpl.class);
    }

    public void testAddStudent() throws Exception {
   //  Intent intent = new Intent(this.mContext,AddStudentServiceImpl.class);
Context context = getContext();


        StudentRepository studentRepository = new StudentRepositoryImpl(context);
        AddStudentServiceImpl studentService = AddStudentServiceImpl.getInstance();

        Student student = new Student.StudentBuilder()
                .setUserName("Asongezwa")
                .setPassword("Sit123")
                .build();

        //service is adding
        studentService.addStudent(InstrumentationRegistry.getTargetContext(), student);

        Thread.sleep(1000);
       //studentRepository.save(new Student.StudentBuilder().setUserName("adad").setPassword("adsasd").build());
       Student insertStudent = studentRepository.findByid(1L);
       Assert.assertNotNull(insertStudent);

        /*Student updateStudent = new Student.StudentBuilder()
                .copy(insertStudent)
                .setUserName("David")
                .build();

        //services updating
        studentService.updateStudent(getAppContext(),updateStudent);

        Thread.sleep(1000);
        Student updatedStudent = studentRepository.findByid(1L);
        Assert.assertEquals(updatedStudent.getUserName(),"David");

        //services deleting
        studentService.deleteStudent(getAppContext(), updatedStudent);

       /* //Checks how many records are in the database
        Set<Student> students = studentRepository.findAll();
        int number= students.size();
        Assert.assertEquals(number,5)*/
    }
}

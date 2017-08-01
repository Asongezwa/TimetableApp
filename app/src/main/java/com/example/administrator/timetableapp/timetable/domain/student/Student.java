package com.example.administrator.timetableapp.timetable.domain.student;
import java.io.Serializable;



import java.util.Date;

/**
 * Created by Administrator on 2017/07/25.
 */

public class Student implements Serializable {


    private String userName;
    private String password;
    private long studentID;


    public String getUserName() {
        return userName;
    }
    public long getStudentID() {
        return studentID;
    }

    public String getPassword() {
        return password;
    }
    public Student(StudentBuilder studentBuilder){
        this.userName = studentBuilder.userName;
        this.password = studentBuilder.password;
        this.studentID = studentBuilder.studentID;

    }

    public static class StudentBuilder{
        private String userName;
        private String password;
        private long studentID;



        public StudentBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }


        public StudentBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public StudentBuilder setStudentID(long studentID) {
            this.studentID = studentID;
            return this;
        }

        public StudentBuilder copy(Student student){
            this.userName = student.userName;
            this.password = student.password;
            this.studentID = student.studentID;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }
}

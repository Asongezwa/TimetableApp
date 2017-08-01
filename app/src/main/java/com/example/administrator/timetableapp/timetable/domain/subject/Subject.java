package com.example.administrator.timetableapp.timetable.domain.subject;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/07/25.
 */

public class Subject implements Serializable {
    private String subName;
    private String subCode;
    private long subID;


    public long getSubID() {
        return subID;
    }

    public String getSubName() {
        return subName;
    }

    public String getSubCode() {
        return subCode;
    }
    public Subject(SubjectBuilder subjectBuilder){
        this.subID = subjectBuilder.subID;
        this.subName = subjectBuilder.subName;
        this.subCode = subjectBuilder.subCode;

    }

    public static class SubjectBuilder{

        private String subName;
        private String subCode;
        private long subID;


        public SubjectBuilder setSubName(String subName) {
            this.subName = subName;
            return this;
        }

        public SubjectBuilder setSubID(long subID) {
            this.subID = subID;
            return this;
        }

        public SubjectBuilder setSubCode(String subCode) {
            this.subCode = subCode;
            return this;
        }

        public Subject build(){
            return new Subject(this);

        }
        public SubjectBuilder copy(Subject subject){
            this.subID = subject.subID;
            this.subName = subject.subName;
            this.subCode = subject.subCode;

            return this;
        }

    }
}

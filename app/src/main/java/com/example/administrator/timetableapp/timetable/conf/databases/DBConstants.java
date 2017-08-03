package com.example.administrator.timetableapp.timetable.conf;

/**
 * Created by Administrator on 2017/07/26.
 */

public class DBConstants {
    public static final String DATABASE_NAME = "androidDatabase";
    public static int DATABASE_VERSION =1;

    public static final String TABLE_NAME_STUDENT="student";
    public static final String COLUMN_ID_STUDENT = "id";
    public static final String COLUMN_USERNAME_STUDENT = "userName";
    public static final String COLUMN_PASSWORD_STUDENT = "password";

    public static final String DATABASE_CREATEA= "CREATE TABLE "
            +TABLE_NAME_STUDENT +"("
            +COLUMN_ID_STUDENT + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            +COLUMN_USERNAME_STUDENT + " TEXT NOT NULL, "
            +COLUMN_PASSWORD_STUDENT + " TEXT NOT NULL );";


    //subject
    public static final String TABLE_NAME="subject";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SUBNAME = "subName";
    public static final String COLUMN_SUBCODE = "subCode";

    //Creating the databases
    public static final String DATABASE_CREATEB= "CREATE TABLE "
            +TABLE_NAME +"("
            +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            +COLUMN_SUBNAME + " TEXT NOT NULL, "
            +COLUMN_SUBCODE + " TEXT NOT NULL );";

    //timetable
    public static final String TABLE_NAME_TIMETABLE=" timetable ";
    //long id;

    public static final String COLUMN_ID_TIMETABLE = "id";
    public static final String COLUMN_SCHEDULED_ACTIVITY1= "scheduledActivity1";
    public static final String COLUMN_SCHEDULED_ACTIVITY2 =  "scheduledActivity2";
    public static final String COLUMN_SCHEDULED_ACTIVITY3 ="scheduledActivity3";
    public static final String COLUMN_SCHEDULED_ACTIVITY4="scheduledActivity4";
    public static final String COLUMN_SCHEDULED_ACTIVITY5="scheduledActivity5";

    //Create db
    public static final String DATABASE_CREATEC = "CREATE TABLE "
            +TABLE_NAME_TIMETABLE + "("
            +COLUMN_ID_TIMETABLE+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_SCHEDULED_ACTIVITY1+" TEXT(25), "
            +COLUMN_SCHEDULED_ACTIVITY2+" TEXT(25), "
            +COLUMN_SCHEDULED_ACTIVITY3+" TEXT(25), "
            +COLUMN_SCHEDULED_ACTIVITY4+" TEXT(25), "
            +COLUMN_SCHEDULED_ACTIVITY5+" TEXT(25));";


}

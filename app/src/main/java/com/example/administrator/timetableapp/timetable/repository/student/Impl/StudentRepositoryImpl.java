package com.example.administrator.timetableapp.timetable.repository.student.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.administrator.timetableapp.timetable.conf.DBConstants;
import com.example.administrator.timetableapp.timetable.domain.student.Student;
import com.example.administrator.timetableapp.timetable.repository.student.StudentRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/07/26.
 */

public class StudentRepositoryImpl extends SQLiteOpenHelper implements StudentRepository {
    public static final String TABLE_NAME="student";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "userName";
    public static final String COLUMN_PASSWORD = "password";

    //Creating the databases
    private static final String DATABASE_CREATE= "CREATE TABLE "
                                                +TABLE_NAME +"("
                                                +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                                                +COLUMN_USERNAME + " TEXT NOT NULL, "
                                                +COLUMN_PASSWORD + " TEXT NOT NULL );";
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        Log.w(this.getClass().getName(),
                "Upgrading from version "+ oldVersion+ " to "
                        + newVersion+ ", which will destroy all old datat");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public StudentRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException{
        db = this.getWritableDatabase();
    }
    public void close(){
        this.close();
    }
    @Override
    public Cursor selectAll() {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "select * from student";
        return (db.rawQuery(sql,null));
    }
    @Override
    public Student findByid(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                            TABLE_NAME,
                            new String[]{
                                    COLUMN_ID,
                                    COLUMN_USERNAME,
                                    COLUMN_PASSWORD},
                            COLUMN_ID + " =? ",
                            new String[]{String.valueOf(id)},
                            null,
                            null,
                            null,
                            null);
        if(cursor.moveToNext()){
            final Student student = new Student.StudentBuilder()
                    .setStudentID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID
                    )))
                    .setUserName(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                    .setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .build();
            return student;
        }
        else{
            return null;
        }

    }
    @Override
    public  Student save(Student entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, entity.getUserName());
        values.put(COLUMN_PASSWORD, entity.getPassword());

        long id = db.insertOrThrow(TABLE_NAME, null, values);

        Student insertStudent = new Student.StudentBuilder()
                                    .copy(entity)
                                    .setStudentID(new Long(id))
                                    .build();
                    return  insertStudent;


    }
    @Override
    public  Student update(Student entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getStudentID());
        values.put(COLUMN_USERNAME, entity.getUserName());
        values.put(COLUMN_PASSWORD, entity.getPassword());

        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getStudentID())}
        );

        return entity;


    }

    @Override
    public Student delete(Student entity) throws SQLException {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getStudentID())}
        );
        return entity;


    }
    public  Set<Student> findAll() throws SQLException{
            SQLiteDatabase db = this.getReadableDatabase();
            Set<Student>  students = new HashSet<>();
            open();
            Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null );
            if(cursor.moveToFirst()){

                do{

                    final Student student = new Student.StudentBuilder()
                            .setStudentID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                            .setUserName(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                            .setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                            .build();
                    students.add(student);
                }while (cursor.moveToNext());
                return  students;
            }
            return null;

    }

    @Override
    public  int deleteAll() throws SQLException {
        open();
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
        return rowsDeleted;


    }
}

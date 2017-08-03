package com.example.administrator.timetableapp.timetable.repository.subject.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.administrator.timetableapp.timetable.conf.DBConstants;
import com.example.administrator.timetableapp.timetable.domain.subject.Subject;
import com.example.administrator.timetableapp.timetable.repository.subject.SubjectRepository;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/07/27.
 */

public class SubjectRepositoryImpl extends SQLiteOpenHelper implements SubjectRepository{

        public static final String TABLE_NAME="subject";
        private SQLiteDatabase db;

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_SUBNAME = "subName";
        public static final String COLUMN_SUBCODE = "subCode";

        //Creating the databases
        private static final String DATABASE_CREATE= "CREATE TABLE "
                +TABLE_NAME +"("
                +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                +COLUMN_SUBNAME + " TEXT NOT NULL, "
                +COLUMN_SUBCODE + " TEXT NOT NULL );";
        @Override
        public void onCreate(SQLiteDatabase db){

            db.execSQL(DBConstants.DATABASE_CREATEA);
            db.execSQL(DBConstants.DATABASE_CREATEB);
            db.execSQL(DBConstants.DATABASE_CREATEC);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

            Log.w(this.getClass().getName(),
                    "Upgrading from version "+ oldVersion+ " to "
                            + newVersion+ ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
        public SubjectRepositoryImpl(Context context){
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
            String sql = "select * from subject";
            return (db.rawQuery(sql,null));
        }
        @Override
        public Subject findByid(Long id){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.query(
                    TABLE_NAME,
                    new String[]{
                            COLUMN_ID,
                            COLUMN_SUBNAME,
                            COLUMN_SUBCODE},
                    COLUMN_ID + " =? ",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null,
                    null);
            if(cursor.moveToNext()){
                final Subject subject = new Subject.SubjectBuilder()
                        .setSubID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID
                        )))
                        .setSubName(cursor.getString(cursor.getColumnIndex(COLUMN_SUBNAME)))
                        .setSubCode(cursor.getString(cursor.getColumnIndex(COLUMN_SUBCODE)))
                        .build();
                return subject;
            }
            else{
                return null;
            }

        }
        @Override
        public  Subject save(Subject entity) throws SQLException {
            open();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getSubID());
            values.put(COLUMN_SUBNAME, entity.getSubName());
            values.put(COLUMN_SUBCODE, entity.getSubCode());


            long id = db.insertOrThrow(TABLE_NAME, null, values);

            Subject insertSubject = new Subject.SubjectBuilder()
                    .copy(entity)
                    .setSubID(new Long(id))
                    .build();
            return  insertSubject;


        }
        @Override
        public  Subject update(Subject entity) throws SQLException {
            open();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getSubID());
            values.put(COLUMN_SUBNAME, entity.getSubName());
            values.put(COLUMN_SUBCODE, entity.getSubCode());

            db.update(
                    TABLE_NAME,
                    values,
                    COLUMN_ID + " =? ",
                    new String[]{String.valueOf(entity.getSubID())}
            );

            return entity;


        }

        @Override
        public Subject delete(Subject entity) throws SQLException {
            open();
            db.delete(
                    TABLE_NAME,
                    COLUMN_ID + " =? ",
                    new String[]{String.valueOf(entity.getSubID())}
            );
            return entity;


        }
        public  Set<Subject> findAll() throws SQLException{
            SQLiteDatabase db = this.getReadableDatabase();
            Set<Subject>  subjects = new HashSet<>();
            open();
            Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null );
            if(cursor.moveToFirst()){

                do{

                    final Subject subject = new Subject.SubjectBuilder()
                            .setSubID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                            .setSubName(cursor.getString(cursor.getColumnIndex(COLUMN_SUBNAME)))
                            .setSubCode(cursor.getString(cursor.getColumnIndex(COLUMN_SUBCODE)))
                            .build();
                    subjects.add(subject);
                }while (cursor.moveToNext());
                return  subjects;
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

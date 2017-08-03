package com.example.administrator.timetableapp.timetable.repository.timetable.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.administrator.timetableapp.timetable.conf.DBConstants;
import com.example.administrator.timetableapp.timetable.domain.timetable.Timetable;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/07/28.
 */

public class TimetableRepositoryImpl extends SQLiteOpenHelper implements com.example.administrator.timetableapp.timetable.repository.timetable.Impl.TimetableRepository {

    public static final String TABLE_NAME=" timetable ";
    private SQLiteDatabase db;
    //long id;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SCHEDULED_ACTIVITY1= "scheduledActivity1";
    public static final String COLUMN_SCHEDULED_ACTIVITY2 =  "scheduledActivity2";
    public static final String COLUMN_SCHEDULED_ACTIVITY3 ="scheduledActivity3";
    public static final String COLUMN_SCHEDULED_ACTIVITY4="scheduledActivity4";
    public static final String COLUMN_SCHEDULED_ACTIVITY5="scheduledActivity5";

    //Create db
    private static final String DATABASE_CREATE = "CREATE TABLE "
            +TABLE_NAME + "("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_SCHEDULED_ACTIVITY1+" TEXT(25), "
            +COLUMN_SCHEDULED_ACTIVITY2+" TEXT(25), "
            +COLUMN_SCHEDULED_ACTIVITY3+" TEXT(25), "
            +COLUMN_SCHEDULED_ACTIVITY4+" TEXT(25), "
            +COLUMN_SCHEDULED_ACTIVITY5+" TEXT(25));";


    public TimetableRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open(){
        db = this.getWritableDatabase();
    }

    public void close(){
        this.close();
    }

    @Override
    public Timetable findByid(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_SCHEDULED_ACTIVITY1,
                        COLUMN_SCHEDULED_ACTIVITY2,
                        COLUMN_SCHEDULED_ACTIVITY3,
                        COLUMN_SCHEDULED_ACTIVITY4,
                        COLUMN_SCHEDULED_ACTIVITY5},
                COLUMN_ID+" =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){

            final Timetable timetable = new Timetable.TimetableBuilder()
                    .setTimeID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .setScheduledActivity1(cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULED_ACTIVITY1)))
                    .setScheduledActivity2(cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULED_ACTIVITY2)))
                    .setScheduledActivity3(cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULED_ACTIVITY3)))
                    .setScheduledActivity4(cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULED_ACTIVITY4)))
                    .setScheduledActivity5(cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULED_ACTIVITY5)))
                    .build();
            return timetable;
        }
        else {
            return null;
        }
    }

    @Override
    public Timetable save(Timetable entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getTimeID());
        values.put(COLUMN_SCHEDULED_ACTIVITY1,entity.getScheduledActivity1());
        values.put(COLUMN_SCHEDULED_ACTIVITY2,entity.getScheduledActivity2());
        values.put(COLUMN_SCHEDULED_ACTIVITY3,entity.getScheduledActivity3());
        values.put(COLUMN_SCHEDULED_ACTIVITY4,entity.getScheduledActivity4());
        values.put(COLUMN_SCHEDULED_ACTIVITY5,entity.getScheduledActivity5());
        long id = db.insertOrThrow(TABLE_NAME,null,values);
        Timetable insertedEntity = new Timetable.TimetableBuilder()
                .copy(entity)
                .setTimeID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Timetable update(Timetable entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getTimeID());
        values.put(COLUMN_SCHEDULED_ACTIVITY1,entity.getScheduledActivity1());
        values.put(COLUMN_SCHEDULED_ACTIVITY2,entity.getScheduledActivity2());
        values.put(COLUMN_SCHEDULED_ACTIVITY3,entity.getScheduledActivity3());
        values.put(COLUMN_SCHEDULED_ACTIVITY4,entity.getScheduledActivity4());
        values.put(COLUMN_SCHEDULED_ACTIVITY5,entity.getScheduledActivity5());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID+" =? ",
                new String[]{String.valueOf(entity.getTimeID())}
        );
        return entity;
    }

    @Override
    public Timetable delete(Timetable entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID +" =? ",
                new String[]{String.valueOf(entity.getTimeID())});

        return entity;
    }

    @Override
    public Set<Timetable> findAll() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Timetable>timetables = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{

                final Timetable timetable = new Timetable.TimetableBuilder()
                        .setTimeID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .setScheduledActivity1(cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULED_ACTIVITY1)))
                        .setScheduledActivity2(cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULED_ACTIVITY2)))
                        .setScheduledActivity3(cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULED_ACTIVITY3)))
                        .setScheduledActivity4(cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULED_ACTIVITY4)))
                        .setScheduledActivity5(cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULED_ACTIVITY5)))
                        .build();
                timetables.add(timetable);
            }while(cursor.moveToNext());

        }
        return timetables;
    }

    @Override
    public int deleteAll() {
        open();
        int deletedRows = db.delete(TABLE_NAME,null,null);
        //   close();
        return deletedRows;
    }

    @Override
    public Cursor selectAll() {
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DBConstants.DATABASE_CREATEA);
        db.execSQL(DBConstants.DATABASE_CREATEB);
        db.execSQL(DBConstants.DATABASE_CREATEC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}


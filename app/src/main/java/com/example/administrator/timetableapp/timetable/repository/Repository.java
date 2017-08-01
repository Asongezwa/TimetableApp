package com.example.administrator.timetableapp.timetable.repository;

import android.database.Cursor;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Administrator on 2017/07/26.
 */

public interface Repository<E,ID> {
    E findByid(ID id);
    E save(E entity) throws SQLException;
    E update(E entity) throws SQLException;
    E delete(E entity) throws SQLException;

    Set<E> findAll() throws SQLException;
    int deleteAll() throws SQLException;
    Cursor selectAll() throws SQLException;
}

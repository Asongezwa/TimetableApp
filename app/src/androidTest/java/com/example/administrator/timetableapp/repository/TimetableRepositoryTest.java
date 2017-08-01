package com.example.administrator.timetableapp.repository;

import android.test.AndroidTestCase;

import com.example.administrator.timetableapp.timetable.domain.timetable.Timetable;
import com.example.administrator.timetableapp.timetable.repository.timetable.Impl.TimetableRepository;
import com.example.administrator.timetableapp.timetable.repository.timetable.Impl.TimetableRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;
/**
 * Created by Administrator on 2017/07/27.
 */

public class TimetableRepositoryTest extends AndroidTestCase {

        private static final String TAG= "TIMETABLE TEST";
        private Long id;


        public void testCreateReadUpdateDelete() throws Exception {
            TimetableRepository repo = new TimetableRepositoryImpl(this.getContext());


            //Create
            Timetable timetable = new Timetable.TimetableBuilder()
                    .setScheduledActivity1("MAT")
                    .setScheduledActivity2("PHY")
                    .setScheduledActivity3("ENG")
                    .setScheduledActivity4("CSC")
                    .setScheduledActivity5("STA")
                    .build();
            Timetable insertTimetable = repo.save(timetable);
            id = insertTimetable.getTimeID();
            Assert.assertNotNull(TAG+" CREATE",insertTimetable);


            //READ ALL
            Set<Timetable> timetables = repo.findAll();
            Assert.assertTrue(TAG+" READ ALL",timetables.size()>0);

            //READ entity
            Timetable entity = repo.findByid(id);
            Assert.assertNotNull(TAG + " READ ENTITY", entity);

            //update entity
            Timetable updateItem = new Timetable.TimetableBuilder()
                    .copy(entity)
                    .setScheduledActivity1("GEO")
                    .build();
            repo.update(updateItem);
            Timetable newEntity = repo.findByid(id);
            Assert.assertEquals(TAG + " UPDATE ENTITY", "GEO", newEntity.getScheduledActivity1());

            //DELETE ENTITY
            repo.delete(updateItem);
            Timetable deletedItem = repo.findByid(id);
            Assert.assertNull(TAG+" DELETE",deletedItem);


            //Delete ALL
            repo.deleteAll();



        }

}

package com.example.administrator.timetableapp.repository;

import com.example.administrator.timetableapp.timetable.domain.subject.Subject;
import com.example.administrator.timetableapp.timetable.repository.subject.Impl.SubjectRepositoryImpl;
import com.example.administrator.timetableapp.timetable.repository.subject.SubjectRepository;
import android.test.AndroidTestCase;
import junit.framework.Assert;
import java.util.Set;
/**
 * Created by Administrator on 2017/07/27.
 */









public class SubjectRepositoryTest extends AndroidTestCase{
    public static final String TAG = "SUBJECT TEST";
    private Long id;

    public void testCreateUpdateDelete() throws Exception {

        SubjectRepository repo = new SubjectRepositoryImpl(this.getContext());



            //Create object
            Subject createEntity = new Subject.SubjectBuilder()
                    .setSubID(123)
                    .setSubName("MATH")
                    .setSubCode("MAT211")
                    .build();

            //write object to database
        Subject insertedEntity = repo.save(createEntity);
        id = insertedEntity.getSubID();
            Assert.assertNotNull(TAG + " CREATE", insertedEntity);

            //READ ALL
            Set<Subject> subjects = repo.findAll();
            Assert.assertTrue(TAG + " READ ALL", subjects.size() > 0);

            //READ ENTITY
            Subject entity = repo.findByid(id);
            Assert.assertNotNull(TAG+" READ ENTITY", entity);

            //UPDATE ENTITY
            Subject updateEntity = new Subject.SubjectBuilder()
                    .copy(entity)
                    .setSubName("PHY")
                    .setSubCode("PHY151")
                    .build();
            repo.update(updateEntity);
            Subject newEntity  = repo.findByid(id);
            Assert.assertEquals(TAG+" UPDATE ENTITY","PHY",newEntity.getSubName());


            //DELETE ENTITY
            repo.delete(updateEntity);
            Subject deletedEntity = repo.findByid(id);
            Assert.assertNull(TAG+" DELETE",deletedEntity);

            //Delete All
            repo.deleteAll();
        }


}

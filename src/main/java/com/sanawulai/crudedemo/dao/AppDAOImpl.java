package com.sanawulai.crudedemo.dao;

import com.sanawulai.crudedemo.entity.Instructor;
import com.sanawulai.crudedemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository  // Add this annotation
public class AppDAOImpl implements AppDAO {
    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional //because we are making changes to the database
    public void deleteIstructorById(int theId) {

        //retrieve the instructor
        Instructor theInstructor = entityManager.find(Instructor.class, theId);

        //delete the instructor
        entityManager.remove(theInstructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional //since we are gonna modify the database
    public void deleteInstructorDetailById(int theId) {

        //retrieve instructor detail
        InstructorDetail theInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        //remove the associated object reference
        //break bi-directional link

        theInstructorDetail.getInstructor().setInstructorDetail(null);

        //delete the instructorDetail
        entityManager.remove(theInstructorDetail);

    }
}
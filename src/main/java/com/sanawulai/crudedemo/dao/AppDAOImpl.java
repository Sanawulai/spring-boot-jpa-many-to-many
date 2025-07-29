package com.sanawulai.crudedemo.dao;

import com.sanawulai.crudedemo.entity.Course;
import com.sanawulai.crudedemo.entity.Instructor;
import com.sanawulai.crudedemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        //get the courses
        List<Course> courses = theInstructor.getCourses();

        //break association of all courses for the instructor
        for (Course theCourse : courses){
            theCourse.setInstructor(null);

        }

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

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id=:data", Course.class);

        query.setParameter("data", theId);

        //execute the query
        List<Course> courses = query.getResultList();

        return courses;

    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //create the query
        TypedQuery<Instructor> query = entityManager.createQuery(
                                                        "select i from Instructor i "+"join fetch i.courses " +
                                                                " join fetch i.instructorDetail " +
                                                                " where i.id=:data", Instructor.class);
        query.setParameter("data", theId);

        //execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional //since we are modifying the database
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor);

    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        //retrieve the course
        Course theCourse = entityManager.find(Course.class, theId);

        //delete the course
        entityManager.remove(theCourse);

    }
}
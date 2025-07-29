package com.sanawulai.crudedemo;

import com.sanawulai.crudedemo.dao.AppDAO;
import com.sanawulai.crudedemo.entity.Course;
import com.sanawulai.crudedemo.entity.Instructor;
import com.sanawulai.crudedemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudeDemeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudeDemeApplication.class, args);}


    @Bean
    public CommandLineRunner commandLineRunner (AppDAO appDAO){

        return  runner ->{
              //createInstructor(appDAO);


            //findInstructor(appDAO);

            //deleteInstructor(appDAO);

            //findInstructorDetail(appDAO);

            //deleteInstructorDetail(appDAO);

            //createInstructorWithCourses(appDAO);

            //findInstructorWithCourses(appDAO);

            //findCoursesForInstructor(appDAO);

            //findInstructorWithCoursesJoinFetch(appDAO);

           // updateInstructor(appDAO);

            //updateCourse(appDAO);

            //deleteInstructor(appDAO);

            deleteCourse(appDAO);

        };
    }

    private void deleteCourse(AppDAO appDAO) {

        int theId = 10;

        System.out.println("Deleting the course with id: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("Done!");

    }

    private void updateCourse(AppDAO appDAO) {

        int theId=10;

        //find the course
        System.out.println("Finding the course with id: " + theId);
        Course theCourse = appDAO.findCourseById(theId);

        //update the Course
        System.out.println("Updating the course with id: " + theId);
        theCourse.setTitle("Enjoying Spring with Kotlin");

        //update information on database
        appDAO.update(theCourse);

        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {

        int theId = 1;

        //find the instructor
        System.out.println("Finding instructor id: " + theId);
        Instructor theInstructor = appDAO.findInstructorById(theId);

        //update the instructor
        System.out.println("Updating the instructor id: " + theId);
        theInstructor.setEmail("TESTER");

        appDAO.update(theInstructor);
        System.out.println("Done!");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

        int theId = 1;

        //find the instructor
        System.out.println("Finding instructor with id: " + theId);
        Instructor theInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("theInstructor: " + theInstructor);
        System.out.println("the associated courses: " + theInstructor.getCourses());

        System.out.println("Done!");

    }

    private void findCoursesForInstructor(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Finding instructor with id: " + theId);
        Instructor theInstructor = appDAO.findInstructorById(theId);
        System.out.println("Instructor: " + theInstructor);

        //find courses for instructor
        System.out.println("Finding courses for instructor id: " + theId);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);

        //associate the objects
        theInstructor.setCourses(courses);

        System.out.println("the associated courses:" + theInstructor.getCourses());

        System.out.println("Done!");




    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor with id: " + theId);
        Instructor theInstructor = appDAO.findInstructorById(theId);
        System.out.println("Instructor: " + theInstructor);
        System.out.println("Courses: " + theInstructor.getCourses());
        System.out.println("Done!");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {

        //create the instructor
        Instructor theInstructor = new Instructor("Majeeda","Abdullai","MAbdullai@gmail");

        //create the instructor details
        InstructorDetail theInstructorDetail = new InstructorDetail("http://www.youtube.com/user/MajeedaAbdullai","Java");

        //associate the objects
        theInstructor.setInstructorDetail(theInstructorDetail);

        //create some courses
        Course course1 = new Course("Java Fundamentals");
        Course course2 = new Course("Spring MVC");

        //add courses to instructor
        theInstructor.add(course1);
        theInstructor.add(course2);

        //save instructor
        //NOTE: this will Also save the course
        //because of cascadeType.Persist
        System.out.println("Saving the instructor "+ theInstructor);
        System.out.println("The Courses: "+ theInstructor.getCourses());
        appDAO.save(theInstructor);
        System.out.println("Done!");

    }

    private void deleteInstructorDetail(AppDAO appDAO) {

        int theId = 4;
        System.out.println("Deleting instructor detail with id: " + theId);
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Done!");
    }

    private void findInstructorDetail(AppDAO appDAO) {

        //get the instructor detail object
        int theId = 4;
        Instructor theInstructor = appDAO.findInstructorById(theId);

        //print the instructor detail
        System.out.println(theInstructor);

        //print the associated instructor
        System.out.println("the associated instructor is " + theInstructor.getInstructorDetail());
        System.out.println("Done");
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Deleting instructor with id: " + theId);
        appDAO.deleteIstructorById(theId);
    }

    private void findInstructor(AppDAO appDAO) {

        int theId = 4 ;
        System.out.println("Finding instructor with id: " + theId);
        Instructor theInstructor = appDAO.findInstructorById(theId);
        System.out.println("theInstructor " + theInstructor);
        System.out.println("the associated instructorDetail only: " + theInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {

        //create the instructor
        Instructor theInstructor = new Instructor("Sanau","Wulai","numbonayar@gmail");

        //create the instructor detail
        InstructorDetail theInstructorDetail = new InstructorDetail("calbank.net","Java");

        //associate the objects
        theInstructor.setInstructorDetail(theInstructorDetail);

        //save the instructor
        //NOTE: this will ALSO save the details object
        //because of CascadeType.ALL
        System.out.println("Saving the instructor..."+ theInstructor);
        appDAO.save(theInstructor);

        System.out.println("Done!");

    }

}

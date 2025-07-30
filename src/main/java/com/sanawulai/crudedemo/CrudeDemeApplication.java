package com.sanawulai.crudedemo;

import com.sanawulai.crudedemo.dao.AppDAO;
import com.sanawulai.crudedemo.entity.*;
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

           // createCourseAndStudents(appDAO);

            //findCourseAndStudent(appDAO);

            //findStudentAndCourses(appDAO);

            addMoreCoursesForStudents(appDAO);


        };
    }

    private void addMoreCoursesForStudents(AppDAO appDAO) {

        int theId = 2;
        Student theStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        //create more courses
        Course course1 = new Course("God of War");
        Course course2 = new Course("The Art of War");

        //add courses to student
        theStudent.addCourse(course1);
        theStudent.addCourse(course2);

        System.out.println("updating Students " + theStudent);
        System.out.println("the associated courses: " + theStudent.getCourses());

        appDAO.update(theStudent);
        System.out.println("Done!");
    }

    private void findStudentAndCourses(AppDAO appDAO) {

        int theId = 2;
        Student theStudent = appDAO.findStudentAndCoursesByStudentId(theId);
        System.out.println("Load Courses: " + theStudent);
        System.out.println("the associated courses: " + theStudent.getCourses());
        System.out.println("Done!");
    }

    private void findCourseAndStudent(AppDAO appDAO) {
        int theId = 10;
        Course theCourse = appDAO.findCourseAndStudentsByCourseId(theId);
        System.out.println("Load Students: " + theCourse);
        System.out.println("the associated students: " + theCourse.getStudents());
        System.out.println("Done!");
    }

    private void createCourseAndStudents(AppDAO appDAO) {

        //create a course
        Course theCourse = new Course("Pacman How to score one Million Points");

        //create the students
        Student student1 = new Student("Sanau","Wulai","numbonayar@gmail");

        Student student2 = new Student("Majeeda","Abdullai","MAbdullai@gmail");

        //add students to the course
        theCourse.addStudent(student1);
        theCourse.addStudent(student2);

        //save the course and associated students
        System.out.println("saving the course and students..."+theCourse);
        System.out.println("the students: "+theCourse.getStudents());

        appDAO.save(theCourse);
        System.out.println("Done!");
    }

    private void deletCourseAndReviews(AppDAO appDAO) {

        int theId=10;
        System.out.println("Deleting the course with id: " + theId);
        appDAO.deleteCourseById(theId);
        System.out.println("Done");
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {

        //get the course and reviews
        int theId=10;
        Course theCourse =appDAO.findCourseAndReviewsByCourseId(theId);


        //print the reviews
        System.out.println("Reviews: "+theCourse.getReviews());


        //print the course
        System.out.println("theCourse: "+theCourse);

        System.out.println("Done!");
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        //create a course
        Course theCourse = new Course("pacman-How to score one Million Points");


        //add some reviews
        theCourse.addReview(new Review("Great course ... loved it!"));
        theCourse.addReview(new Review("Cool course ... loved it!"));
        theCourse.addReview(new Review("What a dumb course ... hated it!"));

        //save the course...and leverage the cascade all
        System.out.println("Saving the course..."+ theCourse);
        System.out.println("The reviews: "+ theCourse.getReviews());

        appDAO.save(theCourse);

        System.out.println("Done!");

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

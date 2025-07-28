package com.sanawulai.crudedemo;

import com.sanawulai.crudedemo.dao.AppDAO;
import com.sanawulai.crudedemo.entity.Instructor;
import com.sanawulai.crudedemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
        };
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
        int theId = 4;
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

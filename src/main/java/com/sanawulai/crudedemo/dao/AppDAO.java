package com.sanawulai.crudedemo.dao;

import com.sanawulai.crudedemo.entity.Instructor;
import com.sanawulai.crudedemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteIstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);



}

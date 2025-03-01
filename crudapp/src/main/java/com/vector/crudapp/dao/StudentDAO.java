package com.vector.crudapp.dao;

import com.vector.crudapp.entity.Student;


import java.util.List;


public interface StudentDAO {

     void save(Student thestudent);

     Student findbyID(Integer id);

     List<Student> findAll();

     void update(Student thestudent);

     void delete(Integer id);

     int deleteALL();
}

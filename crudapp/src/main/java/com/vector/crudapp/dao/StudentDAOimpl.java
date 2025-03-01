package com.vector.crudapp.dao;

import com.vector.crudapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StudentDAOimpl implements StudentDAO{

    //define field for entity manager

    private EntityManager entityManager;

    //inject entity manager using constructor

@Autowired
    public StudentDAOimpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement the save method
    @Override
    public void save(Student thestudent) {
entityManager.persist(thestudent);
    }

    @Override
    public Student findbyID(Integer id) {

        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> typedQuery = entityManager.createQuery("FROM Student",Student.class);

        return typedQuery.getResultList();
    }

    @Override
    public void update(Student thestudent) {
        entityManager.merge(thestudent);
    }

    @Override
    public void delete(Integer id) {

    Student thestudent = entityManager.find(Student.class,id);
    entityManager.remove(thestudent);

    }

    @Override
    public int deleteALL() {
    int numD = entityManager.createNativeQuery("TRUNCATE TABLE student").executeUpdate();
        return numD;
    }
}

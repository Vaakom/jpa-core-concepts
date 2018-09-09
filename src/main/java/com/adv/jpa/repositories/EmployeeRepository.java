package com.adv.jpa.repositories;

import com.adv.jpa.domain.Employee;
import com.adv.jpa.domain.FullTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public Employee findById(Long id){
        return em.find(Employee.class, id);
    }

    public void insert(Employee employee){
        em.persist(employee);
    }

    public List<Employee> findAll(){
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    public List<FullTimeEmployee> findAllFullTime(){
        return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }
}

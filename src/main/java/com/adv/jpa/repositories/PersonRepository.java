package com.adv.jpa.repositories;

import com.adv.jpa.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonRepository {

    @Autowired
    EntityManager entityManager;

    public List<Person> findAll() {
        TypedQuery<Person> query = entityManager.createNamedQuery("find_all_person", Person.class);
        return query.getResultList();
    }

    public Person findById(Long id){
        return entityManager.find(Person.class, id);
    }

    public Person insertOrUpdate(Person person) {
        return entityManager.merge(person);
    }

    public void deleteById(Long id) {
        Person person = findById(id);
        entityManager.remove(person);
    }
}

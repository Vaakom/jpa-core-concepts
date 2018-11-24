package com.adv.jpa.dao;

import com.adv.jpa.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJdbcTemplateDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getLong("id"));
            person.setName(resultSet.getString("name"));
            person.setLocation(resultSet.getString("location"));
            person.setBirthDate(resultSet.getTimestamp("birth_date"));

            return person;
        }
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person t order by t.name", new PersonRowMapper());
    }

    public Person findById(Long id) {
        return jdbcTemplate.queryForObject("select * from person t where t.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from person t where t.id = ?", new Object[]{id});
    }

    public int insert(Person person) {
        return jdbcTemplate.update("insert into person values (?, ?, ?, ?)",
                new Object[]{
                        person.getId(),
                        person.getName(),
                        person.getLocation(),
                        new Timestamp(person.getBirthDate().getTime())
                });
    }

    public int update(Person person) {
        return jdbcTemplate.update("update person t set t.name = ?, t.location = ?, t.birth_date = ? where t.id = ?",
                new Object[]{
                        person.getName(),
                        person.getLocation(),
                        new Timestamp(person.getBirthDate().getTime()),
                        person.getId()
                });
    }
}

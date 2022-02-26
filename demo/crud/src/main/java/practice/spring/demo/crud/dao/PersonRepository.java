package practice.spring.demo.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.spring.demo.crud.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    public PersonEntity findByFirstNameAndLastName(String firstName, String lastName);

    public void deleteByFirstNameAndLastName(String firstName, String lastName);

}

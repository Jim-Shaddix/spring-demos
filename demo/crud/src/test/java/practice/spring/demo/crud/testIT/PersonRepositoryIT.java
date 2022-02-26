package practice.spring.demo.crud.testIT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import practice.spring.demo.crud.dao.PersonRepository;
import practice.spring.demo.crud.entity.PersonEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PersonRepositoryIT {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testSave() {

        String firstName = "jim";
        String lastName = "shaddix";

        PersonEntity person = new PersonEntity();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(26);
        person.setEmail("jimmy.shaddix2.0@gmail.com");
        personRepository.save(person);

        PersonEntity entity = personRepository.findByFirstNameAndLastName(firstName, lastName);

    }

}

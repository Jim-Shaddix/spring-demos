package practice.spring.demo.crud.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import practice.spring.demo.crud.dao.PersonRepository;
import practice.spring.demo.crud.dto.PersonDto;
import practice.spring.demo.crud.entity.PersonEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
@AllArgsConstructor
public class PersonService {

    public PersonRepository personRepository;

    public PersonDto getPersonByName(String firstName, String lastName) {

        PersonEntity personEntity =  personRepository.findByFirstNameAndLastName(firstName, lastName);

        PersonDto newPersonDto = new PersonDto();
        newPersonDto.setFirstName(personEntity.getFirstName());
        newPersonDto.setLastName(personEntity.getLastName());
        newPersonDto.setAge(personEntity.getAge());

        return newPersonDto;
    }

    public PersonDto getPersonById(Integer id) {

        PersonDto newPersonDto = new PersonDto();

        log.info("Getting Persion with id: " + id.toString());
        //PersonEntity personEntity = personRepository.getById(id);
        PersonEntity personEntity = personRepository.findById(id).get();
        newPersonDto.setFirstName(personEntity.getFirstName());
        newPersonDto.setLastName(personEntity.getLastName());
        newPersonDto.setAge(personEntity.getAge());
        newPersonDto.setEmail(personEntity.getEmail());

        return newPersonDto;
    }

    public List<PersonDto> getPeople() {

        List<PersonEntity> personEntities = personRepository.findAll();
        List<PersonDto> dtos = personEntities.stream()
                .map((entity) -> {
                    PersonDto dto = new PersonDto();
                    dto.setFirstName(entity.getFirstName());
                    dto.setLastName(entity.getLastName());
                    dto.setAge(entity.getAge());
                    return dto;
                })
                .collect(Collectors.toList());

        return dtos;
    }

    public void addPerson(PersonDto personDto) {

        PersonEntity newPersonEntity = new PersonEntity();
        newPersonEntity.setFirstName(personDto.getFirstName());
        newPersonEntity.setLastName(personDto.getLastName());
        newPersonEntity.setAge(personDto.getAge());
        newPersonEntity.setEmail(personDto.getEmail());

        log.info("adding the person: " + newPersonEntity.toString());

        personRepository.save(newPersonEntity);

    }

    public void updatePerson(PersonDto personDto) {

        // find person by first and last name.
        PersonEntity personEntity = personRepository.findByFirstNameAndLastName(
                personDto.getFirstName(),
                personDto.getLastName()
        );

        // insert updated person.
        PersonEntity newPersonEntity = new PersonEntity();
        newPersonEntity.setFirstName(personDto.getFirstName());
        newPersonEntity.setLastName(personDto.getLastName());
        newPersonEntity.setAge(personDto.getAge());

        log.info(String.format("Updating the person: [%s] to [%s]",
                personEntity.toString(),
                newPersonEntity.toString())
        );

        personRepository.save(newPersonEntity);


    }

    public void deletePerson(PersonDto personDto) {

        log.info("Deleted the person: " + personDto.toString());
        personRepository.deleteByFirstNameAndLastName(
                personDto.getFirstName(),
                personDto.getLastName()
        );

    }

}

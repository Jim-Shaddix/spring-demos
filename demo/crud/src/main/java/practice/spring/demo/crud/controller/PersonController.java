package practice.spring.demo.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import practice.spring.demo.crud.dto.PersonDto;
import practice.spring.demo.crud.service.PersonService;

import java.util.List;

@Controller
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ResponseBody
    @GetMapping("/search")
    public PersonDto getPersonByName(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "first-name", required = false) String firstName,
            @RequestParam(value = "last-name", required = false) String lastName
    ) {

        PersonDto personDto;

        if (id != null) {
            personDto = personService.getPersonById(id);
        } else {
            personDto = personService.getPersonByName(firstName, lastName);
        }

        return personDto;
    }

    @ResponseBody
    @GetMapping
    public List<PersonDto> getPeople() {
        return personService.getPeople();
    }

    @ResponseBody
    @PostMapping
    public String addPerson(@RequestBody PersonDto personDto) {
        System.out.println("about to add a person ...");
        personService.addPerson(personDto);
        return "Completed";
    }

    @PutMapping
    public void updatePerson(@RequestBody PersonDto personDto) {
        personService.updatePerson(personDto);
    }

    @DeleteMapping
    public void deletePerson(@RequestBody PersonDto personDto) {
        personService.deletePerson(personDto);
    }

}

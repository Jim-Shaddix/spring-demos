package practice.spring.demo.crud.testIT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import practice.spring.demo.crud.dao.PersonRepository;
import practice.spring.demo.crud.dto.PersonDto;
import practice.spring.demo.crud.entity.PersonEntity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerIT {

    private static final String personURI = "http://localhost/api/person/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final PersonEntity firstTestDto;
    private static final PersonEntity secondTestDto;

    static {

        firstTestDto = new PersonEntity();
        firstTestDto.setFirstName("jim");
        firstTestDto.setLastName("shaddix");
        firstTestDto.setEmail("jimmy.shaddix2.0@gmail.com");
        firstTestDto.setAge(26);

        secondTestDto = new PersonEntity();
        secondTestDto.setFirstName("bil");
        secondTestDto.setLastName("shaddix");
        secondTestDto.setEmail("billy.shaddix@gmail.com");
        secondTestDto.setAge(23);

    }

    /**
     * tests that the spring context is being built without failure.
     */
    @Test
    void buildContext() {}

    @BeforeEach
    void setPersonInDatabase() {
        personRepository.save(firstTestDto);
        personRepository.save(secondTestDto);
        personRepository.flush();
    }

    @AfterEach
    void removePeople() {
        personRepository.deleteAll();
        personRepository.flush();
    }

    @Test
    void getPersonByName() throws Exception {
        mockMvc.perform(
                    get(personURI + "search")
                            .param("first-name", firstTestDto.getFirstName())
                            .param("last-name", firstTestDto.getLastName())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("age").value(firstTestDto.getAge()))
                .andExpect(content().contentType("application/json"));

    }

    @Test
    public void getPersonById() throws Exception {

        PersonEntity firstPersonEntity =  personRepository.findByFirstNameAndLastName(
                firstTestDto.getFirstName(),
                firstTestDto.getLastName()
        );

        mockMvc.perform(
                        get(personURI + "search")
                                .param("id", String.valueOf(firstPersonEntity.getId()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("age").value(firstTestDto.getAge()))
                .andExpect(jsonPath("first-name").value(firstTestDto.getFirstName()))
                .andExpect(jsonPath("last-name").value(firstTestDto.getLastName()))
                .andExpect(jsonPath("email").value(firstTestDto.getEmail()))
                .andExpect(content().contentType("application/json"));

    }

    @Test
    void getPeople() throws Exception {
        mockMvc.perform(get(personURI))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void addPerson() throws Exception {

        PersonDto personDto = new PersonDto();
        personDto.setFirstName("jim-DTO");
        personDto.setLastName("Shaddix-DTO");
        personDto.setAge(50);
        personDto.setEmail("jim-DTO-email");

        mockMvc.perform(
                post(personURI)
                        .content(objectMapper.writeValueAsString(personDto))
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }

}
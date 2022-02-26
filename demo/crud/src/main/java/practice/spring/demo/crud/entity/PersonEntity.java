package practice.spring.demo.crud.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//, indexes = {
//@Index(name = "mutltiname", columnList = "firstName, lastName", unique = true)
//}
@Data
@NoArgsConstructor
@Entity
@Table(name = "Person", schema = "db")
public class PersonEntity {

    @Basic
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Basic
    @Column(name = "age", nullable = true)
    private Integer age;

    @Column(name = "email")
    private String email;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

}

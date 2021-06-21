package hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "CreateDate")
    private LocalDateTime createDate;

    @Column(name = "Active")
    private boolean active;

    @OneToMany(mappedBy = "user")
    private List<Individual> individuals;

    @OneToMany(mappedBy = "user")
    private List<Corporate> corporates;

    @OneToMany(mappedBy = "user")
    private List<PhoneNumber> phoneNumbers;
}
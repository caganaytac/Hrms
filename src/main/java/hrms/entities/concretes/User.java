package hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.Length;

import hrms.core.utilities.validation.email.Email;
import hrms.core.utilities.validation.password.Password;
import hrms.entities.constants.Messages;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "individuals", "corporates", "githubAccounts",
        "linkedinAccounts", "phoneNumbers", "userBiographies", "userPhotos" })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = Messages.emailNotEmpty)
    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty(message = Messages.passwordNotEmpty)
    @Length(min = 8, message = Messages.passwordLength)
    @Password
    @Column(name = "password")
    private String password;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "confirmed")
    private boolean confirmed;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "user")
    private List<Individual> individuals;

    @OneToMany(mappedBy = "user")
    private List<Corporate> corporates;

    @OneToMany(mappedBy = "user")
    private List<GithubAccount> githubAccounts;

    @OneToMany(mappedBy = "user")
    private List<LinkedinAccount> linkedinAccounts;

    @OneToMany(mappedBy = "user")
    private List<PhoneNumber> phoneNumbers;

    @OneToMany(mappedBy = "user")
    private List<UserBiography> userBiographies;

    @OneToMany(mappedBy = "user")
    private List<UserPhoto> userPhotos;
}
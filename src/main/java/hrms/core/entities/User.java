package hrms.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hrms.entities.concretes.Corporate;
import hrms.entities.concretes.GithubAccount;
import hrms.entities.concretes.Individual;
import hrms.entities.concretes.LinkedinAccount;
import hrms.entities.concretes.PhoneNumber;
import hrms.entities.concretes.UserBiography;
import hrms.entities.concretes.UserPhoto;

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
    private int id;

    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @Column(name = "create_date")
    private LocalDateTime createDate;

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
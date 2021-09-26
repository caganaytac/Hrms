package hrms.entities.concretes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "individuals")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "individualJobPositions", "individualEducations",
        "individualJobExperiences", "individualLanguages", "individualTechnologies", "employees" })
public class Individual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "createDate" })
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "national_identity")
    private String nationalIdentity;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "individual")
    private List<Employee> employees;

    @OneToMany(mappedBy = "individual")
    private List<IndividualJobPosition> individualJobPositions;

    @OneToMany(mappedBy = "individual")
    private List<IndividualEducation> individualEducations;

    @OneToMany(mappedBy = "individual")
    private List<IndividualJobExperience> individualJobExperiences;

    @OneToMany(mappedBy = "individual")
    private List<IndividualLanguage> individualLanguages;
    
    @OneToMany(mappedBy = "individual")
    private List<IndividualTechnology> individualTechnologies;

    public Individual(User user, String firstName, String lastName, String nationalIdentity, LocalDate dateOfBirth) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalIdentity = nationalIdentity;
        this.dateOfBirth = dateOfBirth;
    }
}
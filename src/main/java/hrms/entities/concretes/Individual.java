package hrms.entities.concretes;

import hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "individuals")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "individualJobPositions", "individualEducations",
        "individualJobExperiences", "individualLanguages", "individualTechnologies" })
public class Individual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
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
    private List<IndividualJobPosition> individualJobPositions;

    @OneToMany(mappedBy = "individual")
    private List<IndividualEducation> individualEducations;

    @OneToMany(mappedBy = "individual")
    private List<IndividualJobExperience> individualJobExperiences;

    @OneToMany(mappedBy = "individual")
    private List<IndividualLanguage> individualLanguages;
    
    @OneToMany(mappedBy = "individual")
    private List<IndividualTechnology> individualTechnologies;
}
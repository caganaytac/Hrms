package hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "job_positions")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "individualJobPositions", "jobAdverts",
        "individualJobExperiences" })
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "jobPosition")
    private List<IndividualJobPosition> individualJobPositions;

    @OneToMany(mappedBy = "jobPosition")
    private List<JobAdvert> jobAdverts;

    @OneToMany(mappedBy = "jobPosition")
    private List<IndividualJobExperience> individualJobExperiences;
}
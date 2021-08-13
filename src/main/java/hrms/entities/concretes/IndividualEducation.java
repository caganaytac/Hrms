package hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "individual_educations")
public class IndividualEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "individual_id")
    private Individual individual;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "school_faculty_id")
    private SchoolFaculty schoolFaculty;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "graduate_year")
    private Short graduateYear;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "active")
    private boolean active;
}
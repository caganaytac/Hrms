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
@Table(name = "individual_job_experiences")
public class IndividualJobExperience{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "individual_id")
    private Individual individual;

    @ManyToOne
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;
    
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "start_date")
    private short startDate;

    @Column(name = "finish_date")
    private short finishDate;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "active")
    private boolean active;
}
package hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "job_adverts")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "confirmedJobAdverts" })
public class JobAdvert{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "corporate_id")
    private Corporate corporate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
        
    @NotNull
    @ManyToOne
    @JoinColumn(name = "work_area_id")
    private WorkArea workArea;
        
    @NotNull
    @ManyToOne
    @JoinColumn(name = "work_time_id")
    private WorkTime workTime;

    @Column(name = "min_salary")
    private Double minSalary;

    @Column(name = "max_salary")
    private Double maxSalary;

    @Min(value = 1)
    @NotNull
    @Column(name = "open_position")
    private Short openPosition;

    @Column(name = "deadline")
    private LocalDate deadline;

    @NotEmpty
    @Column(name = "description")
    private String description;
  
    @Column(name = "status")
    private boolean status;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "jobAdvert")
    private List<ConfirmedJobAdvert> confirmedJobAdverts;
}
package hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "job_adverts")
public class JobAdvert{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "corporate_id")
    private Corporate corporate;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;
    
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
      
    @Column(name = "min_salary")
    private Double minSalary;
             
    @Column(name = "max_salary")
    private Double maxSalary;
    
    @Length(min = 1)
    @NotEmpty
    @Column(name = "open_position")
    private short openPosition;
    
    @NotEmpty
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
}
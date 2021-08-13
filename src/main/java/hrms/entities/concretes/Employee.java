package hrms.entities.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employees")
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "verifiedCorporates", "verifiedJobAdverts" })
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "individual_id")
    @JsonIgnoreProperties({ "createDate" })
    private Individual individual;

    @NotBlank
    @Length(min = 0, max = 1000)
    @JoinColumn(name = "credit_score")
    private Short creditScore;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Nullable
    @Column(name = "finish_date")
    private LocalDate finishDate;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "employee")
    private List<ConfirmedCorporate> verifiedCorporates;

    @OneToMany(mappedBy = "employee")
    private List<ConfirmedJobAdvert> verifiedJobAdverts;

    public Employee(Individual individual, Short creditScore, LocalDate startDate, LocalDate finishDate) {
        this.individual = individual;
        this.creditScore = creditScore;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
}
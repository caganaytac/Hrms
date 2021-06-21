package hrms.entities.concretes;

import hrms.core.entities.User;
import hrms.entities.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "corporates")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "jobAdverts" })
public class Corporate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = Messages.userNotEmpty)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty(message = Messages.companyNameNotEmpty)
    @Column(name = "company_name")
    private String companyName;

    @NotEmpty(message = Messages.websiteNotEmpty)
    @Column(name = "website")
    private String website;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "city")
    private List<JobAdvert> jobAdverts;
}
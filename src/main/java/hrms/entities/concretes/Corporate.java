package hrms.entities.concretes;

import hrms.core.utilities.validation.website.Website;
import hrms.entities.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({ "password", "createDate", "active" })
    private User user;

    @NotEmpty(message = Messages.companyNameNotEmpty)
    @Column(name = "company_name")
    private String companyName;

    @NotBlank(message = Messages.websiteNotEmpty)
    @Website
    @Column(name = "website")
    private String website;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "confirmed")
    private boolean confirmed;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "corporate")
    private List<JobAdvert> jobAdverts;

    public Corporate(User user, String companyName, String website) {
        this.user = user;
        this.companyName = companyName;
        this.website = website;
    }
}
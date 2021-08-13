package hrms.entities.concretes;

import hrms.core.utilities.validation.website.Website;
import hrms.entities.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "corporates_to_confirm")
public class CorporateToConfirm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "corporate_id")
    private Corporate corporate;

    @NotEmpty(message = Messages.companyNameNotEmpty)
    @Column(name = "company_name")
    private String companyName;

    @NotBlank(message = Messages.websiteNotEmpty)
    @Website
    @Column(name = "website")
    private String website;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "active")
    private boolean active;
}
package hrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Corporates")
public class Corporate {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "UserId")
    private int userId;

    @Column(name = "Website")
    private String website;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "CreateDate")
    private Date createDate;

    @Column(name = "Active")
    private boolean active;

    public Corporate(int id, int userId, String website, String companyName, Date createDate, boolean active) {
        this.id = id;
        this.userId = userId;
        this.website = website;
        this.companyName = companyName;
        this.createDate = createDate;
        this.active = active;
    }
    public Corporate(){}
}

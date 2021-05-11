package hrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Individuals")
public class Individual {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "NationalIdentity")
    private String nationalIdentity;

    @Column(name = "DateOfBirth")
    private Date dateOfBirth;

    @Column(name = "CreateDate")
    private Date createDate;

    @Column(name = "Active")
    private boolean active;

    public Individual(int id, String firstName, String lastName,
                      String nationalIdentity, Date dateOfBirth,
                      Date createDate, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalIdentity = nationalIdentity;
        this.dateOfBirth = dateOfBirth;
        this.createDate = createDate;
        this.active = active;
    }

    public Individual(){}
}

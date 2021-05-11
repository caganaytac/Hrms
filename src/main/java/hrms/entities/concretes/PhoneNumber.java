package hrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PhoneNumbers")
public class PhoneNumber {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "UserId")
    private int userId;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "CreateDate")
    private Date createDate;

    @Column(name = "Active")
    private boolean active;

    public PhoneNumber(int id, int userId, String phoneNumber, Date createDate, boolean active) {
        this.id = id;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.active = active;
    }

    public PhoneNumber(){}
}
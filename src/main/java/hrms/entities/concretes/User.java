package hrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "CreateDate")
    private Date createDate;

    @Column(name = "Active")
    private boolean active;

    public User(int id, String email, String password, Date createDate, boolean active) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.active = active;
    }

    public User(){}
}
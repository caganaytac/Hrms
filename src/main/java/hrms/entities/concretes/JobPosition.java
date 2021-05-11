package hrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "JobPositions")
public class JobPosition {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "CreateDate")
    private Date createDate;

    @Column(name = "Active")
    private boolean active;

    public JobPosition(int id, String name, Date createDate, boolean active) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.active = active;
    }
    public JobPosition(){}
}
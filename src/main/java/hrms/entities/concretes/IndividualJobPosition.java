package hrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "IndividualJobPositions")
public class IndividualJobPosition {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "IndividualId")
    private int individualId;

    @Column(name = "CreateDate")
    private Date createDate;

    @Column(name = "Active")
    private boolean active;

    public IndividualJobPosition(int id, int individualId, Date createDate, boolean active) {
        super();
        this.id = id;
        this.individualId = individualId;
        this.createDate = createDate;
        this.active = active;
    }

    public IndividualJobPosition() {}
}
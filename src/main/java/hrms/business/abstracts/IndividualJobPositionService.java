package hrms.business.abstracts;

import hrms.entities.concretes.IndividualJobPosition;

import java.util.List;

public interface IndividualJobPositionService {
    List<IndividualJobPosition> getAll();
}

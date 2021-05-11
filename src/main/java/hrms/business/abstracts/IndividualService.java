package hrms.business.abstracts;

import hrms.entities.concretes.Individual;

import java.util.List;

public interface IndividualService {
    List<Individual> getAll();
}

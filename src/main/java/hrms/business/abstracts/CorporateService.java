package hrms.business.abstracts;

import hrms.entities.concretes.Corporate;

import java.util.List;

public interface CorporateService {
    List<Corporate> getAll();
}

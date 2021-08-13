package hrms.core.business.adapters.personService;

import org.springframework.stereotype.Service;

// import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class PersonManager implements PersonService {
    @Override
    public boolean verify(Person person) {
        // try {
        //     KPSPublicSoapProxy kpsPublic = new KPSPublicSoapProxy();
        //     return kpsPublic.TCKimlikNoDogrula(person.getCitizienId(), person.getFirstName().toUpperCase(),
        //             person.getLastName().toUpperCase(), person.getBirthYear());
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     return false;
        // }
        return true;
    }
}
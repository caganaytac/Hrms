package hrms.core.business.adapters.personService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private long citizienId;
    private String firstName;
    private String lastName;
    private int birthYear;
}
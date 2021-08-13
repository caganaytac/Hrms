package hrms.entities.validation.hrmsEmail;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator; 
import javax.validation.ConstraintValidatorContext;

public class HrmsEmailValidator implements ConstraintValidator<HrmsEmail, String> {
    @Override
    public boolean isValid(String hrmsEmail, ConstraintValidatorContext arg) {
        if (hrmsEmail.isBlank()) return true;
        Pattern pattern = Pattern.compile("^[A-Z0-9\\.-]+@hrms.com$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(hrmsEmail.trim()).find();
    }
}
package hrms.core.utilities.validation.email;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String>{
    @Override
    public boolean isValid(String email, ConstraintValidatorContext arg) {
        if (email.isBlank()) return true;
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,8}$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email.trim()).find();
    }
}
package hrms.core.utilities.validation.password;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext arg) {
        if (password.isBlank() || password.length() < 8) return true;
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!#@&()\\–_\\[{}\\]:;',?/*~$^+=<>]).+$");
        return pattern.matcher(password).find();
    }
}
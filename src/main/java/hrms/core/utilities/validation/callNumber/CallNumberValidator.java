package hrms.core.utilities.validation.callNumber;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CallNumberValidator implements ConstraintValidator<CallNumber, String> {
    @Override
    public boolean isValid(String callNumber, ConstraintValidatorContext arg) {
        if (callNumber.isBlank()) return true;
        Pattern pattern = Pattern.compile("^(\\+90[ -]?|0[ -]?)?(5[0-9]{2}|\\(5[0-9]{2}\\))[ -]?(\\d{3})[ -]?(\\d{4})$");
        return pattern.matcher(callNumber.trim()).find();
    }
}
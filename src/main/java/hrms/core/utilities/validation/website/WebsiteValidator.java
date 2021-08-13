package hrms.core.utilities.validation.website;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WebsiteValidator implements ConstraintValidator<Website, String> {
    @Override
    public boolean isValid(String website, ConstraintValidatorContext arg) {
        if (website.isBlank()) return true;
        Pattern pattern = Pattern.compile("^(http:\\/\\/|https:\\/\\/)?(www.)?([a-z0-9]{1,256})(\\.com|\\.co|\\.net|\\.tk|\\.org|\\.gov|\\.uk|\\.us|\\.tv||\\.istanbul||\\.com.tr)(\\.uk|\\.us|\\.ru)?\\/?$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(website.trim()).find();
    }
}
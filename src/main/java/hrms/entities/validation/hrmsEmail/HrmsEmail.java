package hrms.entities.validation.hrmsEmail;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HrmsEmailValidator.class)
public @interface HrmsEmail {
    public abstract java.lang.String message() default "An employee's email must end with hrms.com.";

    public abstract java.lang.Class<?>[] groups() default {};

    public abstract java.lang.Class<? extends javax.validation.Payload>[] payload() default {};
}
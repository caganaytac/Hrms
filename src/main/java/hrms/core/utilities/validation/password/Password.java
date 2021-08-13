package hrms.core.utilities.validation.password;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    public abstract java.lang.String message() default "Your password must contain at least one uppercase"
     + " letter, one lowercase letter and one special character.";
  
    public abstract  java.lang.Class<?>[] groups() default {};
    
    public abstract  java.lang.Class<? extends javax.validation.Payload>[] payload() default {};
}
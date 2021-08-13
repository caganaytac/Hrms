package hrms.core.utilities.validation.email;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {
  public abstract java.lang.String message() default "Please enter a valid email.";
  
  public abstract  java.lang.Class<?>[] groups() default {};
  
  public abstract  java.lang.Class<? extends javax.validation.Payload>[] payload() default {};
}
package hrms.core.utilities.validation.website;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WebsiteValidator.class)
public @interface Website {
    public abstract java.lang.String message() default "Please enter a valid website url.";
 
   public abstract  java.lang.Class<?>[] groups() default {};
   
   public abstract  java.lang.Class<? extends javax.validation.Payload>[] payload() default {};  
}
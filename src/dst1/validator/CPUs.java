package dst1.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=CPUsValidator.class)
@Target({ElementType.METHOD,
	ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CPUs {
	String message() default "Number of CPUs must be in bounds. No clue how to tell you the bounds.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    int min();
    int max();
    
}

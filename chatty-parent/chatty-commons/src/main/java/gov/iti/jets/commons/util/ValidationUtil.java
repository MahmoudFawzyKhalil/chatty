package gov.iti.jets.commons.util;

import jakarta.validation.*;

import java.util.Set;

public class ValidationUtil {

//    private static final ValidationUtil INSTANCE = new ValidationUtil();
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ValidationUtil() {

    }

    /*public static ValidationUtil getInstance() {
        return INSTANCE;
    }*/

    public static <T> void validate( T dto ) {
        Set<ConstraintViolation<T>> violations = validator.validate( dto );
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException( violations );
        }
    }
}

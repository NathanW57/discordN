package com.example.discordexa.discord.constraint;//package com.example.discordexa.discord.constraint;//package constraint;
//
//
//
//import bean.ErrorDetail;
//import com.example.discordexa.discord.bean.ErrorDetail;
//import jakarta.ws.rs.ext.Provider;
//import org.hibernate.resource.transaction.backend.jta.internal.synchronization.ExceptionMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import javax.validation.Path;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Set;
//
//
//@Provider
//public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
//
//    @Override
//    public ResponseEntity<ConstraintViolation> toResponse(ConstraintViolationException exception) {
//        List<ErrorDetail> errors = new ArrayList<>();
//        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
//        String className = null;
//        for (ConstraintViolation<?> violation : violations) {
//           String field = null;
//                   for(Path.Node node : violation.getPropertyPath())
//                   {
//                       field = node.toString();
//                   }
//                       errors.add(new ErrorDetail("BadArgument", field, violation.getMessage()));
//
//            if (className == null) {
//                className = violation.getRootBeanClass().getSimpleName();
//            }
//        }
//
//        String errorMessage = errors.size() > 1 ? "Multiple errors in " + className + " data" : "Error in " + className + " data";
//        LinkedHashMap<String, Object> errorDetails = new LinkedHashMap<>();
//        errorDetails.put("code", "BadArgument");
//        errorDetails.put("message", errorMessage);
//        errorDetails.put("target", className);
//        errorDetails.put("details", errors);
//
//        LinkedHashMap<String, Object> errorResponse = new LinkedHashMap<>();
//        errorResponse.put("error", errorDetails);
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//}

import com.example.discordexa.discord.bean.ErrorDetail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;


@ControllerAdvice
public class ConstraintViolationExceptionMapper {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {
        List<ErrorDetail> errors = new ArrayList<>();
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        String className = null;
        for (ConstraintViolation<?> violation : violations) {
            String field = null;
            for(Path.Node node : violation.getPropertyPath())
            {
                field = node.toString();
            }
            errors.add(new ErrorDetail("BadArgument", field, violation.getMessage()));

            if (className == null) {
                className = violation.getRootBeanClass().getSimpleName();
            }
        }

        String errorMessage = errors.size() > 1 ? "Multiple errors in " + className + " data" : "Error in " + className + " data";
        LinkedHashMap<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put("code", "BadArgument");
        errorDetails.put("message", errorMessage);
        errorDetails.put("target", className);
        errorDetails.put("details", errors);

        LinkedHashMap<String, Object> errorResponse = new LinkedHashMap<>();
        errorResponse.put("error", errorDetails);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

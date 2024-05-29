//package shop.ojtprojectdemoshop.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import shop.ojtprojectdemoshop.model.dto.response.ResponseError;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ResponseError> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
//        ResponseError response = new ResponseError();
//        response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
//        response.setStatus(HttpStatus.BAD_REQUEST.value());
//        response.setError("Bad Request");
//        response.setMessage("Validation failed for object='" + ex.getBindingResult().getObjectName() + "'. Error count: " + ex.getBindingResult().getErrorCount());
//        response.setPath(request.getDescription(false).replace("uri=", ""));
//
//        List<ResponseError.ValidationError> validationErrors = new ArrayList<>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            ResponseError.ValidationError validationError = new ResponseError.ValidationError();
//            validationError.setField(error.getField());
//            validationError.setRejectedValue(error.getRejectedValue());
//            validationError.setDefaultMessage(error.getDefaultMessage());
//            validationError.setCode(error.getCode());
//            validationErrors.add(validationError);
//        }
//        response.setErrors(validationErrors);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ResponseError> handleRuntimeException(RuntimeException ex, WebRequest request) {
//        ResponseError response = new ResponseError(
//                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "Internal Server Error",
//                ex.getMessage(),
//                request.getDescription(false).replace("uri=", "")
//        );
//
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//}

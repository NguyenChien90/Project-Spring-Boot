//package shop.ojtprojectdemoshop.model.dto.response;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Getter @Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class ResponseError {
//    private String timestamp;
//    private int status;
//    private String error;
//    private String message;
//    private List<ValidationError> errors;
//    private String path;
//
//    public ResponseError(String timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public ResponseError(String timestamp, int status, String error, String message, String path) {
//        this.timestamp = timestamp;
//        this.status = status;
//        this.error = error;
//        this.message = message;
//        this.path = path;
//    }
//
//
//    public static class ValidationError {
//        private String field;
//        private Object rejectedValue;
//        private String defaultMessage;
//        private String code;
//
//
//        public String getField() {
//            return field;
//        }
//
//        public void setField(String field) {
//            this.field = field;
//        }
//
//        public Object getRejectedValue() {
//            return rejectedValue;
//        }
//
//        public void setRejectedValue(Object rejectedValue) {
//            this.rejectedValue = rejectedValue;
//        }
//
//        public String getDefaultMessage() {
//            return defaultMessage;
//        }
//
//        public void setDefaultMessage(String defaultMessage) {
//            this.defaultMessage = defaultMessage;
//        }
//
//        public String getCode() {
//            return code;
//        }
//
//        public void setCode(String code) {
//            this.code = code;
//        }
//    }
//}

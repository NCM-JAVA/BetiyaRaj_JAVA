//package Validation;
//
//import java.util.regex.Pattern;
//
//public class FileRequeistionValidator {
//    
//    private static final Pattern DISTRICT_PATTERN = 
//        Pattern.compile("^[\\p{L} .'-]+$"); // Allows letters, spaces, dots, apostrophes and hyphens
//    
//    public static ValidationResult validateDistrict(String district) {
//        ValidationResult result = new ValidationResult();
//        
//        if (district == null || district.isEmpty()) {
//            result.addError("District parameter is required");
//            return result;
//        }
//        
//        if (district.length() > 100) {
//            result.addError("District name too long (max 100 characters)");
//        }
//        
//        if (!DISTRICT_PATTERN.matcher(district).matches()) {
//            result.addError("District name contains invalid characters");
//        }
//        
//        return result;
//    }
//    
//    // Reuse the existing ValidationResult pattern
//    public static class ValidationResult {
//        private boolean passed = true;
//        private StringBuilder errors = new StringBuilder();
//
//        public boolean passed() {
//            return passed;
//        }
//
//        public String getErrors() {
//            return errors.toString();
//        }
//
//        public void addError(String error) {
//            if (passed) passed = false;
//            if (errors.length() > 0) errors.append("; ");
//            errors.append(error);
//        }
//    }
//}
package Validation;

import com.bor.rcms.dto.CaseNotes;
import com.bor.rcms.dto.UserRegistrationRequest;

import Validation.CaseNotesValidator.ValidationResult;

import java.util.regex.Pattern;

public class UserRegistrationValidator {

	private static final Pattern NAME_PATTERN = Pattern.compile("^[\\p{L} '-]+$");

	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9]+([+_.-][A-Za-z0-9]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$");

	private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$"); 

	private static final Pattern AADHAR_PATTERN = Pattern.compile("^[0-9]{12}$");

	private static final Pattern PINCODE_PATTERN = Pattern.compile("^[1-9][0-9]{5}$");

	private static final Pattern BANK_BRANCH_PATTERN = Pattern.compile("^[A-Za-z0-9 _-]{3,50}$");

	private static final Pattern PASSWORD_PATTERN = 
	    Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!\\-_.,])(?=\\S+$).{8,64}$");
	private static final Pattern GENERAL_TEXT_PATTERN = 
	        Pattern.compile("^[\\p{L}0-9 .,'\"()\\-\\/]+$");
	    private static final Pattern ACTION_PATTERN = 
	        Pattern.compile("^[A-Za-z_]{3,20}$");
	    private static final Pattern FORM_ID_PATTERN = 
	        Pattern.compile("^[A-Za-z0-9-]{3,50}$");
//	caseProcessdetails

    public static ValidationResult checkUser(UserRegistrationRequest request) {
        ValidationResult result = new ValidationResult();
        
//        public static ValidationResult checkUser(UserRegistrationRequest requiestion) {
//            ValidationResult result = new ValidationResult();
//            
        // Validate full name
        if (request.getFullName() == null || request.getFullName().isEmpty()) {
            result.addError("Full name is required");
        } else if (!NAME_PATTERN.matcher(request.getFullName()).matches()) {
            result.addError("Full name should contain only letters and spaces");
            return result;
        }
        
        // Validate email
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            result.addError("Email is required");
        } else if (!EMAIL_PATTERN.matcher(request.getEmail()).matches()) {
            result.addError("Invalid email format");
            return result;
        }
        
        // Clean and validate primary phone number
        if (request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty()) {
            result.addError("Primary phone number is required");
        } else {
            String cleanedPhone = request.getPhoneNumber().replaceAll("[^0-9]", "");
            if (!PHONE_PATTERN.matcher(cleanedPhone).matches()) {
                result.addError("Primary phone number must be exactly 10 digits");
            }
        }
        
        // Clean and validate alternate phone number (optional)
        if (request.getAlternatenumber() != null && !request.getAlternatenumber().isEmpty()) {
            String cleanedAltPhone = request.getAlternatenumber().replaceAll("[^0-9]", "");
            if (!PHONE_PATTERN.matcher(cleanedAltPhone).matches()) {
                result.addError("Alternate phone number must be exactly 10 digits");
            }
        }
        
        // Validate password
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            result.addError("Password is required");
        } else if (!PASSWORD_PATTERN.matcher(request.getPassword()).matches()) {
            result.addError("Password must be at least 8 characters with uppercase,"
            		+ " lowercase, number and special character");
        }
        
        // Validate Aadhar (optional)
        if (request.getAadhar() != null && !request.getAadhar().isEmpty() && 
            !AADHAR_PATTERN.matcher(request.getAadhar()).matches()) {
            result.addError("Aadhar must be 12 digits");
            return result;
        }
        
        // Validate pincode
        if (request.getPincode() != null && !request.getPincode().isEmpty() && 
            !PINCODE_PATTERN.matcher(request.getPincode()).matches()) {
            result.addError("Pincode must be 6 digits");
            return result;
        }
        
        // Validate bank name (for PDR)
        if (request.getBankName() != null && !request.getBankName().isEmpty() && 
            !BANK_BRANCH_PATTERN.matcher(request.getBankName()).matches()) {
            result.addError("Bank name contains invalid characters");
            return result;
        }
        
        // Validate branch code (for PDR)
        if (request.getBranchCode() != null && !request.getBranchCode().isEmpty() && 
            !BANK_BRANCH_PATTERN.matcher(request.getBranchCode()).matches()) {
            result.addError("Branch code contains invalid characters");
            return result;
        }
        
        // Validate city
        if (request.getCity() != null && !request.getCity().isEmpty() && 
            !NAME_PATTERN.matcher(request.getCity()).matches()) {
            result.addError("City name should contain only letters and spaces");
            return result;
        }
        
        // Validate state
        if (request.getState() != null && !request.getState().isEmpty() && 
            !NAME_PATTERN.matcher(request.getState()).matches()) {
            result.addError("State name should contain only letters and spaces");
            return result;
        }
        
        // Validate district
        if (request.getDistrict() != null && !request.getDistrict().isEmpty() && 
            !NAME_PATTERN.matcher(request.getDistrict()).matches()) {
            result.addError("District name should contain only letters and spaces");
            return result;
        }
        
        return result;
    }
    public static ValidationResult checkUser(CaseNotes casenotes) {
        ValidationResult result = new ValidationResult();
   
        if (casenotes == null) {
            result.addError("Case notes object cannot be null");
            return result;
        }
        
        // Validate action (required field)
        if (casenotes.getAction() == null || casenotes.getAction().isEmpty()) {
            result.addError("Action is required");
        } else if (!ACTION_PATTERN.matcher(casenotes.getAction()).matches()) {
            result.addError("Action contains invalid characters (only letters and underscore allowed)");
        }
        
        // Validate selectForm if present
        if (casenotes.getSelectForm() != null && !casenotes.getSelectForm().isEmpty()) {
            if (!FORM_ID_PATTERN.matcher(casenotes.getSelectForm()).matches()) {
                result.addError("Select form ID format is invalid");
            }
        }
        
        // Validate note
        if (casenotes.getCaseNotes() != null && !casenotes.getCaseNotes().isEmpty()) {
            if (casenotes.getCaseNotes().length() > 1000) {
                result.addError("Notes cannot exceed 1000 characters");
            } else if (!GENERAL_TEXT_PATTERN.matcher(casenotes.getCaseNotes()).matches()) {
                result.addError("Notes contain invalid characters");
            }
        }
        
        
        return result;
    }
    
    
    

    public static class ValidationResult {
        private boolean passed = true;
        private StringBuilder errors = new StringBuilder();

        public boolean passed() {
            return passed;
        }

        public String getErrors() {
            return errors.toString();
        }

        public void addError(String error) {
            if (passed) passed = false;
            if (errors.length() > 0) errors.append("; ");
            errors.append(error);
        }
    }
}
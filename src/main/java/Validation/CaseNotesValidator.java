package Validation;

import com.bor.rcms.dto.CaseNotes; 
import java.util.regex.Pattern;

public class CaseNotesValidator {
    
    // Validation patterns
    private static final Pattern GENERAL_TEXT_PATTERN = 
        Pattern.compile("^[\\p{L}0-9 .,'\"()\\-\\/]+$");
    private static final Pattern ACTION_PATTERN = 
        Pattern.compile("^[A-Za-z_]{3,20}$");
    private static final Pattern FORM_ID_PATTERN = 
        Pattern.compile("^[A-Za-z0-9-]{3,50}$");

    public static ValidationResult validateCaseNotes(CaseNotes casenotes) {
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
        
        // Validate notes/content if present
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
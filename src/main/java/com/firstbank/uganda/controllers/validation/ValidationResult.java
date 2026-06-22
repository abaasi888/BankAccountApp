package com.firstbank.uganda.controllers.validation;
import java.util.List;
public class ValidationResult {
    private final List<ValidationError> errors;
    private final List<ValidationWarning> warnings;
    private final int score;
    private final boolean isValid;
    public ValidationResult(List<ValidationError> errors, List<ValidationWarning> warnings, int score) {
        this.errors = errors; this.warnings = warnings; this.score = score; this.isValid = errors.isEmpty();
    }
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Validation Score: ").append(score).append("/100\n");
        sb.append("Status: ").append(isValid ? "PASSED" : "FAILED").append("\n\n");
        if (!errors.isEmpty()) { sb.append("ERRORS:\n"); for (ValidationError e : errors) sb.append("• ").append(e.getMessage()).append("\n"); }
        if (!warnings.isEmpty()) { sb.append("\nWARNINGS:\n"); for (ValidationWarning w : warnings) sb.append("• ").append(w.getMessage()).append("\n"); }
        return sb.toString();
    }
    public boolean isPassed() { return isValid; }
    public List<ValidationError> getErrors() { return errors; }
    public List<ValidationWarning> getWarnings() { return warnings; }
    public int getScore() { return score; }
}

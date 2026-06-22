package com.firstbank.uganda.controllers.validation;
import java.time.LocalDate;
public class ValidationWarning {
    private final String message;
    private final LocalDate timestamp;
    public ValidationWarning(String message) { this.message = message; this.timestamp = LocalDate.now(); }
    public String getMessage() { return message; }
    public LocalDate getTimestamp() { return timestamp; }
}

package com.firstbank.uganda.controllers.validation;
public class ValidationError {
    public enum Severity { ERROR, WARNING, INFO }
    private final String message;
    private final Severity severity;
    public ValidationError(String message, Severity severity) { this.message = message; this.severity = severity; }
    public String getMessage() { return message; }
    public Severity getSeverity() { return severity; }
}

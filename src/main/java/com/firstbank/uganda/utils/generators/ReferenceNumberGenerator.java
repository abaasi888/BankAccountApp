package com.firstbank.uganda.utils.generators;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
public class ReferenceNumberGenerator {
    private static final AtomicInteger counter = new AtomicInteger(0);
    public static String generateReferenceNumber() { return "REF-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "-" + String.format("%04d", counter.incrementAndGet() % 10000); }
    public static String generateTransactionReference() { return "TXN-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "-" + String.format("%04d", counter.incrementAndGet() % 10000); }
}

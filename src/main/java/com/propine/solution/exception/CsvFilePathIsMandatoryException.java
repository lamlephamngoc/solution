package com.propine.solution.exception;

public class CsvFilePathIsMandatoryException extends RuntimeException {
    public CsvFilePathIsMandatoryException(String message) {
        super(message);
    }
}

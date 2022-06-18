package com.propine.solution.exception;

public class CsvFilePathIsNotFound extends RuntimeException {
    public CsvFilePathIsNotFound(String message) {
        super(message);
    }
}

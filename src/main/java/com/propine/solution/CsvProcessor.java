package com.propine.solution;

import com.propine.solution.exception.CsvFilePathIsMandatoryException;
import com.propine.solution.exception.CsvFilePathIsNotFound;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@Slf4j
public class CsvProcessor {
    private Map<String, List<Double>> map = new HashMap<>();
    private final String csvFilePath;
    private final String token;
    private final LocalDateTime inputDate;

    public CsvProcessor(String csvFilePath, String token, LocalDateTime inputDate) {
        this.csvFilePath = csvFilePath;
        this.token = token;
        this.inputDate = inputDate;
    }

    public Map<String, List<Double>> process() throws IOException {
        if (null == this.csvFilePath) {
            throw new CsvFilePathIsMandatoryException("Csv file path is mandatory!!!");
        }
        Path file = FileSystems.getDefault().getPath(this.csvFilePath);
        // skip first line
        try (Stream<String> lines = Files.lines(file).skip(1)) {
            lines.forEach(line -> {
                String[] record = line.split(",");
                String timestamp = record[0];
                LocalDateTime transactionDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(
                        Long.parseLong(timestamp) * 1000), TimeZone.getDefault().toZoneId());
                if (null != inputDate && transactionDate.compareTo(inputDate) != 1)
                    return;
                String depositOrWithdrawal = record[1];
                String token = record[2];
                String amount = record[3];
                List<Double> amounts;
                // given token
                if (checkTokenExisting()) {
                    if (null == map.get(token) && this.token.equals(token)) {
                        amounts = new ArrayList<>();
                        amounts.add(processRecord(Double.parseDouble(amount), depositOrWithdrawal));
                        map.put(token, amounts);
                        return;
                    }
                } else {
                    if (null == map.get(token)) {
                        amounts = new ArrayList<>();
                        amounts.add(processRecord(Double.parseDouble(amount), depositOrWithdrawal));
                        map.put(token, amounts);
                        return;
                    }
                }
                if (null != map.get(token))
                    map.get(token).add(processRecord(Double.parseDouble(amount), depositOrWithdrawal));

            });
        } catch (IOException ioException) {
            throw new CsvFilePathIsNotFound("Error happened while processing CSV due to: " + ioException.getMessage());
        }
        return map;
    }

    public boolean checkTokenExisting() {
        return this.token != null && this.token.length() > 0;
    }

    private Double processRecord(double parseDouble, String depositOrWithdrawal) {
        if ("WITHDRAWAL".equals(depositOrWithdrawal)) {
            return parseDouble * -1;
        }
        return parseDouble;
    }
}

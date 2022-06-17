package com.propine.solution.csv;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.String.format;

@Slf4j
public class CsvProcessor {
    public static final String CRYPTO_COMPARE_API = "https://min-api.cryptocompare.com/data/price?fsym=%s&tsyms=USD";

    private final String csvFilePath;
    private final String token;
    private final LocalDateTime inputDate;

    public CsvProcessor(String csvFilePath, String token, LocalDateTime inputDate) {
        this.csvFilePath = csvFilePath;
        this.token = token;
        this.inputDate = inputDate;
    }

    public Map<String, List<Double>> process() throws IOException {
        Map<String, List<Double>> map = new HashMap<>();
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
            log.error("Error happened while processing CSV due to : {}", ioException.getMessage());
        }
        return map;
    }

    public boolean checkTokenExisting() {
        return this.token != null && this.token.length() > 0;
    }

    public static String exchangeRateByToken(String token) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(format(CRYPTO_COMPARE_API, token))
                .build();

        try (Response response = client.newCall(request).execute()) {
            JSONObject json = JSON.parseObject(response.body().string());
            return json.get("USD").toString();
        }
    }

    private Double processRecord(double parseDouble, String depositOrWithdrawal) {
        if ("WITHDRAWAL".equals(depositOrWithdrawal)) {
            return parseDouble * -1;
        }
        return parseDouble;
    }
}

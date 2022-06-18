package com.propine.solution;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Setter
@Getter
@CommandLine.Command
public class CommandLineParsed implements Runnable {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @CommandLine.Option(names = {"-t", "--token"})
    private String token;
    private LocalDateTime inputDate;

    @CommandLine.Option(names = {"-d", "--date"}, description = {
            "date follow pattern YYYY-MM-DD, example: 2022-07-01"
    })
    public void setDate(String date) {
        this.setInputDate(date + " 00:00");
    }

    public void setInputDate(String inputDate) {
        this.inputDate = LocalDateTime.parse(inputDate, formatter);
    }

    @Override
    public void run() {
        // run processing CSV
        log.info("Start processing CSV file");
        CsvProcessor csvProcessor = new CsvProcessor("csv" + FileSystems.getDefault().getSeparator()
                + "small_transactions.csv",
                this.token, this.inputDate);
        if (csvProcessor.checkTokenExisting()) {
            log.info("Process the latest portfolio for token {}", this.token);
        }
        if (null != inputDate) {
            log.info("Process portfolio value per token in USD on that date {}", this.inputDate);
        }
        Map<String, List<Double>> map = new HashMap<>();
        try {
            map = csvProcessor.process();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new LatestPortfolioPrinter(map).print();
    }

}

package com.propine.solution;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Slf4j
public class LatestPortfolioPrinter {
    private Map<String, List<Double>> map;
    private final boolean testing;

    public LatestPortfolioPrinter(Map<String, List<Double>> map, boolean testing) {
        if (map == null) {
            throw new RuntimeException("Printer require map");
        }
        this.map = map;
        this.testing = testing;
    }

    public void print() {
        DecimalFormat formatter = new DecimalFormat("#,##0.000");
        map.forEach((token, amount) -> {
                    Double exchangeRate = 1.0;
                    try {
                        exchangeRate = new ExchangeRateProcessor(token, this.testing).exchangeRateByToken();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    log.info("{} value in USD: {}", token,
                            formatter.format(getSumOfAmount(amount)
                                    * exchangeRate
                            )
                    );
                }
        );
    }

    private double getSumOfAmount(List<Double> amount) {
        return amount.stream().mapToDouble(Double::doubleValue).sum();
    }
}

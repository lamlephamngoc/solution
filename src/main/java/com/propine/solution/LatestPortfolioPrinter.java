package com.propine.solution;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Slf4j
public class LatestPortfolioPrinter {
    private final Map<String, List<Double>> map;

    public LatestPortfolioPrinter(Map<String, List<Double>> map) {
        this.map = map;
    }

    public void print() {
        DecimalFormat formatter = new DecimalFormat("#,##0.000");
        map.forEach((token, amount) -> {
                    Double exchangeRate = 1.0;
                    try {
                        exchangeRate = new ExchangeRateProcessor(token).exchangeRateByToken();
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

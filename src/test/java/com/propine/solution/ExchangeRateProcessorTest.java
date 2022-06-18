package com.propine.solution;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ExchangeRateProcessorTest {
    @Test
    void test_exchange_rate_testing_amount_BTC() throws IOException {
        // given
        ExchangeRateProcessor exchangeRateProcessor = new ExchangeRateProcessor("BTC", true);
        // when
        Double exchangeRate = exchangeRateProcessor.exchangeRateByToken();
        // then
        assertThat(exchangeRate).isEqualTo(30_000.0);
    }

    @Test
    void test_exchange_rate_testing_amount_ETH() throws IOException {
        // given
        ExchangeRateProcessor exchangeRateProcessor = new ExchangeRateProcessor("ETH", true);
        // when
        Double exchangeRate = exchangeRateProcessor.exchangeRateByToken();
        // then
        assertThat(exchangeRate).isEqualTo(1_000.0);
    }

    @Test
    void test_exchange_rate_testing_amount_XRP() throws IOException {
        // given
        ExchangeRateProcessor exchangeRateProcessor = new ExchangeRateProcessor("XRP", true);
        // when
        Double exchangeRate = exchangeRateProcessor.exchangeRateByToken();
        // then
        assertThat(exchangeRate).isEqualTo(0.3);
    }

    @Test
    void test_exchange_rate_fetch_API_amount_BTC_is_greater_than_1000_USD() throws IOException {
        // given
        ExchangeRateProcessor exchangeRateProcessor = new ExchangeRateProcessor("BTC", false);
        // when
        Double exchangeRate = exchangeRateProcessor.exchangeRateByToken();
        // then
        assertThat(exchangeRate).isGreaterThan(1_000);
    }

    @Test
    void test_exchange_rate_fetch_API_amount_ETH_is_greater_than_1_USD() throws IOException {
        // given
        ExchangeRateProcessor exchangeRateProcessor = new ExchangeRateProcessor("ETH", false);
        // when
        Double exchangeRate = exchangeRateProcessor.exchangeRateByToken();
        // then
        assertThat(exchangeRate).isGreaterThan(1.0);
    }

    @Test
    void test_exchange_rate_fetch_API_amount_XRP_is_less_than_1000_USD() throws IOException {
        // given
        ExchangeRateProcessor exchangeRateProcessor = new ExchangeRateProcessor("XRP", false);
        // when
        Double exchangeRate = exchangeRateProcessor.exchangeRateByToken();
        // then
        assertThat(exchangeRate).isLessThan(10_000.0);
    }
}
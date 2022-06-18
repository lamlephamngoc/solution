package com.propine.solution;

import com.propine.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LatestPortfolioPrinterTest extends BaseTest {

    @Test
    void test_print_when_map_null_should_throw_exception() {
        assertThatThrownBy(() -> new LatestPortfolioPrinter(null, false))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Printer require map");
    }

    @Test
    void test_print() {
        // given
        Map<String, List<Double>> map = new HashMap<>();
        map.put("BTC", Arrays.asList(10.0));
        map.put("XRP", Arrays.asList(-10.0));
        map.put("ETH", Arrays.asList(20.0));
        LatestPortfolioPrinter printer = new LatestPortfolioPrinter(map, true);
        printer.print();

        // then
        assertThat(outContent.toString())
                .containsSequence("BTC value in USD: 300,000.000")
                .containsSequence("XRP value in USD: -3.000")
                .containsSequence("ETH value in USD: 20,000.000");
    }
}
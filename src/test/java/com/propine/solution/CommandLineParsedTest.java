package com.propine.solution;

import com.propine.BaseTest;
import com.propine.solution.CommandLineParsed;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandLineParsedTest extends BaseTest {
    private final CommandLineParsed commandLineParsed = new CommandLineParsed();

    @Test
    void do_not_set_date_should_return_null() {
        // then
        LocalDateTime inputDate = commandLineParsed.getInputDate();
        assertThat(inputDate).isNull();
    }

    @Test
    void set_date_should_return_input_date_in_LocalDateTime() {
        commandLineParsed.setDate("2019-10-25");

        // then
        LocalDateTime inputDate = commandLineParsed.getInputDate();
        assertThat(inputDate).hasToString("2019-10-25T00:00");
    }

    @Test
    void do_not_set_token_should_return_null() {
        // then
        String token = commandLineParsed.getToken();
        assertThat(token).isNull();
    }

    @Test
    void set_token_should_return_token() {
        // when
        commandLineParsed.setToken("BTC");

        // then
        String token = commandLineParsed.getToken();
        assertThat(token).isEqualTo("BTC");
    }

    @Test
    void test_run_function_standalone() {
        commandLineParsed.setTesting(true);
        commandLineParsed.run();
        assertThat(outContent.toString())
                .containsSequence("BTC value in USD: 1,350,243.360")
                .containsSequence("XRP value in USD: 4.660")
                .containsSequence("ETH value in USD: 10,987.672");
    }
}

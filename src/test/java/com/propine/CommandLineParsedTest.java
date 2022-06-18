package com.propine;

import com.propine.solution.CommandLineParsed;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandLineParsedTest {
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
        commandLineParsed.setToken("BTC");
        Thread thread = new Thread(commandLineParsed);
        thread.start();

    }
}

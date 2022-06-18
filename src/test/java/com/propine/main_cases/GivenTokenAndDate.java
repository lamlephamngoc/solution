package com.propine.main_cases;

import com.propine.BaseTest;
import com.propine.solution.Main;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GivenTokenAndDate extends BaseTest {
    @Test
    void given_token_and_date_should_return_the_portfolio_value_per_token_in_USD_on_the_date() throws Exception {
        String[] args = new String[5];
        args[0] = "-d";
        args[1] = "2019-10-25";
        args[2] = "-t";
        args[3] = "BTC";
        args[4] = "-test";
        Main.main(args);
        Assertions.assertThat(outContent.toString())
                .containsSequence("BTC value in USD: 1,050,243.360");
    }
}

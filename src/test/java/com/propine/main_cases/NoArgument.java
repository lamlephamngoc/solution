package com.propine.main_cases;

import com.propine.BaseTest;
import com.propine.solution.Main;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NoArgument extends BaseTest {
    @Test
    void no_argument_should_return_the_latest_portfolio_value_per_token_in_USD() throws Exception {
        String[] args = new String[1];
        args[0] = "-test";
        Main.main(args);
        Assertions.assertThat(outContent.toString())
                .containsSequence("BTC value in USD: 1,350,243.360")
                .containsSequence("XRP value in USD: 4.660")
                .containsSequence("ETH value in USD: 10,987.672");
    }
}

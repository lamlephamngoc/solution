package com.propine.main_cases;

import com.propine.BaseTest;
import com.propine.solution.Main;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
public
class GivenToken extends BaseTest {
    @Test
    void given_BTC_token_should_return_the_latest_portfolio_value_in_USD() throws Exception {
        String[] args = new String[3];
        args[0] = "-t";
        args[1] = "BTC";
        args[2] = "-test";
        Main.main(args);
        Assertions.assertThat(outContent.toString())
                .containsSequence("BTC value in USD: 1,350,243.360");
    }

    @Test
    void given_ETH_token_should_return_the_latest_portfolio_value_in_USD() throws Exception {
        String[] args = new String[3];
        args[0] = "--token";
        args[1] = "ETH";
        args[2] = "-test";
        Main.main(args);
        Assertions.assertThat(outContent.toString())
                .containsSequence("ETH value in USD: 10,987.672");
    }

    @Test
    void given_XRP_token_should_return_the_latest_portfolio_value_in_USD() throws Exception {
        String[] args = new String[3];
        args[0] = "--token";
        args[1] = "XRP";
        args[2] = "-test";
        Main.main(args);
        Assertions.assertThat(outContent.toString())
                .containsSequence("XRP value in USD: 4.660");
    }
}

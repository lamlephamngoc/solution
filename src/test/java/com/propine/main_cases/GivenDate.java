package com.propine.main_cases;

import com.propine.BaseTest;
import com.propine.solution.Main;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
public
class GivenDate extends BaseTest {

    @Test
    void given_date_should_return_the_portfolio_value_per_token_in_USD_on_the_date() throws Exception {
        String[] args = new String[3];
        args[0] = "-d";
        args[1] = "2019-10-25";
        args[2] = "-test";
        Main.main(args);
        Assertions.assertThat(outContent.toString())
                .containsSequence("BTC value in USD: 1,050,243.360")
                .containsSequence("XRP value in USD: 4.660")
                .containsSequence("ETH value in USD: 10,987.672");
    }


}

package com.propine;

import com.propine.solution.Main;
import org.junit.jupiter.api.Test;

public class MainTestCases {

    @Test
    void no_argument_should_return_the_latest_portfolio_value_per_token_in_USD() throws Exception {
        String[] args = new String[0];
        Main.main(args);
    }

    @Test
    void given_BTC_token_should_return_the_latest_portfolio_value_in_USD() throws Exception {
        String[] args = new String[2];
        args[0] = "-t";
        args[1] = "BTC";
        Main.main(args);
    }

    @Test
    void given_ETH_token_should_return_the_latest_portfolio_value_in_USD() throws Exception {
        String[] args = new String[2];
        args[0] = "--token";
        args[1] = "ETH";
        Main.main(args);
    }

    @Test
    void given_XRP_token_should_return_the_latest_portfolio_value_in_USD() throws Exception {
        String[] args = new String[2];
        args[0] = "--token";
        args[1] = "XRP";
        Main.main(args);
    }

    @Test
    void given_date_should_return_the_portfolio_value_per_token_in_USD_on_the_date() throws Exception {
        String[] args = new String[2];
        args[0] = "-d";
        args[1] = "2019-10-25";
        Main.main(args);
    }

    @Test
    void given_token_and_date_should_return_the_portfolio_value_per_token_in_USD_on_the_date() throws Exception {
        String[] args = new String[4];
        args[0] = "-d";
        args[1] = "2019-10-25";
        args[2] = "-t";
        args[3] = "BTC";
        Main.main(args);
    }

}

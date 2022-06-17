package com.propine;

import com.propine.solution.Main;
import org.junit.jupiter.api.Test;

public class TestNoArgument {
    @Test
    void should_return_the_latest_portfolio_value_per_token_in_USD() throws Exception {
        String[] args = new String[0];
        Main.main(args);
    }

}

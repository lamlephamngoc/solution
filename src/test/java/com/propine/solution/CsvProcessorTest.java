package com.propine.solution;

import com.propine.solution.CsvProcessor;
import com.propine.solution.exception.CsvFilePathIsMandatoryException;
import com.propine.solution.exception.CsvFilePathIsNotFound;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.data.MapEntry.entry;

public class CsvProcessorTest {

    @Nested
    class NullAndWrongData {
        @Test
        void should_return_error_message() {
            CsvProcessor csv = new CsvProcessor(null, null, null);
            assertThatThrownBy(() -> csv.process())
                    .isInstanceOf(CsvFilePathIsMandatoryException.class)
                    .hasMessageContaining("Csv file path is mandatory!!!");
        }

        @Test
        void wrong_csv_file_path_should_return_error_message() throws IOException {
            CsvProcessor csv = new CsvProcessor("src/test/resources/small_test_transaction.csv",
                    null, null);
            assertThatThrownBy(() -> csv.process())
                    .isInstanceOf(CsvFilePathIsNotFound.class)
                    .hasMessageContaining("Error happened while processing CSV due to: " +
                            "src/test/resources/small_test_transaction.csv");
        }
    }

    @Nested
    class TestCases {

        @Nested
        class NoArgument {
            @Test
            void should_return_latest_token_with_amount() throws IOException {
                // given
                CsvProcessor csv = new CsvProcessor("src/test/resources/small_test_transactions.csv",
                        null, null);
                // when
                Map<String, List<Double>> processed = csv.process();

                // then
                assertThat(processed)
                        .contains(entry("BTC", Arrays.asList(-10.0, 0.162165)))
                        .contains(entry("XRP", Arrays.asList(0.693272, -0.650535)))
                        .contains(entry("ETH", Arrays.asList(-0.493839, 0.347595)));

            }
        }

        @Nested
        class GivenToken {
            @Test
            void given_BTC_should_return_latest_token_with_amount() throws IOException {
                // given
                CsvProcessor csv = new CsvProcessor("src/test/resources/small_test_transactions.csv",
                        "BTC", null);
                // when
                Map<String, List<Double>> processed = csv.process();

                // then
                assertThat(processed)
                        .contains(entry("BTC", Arrays.asList(-10.0, 0.162165)));

            }

            @Test
            void given_XRP_should_return_latest_token_with_amount() throws IOException {
                // given
                CsvProcessor csv = new CsvProcessor("src/test/resources/small_test_transactions.csv",
                        "XRP", null);
                // when
                Map<String, List<Double>> processed = csv.process();

                // then
                assertThat(processed)
                        .contains(entry("XRP", Arrays.asList(0.693272, -0.650535)));

            }

            @Test
            void given_ETH_should_return_latest_token_with_amount() throws IOException {
                // given
                CsvProcessor csv = new CsvProcessor("src/test/resources/small_test_transactions.csv",
                        "ETH", null);
                // when
                Map<String, List<Double>> processed = csv.process();

                // then
                assertThat(processed)
                        .contains(entry("ETH", Arrays.asList(-0.493839, 0.347595)));

            }
        }

        @Nested
        class GivenDate {
            @Test
            void given_date_should_return_latest_token_with_amount() throws IOException {
                // given
                CsvProcessor csv = new CsvProcessor("src/test/resources/small_test_transactions.csv",
                        null, LocalDateTime.parse("2019-10-25T00:00:00"));
                // when
                Map<String, List<Double>> processed = csv.process();

                // then
                assertThat(processed)
                        .contains(entry("BTC", Arrays.asList(0.162165)))
                        .contains(entry("XRP", Arrays.asList(0.693272, -0.650535)))
                        .contains(entry("ETH", Arrays.asList(-0.493839, 0.347595)));

            }
        }

        @Nested
        class GivenDateAndToken {
            @Test
            void given_date_and_token_should_return_latest_token_with_amount() throws IOException {
                // given
                CsvProcessor csv = new CsvProcessor("src/test/resources/small_test_transactions.csv",
                        "BTC", LocalDateTime.parse("2019-10-25T00:00:00"));
                // when
                Map<String, List<Double>> processed = csv.process();

                // then
                assertThat(processed)
                        .contains(entry("BTC", Arrays.asList(0.162165)))
                        .doesNotContain(entry("XRP", Arrays.asList(0.693272, -0.650535)))
                        .doesNotContain(entry("ETH", Arrays.asList(-0.493839, 0.347595)));

            }
        }

    }

}

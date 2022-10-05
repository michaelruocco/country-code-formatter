package uk.co.mruoc.country;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CountryCodeFormatterTest {

    private final CountryCodeFormatter formatter = new CountryCodeFormatter();

    @Test
    void shouldReturnNullUnchanged() {
        String input = null;

        String formatted = formatter.to2AlphaIfPossible(input);

        assertThat(formatted).isNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABCD", "A", "AB", "GB", ""})
    void shouldReturnInputUnchanged(String input) {
        String formatted = formatter.to2AlphaIfPossible(input);

        assertThat(formatted).isEqualTo(input);
    }

    @Test
    void shouldReturn3AlphaCountryCodeAs2AlphaCountryCode() {
        String input = "GBR";

        String formatted = formatter.to2AlphaIfPossible(input);

        assertThat(formatted).isEqualTo("GB");
    }
}

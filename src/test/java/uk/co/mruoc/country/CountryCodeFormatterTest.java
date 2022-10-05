package uk.co.mruoc.country;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CountryCodeFormatterTest {

    private final CountryCodeFormatter formatter = new CountryCodeFormatter();

    @Test
    void shouldReturnNullUnchanged() {
        String input = null;

        String formatted = formatter.to2AlphaIfPossible(input);

        assertThat(formatted).isNull();
    }

    @Test
    void shouldReturnEmptyStringUnchanged() {
        String input = "";

        String formatted = formatter.to2AlphaIfPossible(input);

        assertThat(formatted).isEmpty();
    }

    @Test
    void shouldReturnAnyStringLongerThan3Unchanged() {
        String input = "ABCD";

        String formatted = formatter.to2AlphaIfPossible(input);

        assertThat(formatted).isEqualTo(input);
    }

    @Test
    void shouldReturnAnyStringShorterThan2Unchanged() {
        String input = "A";

        String formatted = formatter.to2AlphaIfPossible(input);

        assertThat(formatted).isEqualTo(input);
    }

    @Test
    void shouldReturnInvalid2AlphaCountryCodeUnchanged() {
        String input = "AB";

        String formatted = formatter.to2AlphaIfPossible(input);

        assertThat(formatted).isEqualTo(input);
    }

    @Test
    void shouldReturn2AlphaCountryCodeUnchanged() {
        String input = "GB";

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

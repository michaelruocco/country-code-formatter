package uk.co.mruoc.country;

import com.neovisionaries.i18n.CountryCode;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CountryCodeFormatter {

    public String to2AlphaIfPossible(String input) {
        if (StringUtils.isEmpty(input)) {
            return warnEmptyCountryCodeAndReturnInput(input);
        }
        if (is3Alpha(input)) {
            return convert3AlphaTo2Alpha(input);
        }
        if (is2Alpha(input)) {
            return input;
        }
        return warnInvalidLengthAndReturnInput(input);
    }

    private static boolean is2Alpha(String input) {
        return input.length() == 2;
    }

    private static boolean is3Alpha(String input) {
        return input.length() == 3;
    }

    private static String convert3AlphaTo2Alpha(String input) {
        return Optional.ofNullable(CountryCode.getByAlpha3Code(input))
                .map(cc -> to2AlphaIfPossible(input, cc))
                .orElse(warnInvalidCodeAndReturnInput(input));
    }

    private static String to2AlphaIfPossible(String input, CountryCode countryCode) {
        String output = countryCode.getAlpha2();
        log.debug("converted 3 alpha country code {} to 2 alpha code {}", input, output);
        return output;
    }

    private static String warnEmptyCountryCodeAndReturnInput(String input) {
        log.warn("country code is null or empty so returning raw value {}", input);
        return input;
    }

    private static String warnInvalidCodeAndReturnInput(String input) {
        log.warn("country code is not recognised so returning raw value {}", input);
        return input;
    }

    private static String warnInvalidLengthAndReturnInput(String input) {
        log.warn("country code is not 2 or 3 alpha so returning raw value {}", input);
        return input;
    }
}

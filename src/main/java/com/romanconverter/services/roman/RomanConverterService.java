package com.romanconverter.services.roman;

import com.romanconverter.domain.roman.RomanNumeral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RomanConverterService {

    private static final Logger log = LoggerFactory.getLogger(RomanConverterService.class);

    public String romanToArabic(String input) {

        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be null or blank.");
        }

        input = sanitizeInput(input);

        String romanNumeral = input.toUpperCase();
        if (!isValidRomanNumeral(romanNumeral)) {
            log.error("Invalid Roman numeral: {}", input);
            throw new IllegalArgumentException("Invalid Roman numeral: " + input);
        }

        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((!romanNumeral.isEmpty()) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        log.info("Converted Roman numeral '{}' to Arabic numeral '{}'", input, result);
        return String.valueOf(result);
    }


    public String arabicToRoman(String input) {

        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be null or blank.");
        }

        input = sanitizeInput(input);

        int inputValue = Integer.parseInt(input);
        if (inputValue <= 0 || inputValue >= 4000) {
            throw new IllegalArgumentException("Input must be between 1 and 3999.");
        }

        StringBuilder result = new StringBuilder();

        for (RomanNumeral symbol : RomanNumeral.getReverseSortedValues()) {
            while (inputValue >= symbol.getValue()) {
                result.append(symbol.name());
                inputValue -= symbol.getValue();
            }
        }

        log.info("Converted Arabic numeral '{}' to Roman numeral '{}'", input, result);
        return result.toString();
    }


    private boolean isValidRomanNumeral(String input) {
        String romanRegex = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        return input.matches(romanRegex);
    }

    private String sanitizeInput(String input){
        return input.replaceAll("[\n\r]", "_");
    }

}
package com.romanconverter.services.roman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RomanConverterServiceTest {

    private RomanConverterService romanConverterService;


    @BeforeEach
    void setUp() {
        romanConverterService = new RomanConverterService();
    }


    @Test
    void testRomanToArabic_ValidRomanNumerals() {
        assertEquals("1", romanConverterService.romanToArabic("I"));
        assertEquals("4", romanConverterService.romanToArabic("IV"));
        assertEquals("9", romanConverterService.romanToArabic("IX"));
        assertEquals("58", romanConverterService.romanToArabic("LVIII"));
        assertEquals("3999", romanConverterService.romanToArabic("MMMCMXCIX"));
    }


    @Test
    void testRomanToArabic_InvalidRomanNumerals() {
        // Test for invalid Roman numeral
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            romanConverterService.romanToArabic("IIII");
        });
        assertEquals("Invalid Roman numeral: IIII", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            romanConverterService.romanToArabic("MMMM");
        });
        assertEquals("Invalid Roman numeral: MMMM", exception.getMessage());
    }


    @Test
    void testArabicToRoman_ValidArabicNumbers() {
        assertEquals("I", romanConverterService.arabicToRoman("1"));
        assertEquals("IV", romanConverterService.arabicToRoman("4"));
        assertEquals("IX", romanConverterService.arabicToRoman("9"));
        assertEquals("LVIII", romanConverterService.arabicToRoman("58"));
        assertEquals("MMMCMXCIX", romanConverterService.arabicToRoman("3999"));
    }


    @Test
    void testArabicToRoman_InvalidArabicNumbers() {
        // Test invalid cases (numbers outside the valid range)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            romanConverterService.arabicToRoman("0");
        });
        assertEquals("Input must be between 1 and 3999.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            romanConverterService.arabicToRoman("4000");
        });
        assertEquals("Input must be between 1 and 3999.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            romanConverterService.arabicToRoman("-5");
        });
        assertEquals("Input must be between 1 and 3999.", exception.getMessage());
    }


    @Test
    void testRomanToArabic_NullOrBlankInput() {
        // Null and empty input should throw IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            romanConverterService.romanToArabic(null);
        });
        assertEquals("Input cannot be null or blank.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            romanConverterService.romanToArabic(" ");
        });
        assertEquals("Input cannot be null or blank.", exception.getMessage());
    }


    @Test
    void testArabicToRoman_NullOrBlankInput() {
        // Null and empty input should throw IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            romanConverterService.arabicToRoman(null);
        });
        assertEquals("Input cannot be null or blank.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            romanConverterService.arabicToRoman(" ");
        });
        assertEquals("Input cannot be null or blank.", exception.getMessage());
    }
}

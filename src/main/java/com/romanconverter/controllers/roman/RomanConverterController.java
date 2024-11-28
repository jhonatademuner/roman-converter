package com.romanconverter.controllers.roman;

import com.romanconverter.services.roman.RomanConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RomanConverterController {

    @Autowired
    private RomanConverterService romanConverterService;


    @PostMapping("api/roman-to-arabic")
    public String convertRomanToArabic(@RequestParam String input){
        return romanConverterService.romanToArabic(input);
    }


    @PostMapping("api/arabic-to-roman")
    public String convertArabicToRoman(@RequestParam String input){
        return romanConverterService.arabicToRoman(input);
    }

}

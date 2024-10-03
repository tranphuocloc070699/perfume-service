package com.loctran.service.utils;

public class StringUtil {
    public static String convertToSlug(String input){
        return input.toLowerCase().replace(" ", "-");
    }

}

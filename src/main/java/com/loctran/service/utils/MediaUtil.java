package com.loctran.service.utils;

public class MediaUtil {
    public static String getExtension(String fileName) {
      return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}

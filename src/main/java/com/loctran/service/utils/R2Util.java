package com.loctran.service.utils;

import java.net.URI;
import java.util.Arrays;

public class R2Util {

  public static String extractPath(String r2Url) {
    try {
      // Extract path from URL
      URI uri = new URI(r2Url);
      String path = uri.getPath().substring(1);
      System.out.println(path);
      return path;
    } catch (Exception e) {
      System.err.println("Error extracting EntityType: " + e.getMessage());
    }
    return "";

  }

}

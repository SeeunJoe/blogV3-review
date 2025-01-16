package com.example.blogv11._core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MyDate {

  public static String formatDate(Timestamp createdAt) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = formatter.format(createdAt);
    return formattedDate;

  }

}
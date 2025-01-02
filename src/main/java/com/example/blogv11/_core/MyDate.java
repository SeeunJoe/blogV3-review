package com.example.blogv11._core;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {

  public static String formatDate(Timestamp createdAt) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = formatter.format(createdAt);
    return formattedDate;

  }

}
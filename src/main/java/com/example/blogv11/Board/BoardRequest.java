package com.example.blogv11.Board;

import lombok.Data;

public class BoardRequest {

  // form에서 보낸 값을 바로 받아와야하기 때문에 name의 키값과 동일하게 필드 작성해야한다
  @Data
  public static class SaveDTO{
    private String title;
    private String content;
  }

  @Data
  public static class UpdateDTO {
    private String title;
    private String content;
  }
}

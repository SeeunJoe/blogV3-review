package com.example.blogv11.Board;

import lombok.Data;

import static com.example.blogv11._core.MyDate.formatDate;

public class BoardResponse {

  //static으로 시작 시 띄운다

  @Data // getter,setter알아서 생성
  public static class DTO{
    private int id;
    private String title;

    //Board객체에 있는 걸 옮긴다
    public DTO(Board board) {
      this.id = board.getId();
      this.title = board.getTitle();
    }
  }

  public static class DetailDTO {
    private int id;
    private String title;
    private String content;
    private String createdAt;

    public DetailDTO(Board board) {
      this.id = board.getId();
      this.title = board.getTitle();
      this.content = board.getContent();
      this.createdAt = formatDate(board.getCreatedAt()); // ToDo: 2024.11.18 형태로 바꾸기
    }
  }

  public static class UpdateFormDTO {    private int id;
    private String title;
    private String content;
    private String createdAt;

    public UpdateFormDTO(Board board) {
      this.id = board.getId();
      this.title = board.getTitle();
      this.content = board.getContent();
      this.createdAt = formatDate(board.getCreatedAt()); // ToDo: 2024.11.18 형태로 바꾸기
    }

  }
}

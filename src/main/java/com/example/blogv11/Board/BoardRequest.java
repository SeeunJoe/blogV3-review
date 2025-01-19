package com.example.blogv11.Board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class BoardRequest {

  // form에서 보낸 값을 바로 받아와야하기 때문에 name의 키값과 동일하게 필드 작성해야한다.

  @Data
  public static class SaveDTO{
    @NotBlank(message = "야 비우지마") // NotBlank는 공백,띄어쓰기도 안됨
    private String title;
    @NotBlank(message = "내용 공백 안됨") // -> @validation을 붙여줘야 작동한다.
    private String content;
    // 외부에서 들어온 dto를 model로 변경해서 db로 보낸다.
    public Board toEntity(){
      // setter말고 생성자를 만들어라
      Board board = new Board(null,title,content,null);
      return board;
    }
  }

  @Data
  public static class UpdateDTO {
    private String title;
    private String content;
  }
}

package com.example.blogv11.Board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor // db에서 조회해서 가져온 RS를 디폴트 생성자를 호출해서 new하고 값을 채워준다.
@Getter
@Table(name="board_tb")
@Entity
public class Board {
  @Id //jakarta
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  private String content;
  private Timestamp createdAt;
}

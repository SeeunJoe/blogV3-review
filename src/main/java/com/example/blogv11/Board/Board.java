package com.example.blogv11.Board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

// blog ver2
@AllArgsConstructor
@NoArgsConstructor // db에서 조회해서 가져온 RS를 디폴트 생성자를 호출해서 new하고 값을 채워준다.// defalut 생성자가 있어야 reflection해서 값을 매핑해서 가져올 수 있다.
@Getter
@Table(name="board_tb")
@Entity
public class Board {
  @Id //jakarta
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(nullable = false)
  private String title;
  private String content;
  @CreationTimestamp // native query에서는 안먹음
  private Timestamp createdAt;

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}

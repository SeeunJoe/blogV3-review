package com.example.blogv11.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

  // jpa는 EntityManager로 DB에 접근한다.(java에서 DBConnection)
  private final EntityManager em;

  public List<Board> findAll() {
    // java의 field와 colum이 같아야 mapping됨
    Query q = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
    return q.getResultList(); // 통신코드
  }

  public Board findById(int id) {
    Query q = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
    q.setParameter(1, id); //물음표 완성하기(물음표 순서, 물음표에 바인딩 될 변수값)
    return (Board) q.getSingleResult(); //한 건이라 single result
  }

  public void save(Board board) {  // board 객체를 만들어서 던지면 insert해준다.
    em.persist(board);
  }

  public void delete(int id) {
    Query q = em.createNativeQuery("delete from board_tb where id = ?");
    q.setParameter(1, id);
    q.executeUpdate(); //insert, delete, update 때 사용.
  }


  public void update(int id, String title, String content) {
    Query q = em.createNativeQuery("update board_tb set title = ?, content = ? where id = ?");
    q.setParameter(1, title);
    q.setParameter(2, content);
    q.setParameter(3, id);
    q.executeUpdate();
  }
}

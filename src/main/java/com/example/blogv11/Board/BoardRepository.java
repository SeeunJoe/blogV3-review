package com.example.blogv11.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

  // jpa는 EntityManager로 DB에 접근한다.(java에서 DBConnection)
  private final EntityManager em;

  public List<Board> findAll() {
    return em.createQuery("select b from Board b order by b.id").getResultList();
  }

  public Optional<Board> findById(int id) {
    // 프라이머리 키로 찾을 때는 find사용

    // Optional이라는 선물박스로 감싼 걸 넘긴다. 안에는 null일수도 board일수도 있다. 선물박스는 null이 아님
    return Optional.ofNullable(em.find(Board.class, id));

  }

  public void save(Board board) {  // board 객체를 만들어서 던지면 insert해준다.
    em.persist(board);
  }

  public void delete(int id) {
    em.createQuery("delete from Board b where b.id = :id").setParameter("id", id).executeUpdate();
  }

}

package com.example.blogv11.board;

import com.example.blogv11.Board.Board;
import com.example.blogv11.Board.BoardRepository;
import com.example.blogv11.Board.BoardResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest // DB 관련된 자원들을 메모리(IoC)에 올린다.
public class BoardRepositoryTest {

  @Autowired
  private BoardRepository boardRepository;

  @Test
  public void findAll_test(){
    // given - parameter ()에 매개변수를 사용못하므로 직접 내부에 적어

    // when - 테스트할 메서드 적어
    List<Board> list = boardRepository.findAll();
    System.out.println();
    // boardRepository.findAll();메서드가 리턴된 값을 확인하려면 받아서 확인해야해

    // eye 눈으로 보고 확인 - 디버그로 봐도 됨
    for(Board board : list){
      System.out.println(board.getId());
      System.out.println(board.getTitle());
      System.out.println(board.getContent());
      System.out.println(board.getCreatedAt());
    }
  }

  @Test
  public void save_test(){
    //given
    String title = "제목6";
    String content = "내용6";

    //when
//    boardRepository.save(title, content);

    //eye
/*    Board board = boardRepository.findById(6);
    System.out.println(board.getId());
    System.out.println(board.getTitle());
    System.out.println(board.getContent());*/

  } // rollback(@Transactional) -> @DataJpaTest의 meta@에 @Transactional이 존재

  @Test
  public void delete_test(){
    //given
    int id = 1;

    //when
    boardRepository.delete(id);

    //eye
    List<Board> list = boardRepository.findAll();
    System.out.println("몇개냐!!!!"+list.size());
  }

}

package com.example.blogv11.Board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

  private final BoardRepository boardRepository;

  public List<BoardResponse.DTO> 게시글목록보기() {

    List<Board> boardList = boardRepository.findAll();
    // 빈 객체 생성 후 데이터를 저장해서 옮긴다.
    List<BoardResponse.DTO> dtos = new ArrayList<>();

    for (Board board : boardList) {
      BoardResponse.DTO dto = new BoardResponse.DTO(board);
      dtos.add(dto);
    }

    return dtos;
  }

  public BoardResponse.DetailDTO 게시글상세보기(int id) {
    Board board = boardRepository.findById(id);
    return new BoardResponse.DetailDTO(board);
  }

  @Transactional
  public void 게시글쓰기(BoardRequest.SaveDTO saveDTO) {
    boardRepository.save(saveDTO.getTitle(), saveDTO.getContent());
  } // @Transactional이 commit 해준다 -> 메서드 내에 여러개가 있을 때 하나라도 실패하면 롤백 다 성공하면 커밋해줌

  @Transactional
  public void 게시글삭제하기(int id) {
    boardRepository.delete(id);
  } //comiit or rollback이 됨 -> select는 필요없지만 insert, update, delete에는 필요

  public BoardResponse.UpdateFormDTO 게시글수정화면보기(int id) {
    Board board = boardRepository.findById(id);
    return new BoardResponse.UpdateFormDTO(board);
  }

  @Transactional
  public void 게시글수정하기(int id, BoardRequest.UpdateDTO updateDTO) {
    // Repository의 update를 다른 Service의 메서드가 사용할 수도 있다
    // -> Repository의 update가 UpdateDTO에 의존되지 않도록 updateDTO.getTitle이런식으로 빼서 Repository로 넘겨준다.
    // Controller와 Service는 1:1 매칭된다. 그래서 종속돼도 됨 -> UpdateDTO 통째로 넘김
    boardRepository.update(id,updateDTO.getTitle(),updateDTO.getContent());
  }
}



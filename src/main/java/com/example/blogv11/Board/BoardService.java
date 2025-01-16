package com.example.blogv11.Board;

import com.example.blogv11._core.error.ex.Exception404;
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
    Board board = boardRepository.findById(id)
        .orElseThrow(()->new Exception404("해당 id의 게시글이 없습니다:"+id));
    return new BoardResponse.DetailDTO(board);
  }

  @Transactional
  public void 게시글쓰기(BoardRequest.SaveDTO saveDTO) {
    boardRepository.save(saveDTO.toEntity());
  }

  @Transactional
  public void 게시글삭제하기(int id) {
    boardRepository.delete(id);
  } //comiit or rollback이 됨 -> select는 필요없지만 insert, update, delete에는 필요

  public BoardResponse.UpdateFormDTO 게시글수정화면보기(int id) {
    Board board = boardRepository.findById(id)
        .orElseThrow(()->new Exception404("해당 id의 게시글이 없습니다:"+id));
    return new BoardResponse.UpdateFormDTO(board);
  }

  @Transactional
  public void 게시글수정하기(int id, BoardRequest.UpdateDTO updateDTO) {
    Board board = boardRepository.findById(id)
        .orElseThrow(()->new Exception404("해당 id의 게시글이 없습니다:"+id));
    board.update(updateDTO.getTitle(), updateDTO.getContent());
    // 영속화된 객체상태 변경 - update + commit => 더티체킹
  }
}



package com.example.blogv11.Board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/")
  public String list(Model model) { //DS(request객체를 model이라는 객체로 랩핑해서 전달해준다)
    List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
    model.addAttribute("models", boardList);
    return "list";
  }

  /**
   * 쿼리스트링(where절) : /board?title=바다 -> unique하지 않은 것 &써도 됨
   * 패스변수(where절) : /board/1  -> unique한 것 &필요없다
   */

  @GetMapping("/board/{id}")
  public String detail(@PathVariable int id,Model model) {
    BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
    model.addAttribute("model", boardDetail);
    return "detail";
  }

  @GetMapping("/board/save-form")
  public String saveForm() {
    return "save-form";
  }

  @PostMapping("/board/save")
  public String save(BoardRequest.SaveDTO saveDTO) {

    boardService.게시글쓰기(saveDTO);

    return "redirect:/";
  }

  @PostMapping("/board/{id}/delete")
  public String delete(@PathVariable int id) {
    boardService.게시글삭제하기(id);
    return "redirect:/";
  }

  @GetMapping("/board/{id}/update-form")
  public String updateForm(@PathVariable int id, Model model) {
    BoardResponse.UpdateFormDTO boardDetail = boardService.게시글수정화면보기(id);
    model.addAttribute("model", boardDetail);
    return "update-form";
  }

  @PostMapping("/board/{id}/update")
  public String update(@PathVariable("id") int id,BoardRequest.UpdateDTO updateDTO) {
    boardService.게시글수정하기(id,updateDTO);
    return "redirect:/board/"+id;
  }



}

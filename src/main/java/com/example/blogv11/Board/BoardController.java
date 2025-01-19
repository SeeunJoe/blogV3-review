package com.example.blogv11.Board;

import com.example.blogv11._core.error.ex.Exception400;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Field;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

  private final BoardService boardService;



  // 메인페이지 - 게시글 목록 보여줌
  @GetMapping("/")
  public String list(Model model) { //DS(request객체를 model이라는 객체로 랩핑해서 전달해준다)
    List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
    model.addAttribute("models", boardList);
    return "list";
  }

  // board_id에 해당하는 게시글 상세보기
  @GetMapping("/board/{id}")
  public String detail(@PathVariable int id,Model model) {
    BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
    model.addAttribute("model", boardDetail);
    return "detail";
  }

  // 글쓰기 페이지
  @GetMapping("/board/save-form")
  public String saveForm() {
    return "save-form";
  }

  // 글쓰기 저장
  @PostMapping("/board/save")
  public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) {
    // Valid를 붙여줘야 SaveDTO에 가서 @NotBlank를 작동시킨다.
    // saveDTO에서 validation을 체크하고 난 오류를 바.로 옆에 있는 Errors에 넣어준다. -> 그래서 꼭 바로 옆에 Errors 넣어줘야한다.

/*    if(errors.hasErrors()) { // 에러 존재하면 true가 뜬다 -> 성공 시 작동 안함
*//*      for(FieldError error : errors.getFieldErrors()) {
        System.out.println(error.getField());
        System.out.println(error.getDefaultMessage());
      }*//*
      String errMsg = errors.getFieldErrors().get(0).getField()+":"+errors.getFieldErrors().get(0).getDefaultMessage();
      throw new Exception400(errMsg);
      // throw로 터트리면 밑의 코드로 넘어가지 못해!
    }*/

    // TODO: 나중에 삭제하기
//    if(saveDTO.getTitle().isBlank()){
//      throw new RuntimeException("title에 공백 혹은 null이 들어갈 수 없습니다");
//    }
    // 나중에 잡지 못한 에러들 다 runtimeException으로 들어간다.

    boardService.게시글쓰기(saveDTO);

    return "redirect:/";
  }

  // 글 삭제하기
  @PostMapping("/board/{id}/delete")
  public String delete(@PathVariable int id) {
    boardService.게시글삭제하기(id);
    return "redirect:/";
  }

  // 글 수정 페이지
  @GetMapping("/board/{id}/update-form")
  public String updateForm(@PathVariable int id, Model model) {
    BoardResponse.UpdateFormDTO boardDetail = boardService.게시글수정화면보기(id);
    model.addAttribute("model", boardDetail);
    return "update-form";
  }

  // 글 수정
  @PostMapping("/board/{id}/update")
  public String update(@PathVariable("id") int id,BoardRequest.UpdateDTO updateDTO) {
    boardService.게시글수정하기(id,updateDTO);
    return "redirect:/board/"+id;
  }



}

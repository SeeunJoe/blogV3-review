package com.example.blogv11._core.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 에러처리 - 여기에서만 한다.
@ControllerAdvice
public class MyControllerAdvice {
    /**
     * client에게 응답을 해줘야한다.
     * controllerAdvice는 ViewResolver를 탄다. -> file을 찾는다
     * file이 너무 많아진다 -> javaScript응답으로 보낸다 -> @ResponseBody
     *
     * 매개변수로 에러의 응답을 찾으려면 for문이 너무 많이 돌아간다
     * -> reflection의 연산을 줄이기 위해 @ExceptionHandler(RuntimeException.class)를 붙여준다
     */
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public String err(RuntimeException e){

         //StringBuilder builder = new StringBuilder(); // 문자열을 더하는 builder pattern
        /**
         * springboot는 기본응답 html
         */
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());

        return body;
    }

}

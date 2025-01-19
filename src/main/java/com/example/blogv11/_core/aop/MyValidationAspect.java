package com.example.blogv11._core.aop;

import com.example.blogv11._core.error.ex.Exception400;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component // 메모리에 띄워야하는데 특별한 sip가 없어(controller,service,repository처럼 역할이 없어) -> component로 띄워
@Aspect //메모리에 안 뜸 -> 관점지향을 관리하는 클래스가 돼라
public class MyValidationAspect {


    // 포인트 컷 -> 변수로 할당시킴
//    @Pointcut
//    public void allMethod() {
//    }

    // 행위
    // method 실행 전 before, method 실행 후 after, method 실행 전 후 around
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)") // 포인트 컷(위치) 자리 -> PostMapping 시 실행
    public void validationCheck(JoinPoint jp) {  // method에 접근 -> reflection
        Object[] args = jp.getArgs();

        for (Object arg : jp.getArgs()) {
            if(arg instanceof Errors){ //매개변수에 Errors가 붙어있는지 for문 돌면서 체킹 -> postmapping이어도 매개변수에 errors가 있어야만 실행되게

                Errors errors = (Errors) arg; // 다운캐스팅
                if(errors.hasErrors()) { // 에러 존재하면 true가 뜬다 -> 성공 시 작동 안함
                    String errMsg = errors.getFieldErrors().get(0).getField()+":"+errors.getFieldErrors().get(0).getDefaultMessage();
                    throw new Exception400(errMsg); //
                }
            }
        }
    }

    // object 타입을 return 해줘야한다.
    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)") // 포인트 컷(위치) 자리 -> PostMapping 시 실행
    public Object validationCheck(ProceedingJoinPoint jp) throws Throwable {  // method에 접근 -> reflection
        Object[] args = jp.getArgs();

        for (Object arg : jp.getArgs()) {
            if(arg instanceof Errors){ //매개변수에 Errors가 붙어있는지 for문 돌면서 체킹 -> postmapping이어도 매개변수에 errors가 있어야만 실행되게

                Errors errors = (Errors) arg; // 다운캐스팅
                if(errors.hasErrors()) { // 에러 존재하면 true가 뜬다 -> 성공 시 작동 안함
                    String errMsg = errors.getFieldErrors().get(0).getField()+":"+errors.getFieldErrors().get(0).getDefaultMessage();
                    throw new Exception400(errMsg); //
                }
            }
        }
        System.out.println("직전");
        Object ob = jp.proceed(); // controller의 save 메서드->proceed , ob-> return값 redirect:/
        System.out.println("직후");
        return ob;
    }
}

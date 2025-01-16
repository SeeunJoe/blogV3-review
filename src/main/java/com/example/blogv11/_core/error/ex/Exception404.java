package com.example.blogv11._core.error.ex;

public class Exception404 extends RuntimeException {
    public Exception404(String msg) {
        super(msg);
    }
    // 실제로는 username check exception같이 디테일하게 만든다.
}

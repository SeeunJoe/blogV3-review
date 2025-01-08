package com.example.blogv11.Board;

public class Elice {
  /**
   * getter 꺼내서 읽는거
   * setter
   */
  private int height;

  // 생성자는 초기값 setting
  public Elice(int height) {
    this.height = 100;
  }

  public int getHeight() {
    return height;
  }

  // setter는 변화값 -> 편의메서드 -> 의미 없음
  public void setHeight(int height) {
    this.height = height;
  }
  // 의미있는 method
  public void 버섯먹기(){
    height = height - 10;
  }

}
